package com.example.dcf.login;

import java.util.Random;

public class BGObjects {
    Random r;
    private float x;
    private float y;
    private int max;
    private int speed;
    private int j=0;
    public BGObjects(int maxX,int maxY,float ballspeed){
        speed = (int)ballspeed;
        r = new Random();
        max =maxX;
        x = r.nextInt(maxX);
        y = r.nextInt(maxY);

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void objmove(){

        if(x<0){

            x = r.nextInt(50)+max;

        }

        x = x-1;
    }
}

