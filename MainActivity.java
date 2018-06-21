package com.example.dcf.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    b1 = (Button)findViewById(R.id.signin1);
    b2 = (Button)findViewById(R.id.signup1);
    b1.setOnClickListener(this);
    b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == b1){
            signin();
        }else if(v == b2){
            signup();
        }
    }

    public void signin(){

        Intent i = new Intent("login");
        startActivity(i);


    }
    public void signup(){

        Intent i = new Intent("register");
        startActivity(i);


    }

}
