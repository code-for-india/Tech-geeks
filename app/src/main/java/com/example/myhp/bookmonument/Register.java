package com.example.myhp.bookmonument;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


/**
 * Created by my hp on 3/19/2016.
 */
public class Register extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Toolbar t=(Toolbar)findViewById(R.id.toolbar_register);
        setSupportActionBar(t);
        TextView uid=(TextView)findViewById(R.id.textView_register_uid);
        TextView name=(TextView)findViewById(R.id.text_register_name);
        String uid1=getIntent().getExtras().getString("uid");
        String name1=getIntent().getExtras().getString("name");
        uid.setText(uid1);
        name.setText(name1);
        findViewById(R.id.button_uploadimage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent in= new Intent(getApplicationContext(),Cameraview.class);
        startActivity(in);
    }
}
