package com.example.myhp.bookmonument;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by my hp on 2/21/2016.
 */
public class Logindata extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response);
     //   final TextView mTextView = (TextView) findViewById(R.id.textView4);

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.kairos.com/enroll";
JSONObject mainjson=new JSONObject();
        try {
            mainjson.put("image","http://media.emirates247.com/images/2016/02/brad-pitt-05.jpg");
            mainjson.put("subject_id","bradpitt2");
            mainjson.put("gallery_name","celebrities");
            mainjson.put("selector","SETPOS");
            mainjson.put("symmetricFill","true");
        } catch (JSONException e) {
            e.printStackTrace();
        }


// Request a string response from the provided URL.
        //JsonObjectRequest jsObjRequest = null;
        JsonArrayRequest jsObjRequest=null;


            jsObjRequest = new JsonArrayRequest
                    (Request.Method.POST, url,mainjson, new Response.Listener<JSONArray>() {



                        @Override
                        public void onResponse(JSONArray response) {
                            //try {

                                Toast.makeText(getApplicationContext(),"respons is"+response.toString(),Toast.LENGTH_LONG).show();
                         //   } catch (JSONException e) {
                            //    e.printStackTrace();
                            //    Toast.makeText(getApplicationContext(),"it is not json",Toast.LENGTH_LONG).show();
                          //  }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub
                            Toast.makeText(getApplicationContext(),"it is error"+error.toString(),Toast.LENGTH_LONG).show();
                           // mTextView.setText("Expected error"+error.toString());
                        }


                    })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    params.put("app_id", "60c8b095");
                    params.put("app_key", "68fd6a3c557665fc1aed57bf3b6dff70");

                    return params;
                }
            };

        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsObjRequest.setRetryPolicy(policy);


// Add the request to the RequestQueue.
        if(jsObjRequest!=null)
        queue.add(jsObjRequest);
    }
}
