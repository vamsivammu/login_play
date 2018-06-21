package com.example.dcf.login;

import java.util.Random;

public class Obstacles {

    private float left;
    private float top,top2;
    private float right;
    private float x ;
    private int bottom,bottom2;
    Random r;
    private int j;
    private float gap;
    public Obstacles(int xmax,int ymax,int leftx){

        left = leftx;
        r = new Random();
        bottom2 = ymax;
        top = 0;
        right = left +30;
        bottom = r.nextInt(300)+200;
        top2 = bottom + 400;

    }

    public float getLeft() {
        return (float)left;
    }

    public float getTop2() {
        return top2;
    }

    public float getBottom2() {
        return (float)bottom2;
    }

    public float getTop() {
        return (float)top;
    }

    public float getRight() {
        return (float)right;
    }

    public float getBottom() {
        return (float)bottom;
    }

    public void move(){

        left = left - 2;
        right= right -2;



    }
    public void update(){
        j = j+1;
        left = 8230 + j*200;
        right = left +30;
        bottom = r.nextInt(400)+200;
        top2 = bottom + 400;

    }

}
