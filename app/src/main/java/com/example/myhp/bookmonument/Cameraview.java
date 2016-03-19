package com.example.myhp.bookmonument;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by my hp on 10/6/2015.
 */
public class Cameraview extends Activity implements View.OnClickListener{

    Button b1;
    ImageView im;
    ImageButton hello;
    Intent i;
    Bitmap bmp;
    static int cameraData;
    String strUpLoadServerUri="http://bloodbanksys.esy.es/bloodbank/image.php";
    @Override
    protected void onCreate(Bundle bund)
    {
        super.onCreate(bund);
        setContentView(R.layout.imageview);
        initialize();
        InputStream is=getResources().openRawResource(R.drawable.greenball);
        bmp= BitmapFactory.decodeStream(is);
    }
    private void initialize()
    {
     b1=(Button)findViewById(R.id.wallpaperbutton);
        im=(ImageView)findViewById(R.id.icamera);
        hello=(ImageButton)findViewById(R.id.hello);
        b1.setOnClickListener(this);

        hello.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
     switch(v.getId())
     {
         case R.id.wallpaperbutton:
             try {
                 getApplicationContext().setWallpaper(bmp);
             }
             catch(IOException e)
             {
                 e.printStackTrace();
             }
             break;

         case R.id.hello:
             i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(i,cameraData);

             break;


     }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            // bmp.g
            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplicationContext(), bmp);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            File finalFile = new File(getRealPathFromURI(tempUri));
            Toast.makeText(getApplicationContext(), finalFile.toString(), Toast.LENGTH_LONG).show();
            // System.out.println(mImageCaptureUri);
            if(finalFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(finalFile.getAbsolutePath());


                im.setImageBitmap(myBitmap);
                sendimagetoserver(finalFile, finalFile.getAbsolutePath());
            }


        }
    }

        public Uri getImageUri(Context inContext, Bitmap inImage) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
            return Uri.parse(path);
        }

        public String getRealPathFromURI(Uri uri) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }



    public void sendimagetoserver(File sourceFile,String strUploadFiles)
    {

        HttpURLConnection mHttpURLConnection = null;
        DataOutputStream mOutputStream = null;
        String strLineEnd = "\r\n";
        String strTwoHyphens = "--";
        //strUpLoadServerUri = UploadServerPath;
        String strBoundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
     //   File sourceFile = new File(strUploadFiles);
        int serverResponseCode=-1;
        String mi="efwd";
      //  if (sourceFile.exists()) {

            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(strUpLoadServerUri);

                // Open a HTTP connection to the URL

                mHttpURLConnection = (HttpURLConnection) url
                        .openConnection();
                mHttpURLConnection.setDoInput(true); // Allow Inputs
                mHttpURLConnection.setDoOutput(true); // Allow Outputs
                mHttpURLConnection.setUseCaches(false); // Don't use a Cached Copy

                mHttpURLConnection.setRequestMethod("POST");
                mHttpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                mHttpURLConnection.setRequestProperty("ENCTYPE","multipart/form-data");
                mHttpURLConnection.setRequestProperty("Content-Type","multipart/form-data;boundary=" + strBoundary);
                mHttpURLConnection.setRequestProperty("uploaded_file",strUploadFiles);

                mOutputStream = new DataOutputStream(mHttpURLConnection.getOutputStream());

                mOutputStream.writeBytes(strTwoHyphens + strBoundary + strLineEnd);
                mOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename="+ strUploadFiles + strLineEnd);

                mOutputStream.writeBytes(strLineEnd);

                // create a buffer of maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    mOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                mOutputStream.writeBytes(strLineEnd);
                mOutputStream.writeBytes(strTwoHyphens + strBoundary + strTwoHyphens + strLineEnd);

                // Responses from the server (code and message)
                serverResponseCode= mHttpURLConnection.getResponseCode();
                mi=mHttpURLConnection.getResponseMessage();

                // close the streams //
                fileInputStream.close();
                mOutputStream.flush();
                mOutputStream.close();

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                //Log.e("Upload file to server", "error: " + ex.getMessage(),ex);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
               // Log.e("Upload file to server Exception","Exception : " + e.getMessage(), e);
            }
     //   }

        if (serverResponseCode == 200) {
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
            Log.d("File Uploaded For ",strUploadFiles + "   Successful");
        }
        else{
            Toast.makeText(getApplicationContext(),"Failure"+serverResponseCode+mi,Toast.LENGTH_LONG).show();
            Log.d("File Uploaded For ", strUploadFiles + "   Failed");
        }
    }

}
