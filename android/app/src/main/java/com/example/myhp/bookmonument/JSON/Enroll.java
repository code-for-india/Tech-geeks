package com.example.myhp.bookmonument.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by my hp on 3/19/2016.
 */
public class Enroll
{
    JSONObject mainjson;
    public JSONObject buildjson()
    {
        mainjson=new JSONObject();
        try {
            mainjson.put("image","http://media.kairos.com/kairos-elizabeth.jpg");
            mainjson.put("gallery_name","gallerytest1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mainjson;

    }

}
