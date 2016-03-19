package com.example.myhp.bookmonument;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myhp.bookmonument.JSON.Enroll;
import com.example.myhp.bookmonument.JSON.Enrollmain;
import com.example.myhp.bookmonument.Utils.Connection;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;

public class MonumentMain extends AppCompatActivity implements View.OnClickListener {
String uid,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monument_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//new CallJSON().execute();
//

//        Snackbar.make(findViewById(R.layout.activity_monument_main), "You must enter your Aadhar UID or Passport to continue", Snackbar.LENGTH_LONG)
     //           .setAction("Action", null).show();
        findViewById(R.id.button_register).setOnClickListener(this);
        findViewById(R.id.button_scan).setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_monument_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
switch (v.getId()) {
    case R.id.button_register:
    Intent in = new Intent(getApplicationContext(), Cameraview.class);
        startActivity(in);
        break;
    case R.id.button_scan:
        new IntentIntegrator(this).initiateScan();


}
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);


        if (resultCode == Activity.RESULT_OK) {
            // Parsing bar code reader result
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
            //Toast.makeText(this,"its working"+result.getContents(),Toast.LENGTH_LONG).show();

            XmlPullParserFactory factory = null;
            try {
                factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                StringBuilder sb=new StringBuilder();
                xpp.setInput(new StringReader(result.getContents()));
                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        // sb.append("startdocument"+"\n");
                    } else if(eventType == XmlPullParser.END_DOCUMENT) {
                        //sb.append("enddocument" + "\n");
                    } else if(eventType == XmlPullParser.START_TAG&&xpp.getName().equals("PrintLetterBarcodeData")) {
                        uid=xpp.getAttributeValue(0);
                        name=xpp.getAttributeValue(1);

                    } else if(eventType == XmlPullParser.END_TAG) {
                        // sb.append("End tag " + xpp.getName() + "\n");
                    } else if(eventType == XmlPullParser.TEXT) {
                        // sb.append("Text " + xpp.getText() + "\n");
                    }
                    eventType = xpp.next();
                }
                Intent in=new Intent(getApplicationContext(),Register.class);
                Bundle b=new Bundle();
                b.putString("uid",uid);
                b.putString("name",name);
                in.putExtras(b);
                startActivity(in);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public class CallJSON extends AsyncTask<Void, Void, String>
    {





        @Override
        protected String doInBackground(Void... params) {
            Enrollmain e=new Enrollmain();

            Connection c=new Connection("https://api.kairos.com/enroll",e.buildjson());
            String result= c.connectiontask();
            return result;
        }
        @Override
        protected void onPostExecute(final String success) {
            Toast.makeText(getApplicationContext(),"it is "+success ,Toast.LENGTH_LONG).show();
        }
    }


}
