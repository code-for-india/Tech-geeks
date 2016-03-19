package com.example.myhp.bookmonument.JSON;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by my hp on 3/19/2016.
 */
public class Enrollmain {

    JSONObject mainjson;
    public JSONObject buildjson()
    {
        mainjson=new JSONObject();
        try {
            mainjson.put("image","http://media.emirates247.com/images/2016/02/brad-pitt-05.jpg");
            mainjson.put("subject_id","bradpitt1");
            mainjson.put("gallery_name","celebrities");
            mainjson.put("selector","SETPOS");
            mainjson.put("symmetricFill","true");


        } catch (JSONException e) {
            e.printStackTrace();


        }
        return mainjson;

    }
}
