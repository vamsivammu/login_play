package com.example.dcf.login;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.MotionEvent;

public class Game extends AppCompatActivity {
    private int sc;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        Display d = getWindowManager().getDefaultDisplay();

        Point p = new Point();
        d.getSize(p);

        View ball = new BouncingBall(this,p.x,p.y);

        setContentView(ball);
        ball.setBackgroundColor(Color.BLACK);



    }



}
