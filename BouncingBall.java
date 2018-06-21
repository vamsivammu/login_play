package com.example.dcf.login;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BouncingBall extends View {
    private int xmin=0;
    private int ymin =0;
    private int xmax;
    private int ymax;
    private float e=0.8f;
    private float ballspeedy = 0;
    private float ballspeedx = 0;
    private float ballacc = 1;
    private float ballrad = 40;
    private float ballcx = ballrad+100;
    private  float ballcy = ballrad+300;
    private Paint paint;
    private RectF bounds;
    private ArrayList<BGObjects> objs;
    private ArrayList<Obstacles> obst;
    private boolean b = true;
    private int score;
    public BouncingBall(Context context,int x,int y) {
        super(context);
        score =0;
        xmax=x;
        ymax=y;
        objs= new ArrayList<BGObjects>();
        obst = new ArrayList<Obstacles>();
        bounds = new RectF();
        paint = new Paint();
        for(int i =0;i<150;i++){

            BGObjects o = new BGObjects(x,y,  ballspeedx);
            objs.add(o);
            //this.setFocusableInTouchMode(b);

        }
        for(int j =0;j<40;j++){
            Random r = new Random();

            Obstacles ob = new Obstacles(x,y,400+j*400);
            obst.add(ob);
        }

    }
    @Override
    protected void onDraw(Canvas canvas){
        for(BGObjects o : objs){
            paint.setStrokeWidth(4);
            paint.setColor(Color.WHITE);
            canvas.drawPoint(o.getX(),o.getY(),paint);
        }
        bounds.set(ballcx-ballrad,ballcy-ballrad,ballcx+ballrad,ballcy+ballrad);
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);

        canvas.drawOval(bounds,paint);


        for(Obstacles ob:obst){

            paint.setColor(Color.RED);
            canvas.drawRect(ob.getLeft(),ob.getTop(),ob.getRight(),ob.getBottom(),paint);
            canvas.drawRect(ob.getLeft(),ob.getTop2(),ob.getRight(),ob.getBottom2(),paint);

        }

//        for(BGObjects o:objs){
//            if(o.getX()<xmin){
//                objs.remove(o);
//
//                BGObjects bgObjects = new BGObjects(xmax,ymax);
//                objs.add(bgObjects);
//            }
//
//        }

        for(BGObjects o : objs){

            o.objmove();
        }
        for(Obstacles o : obst){

            o.move();
            if(collide(o)){
                b=false;
                ballacc = 3;
            }
            if(!collide(o) && isRight(o) && b){
                score = score +1;
                o.update();

            }

        }

        update();

        invalidate();
        paint.setColor(Color.WHITE);
        paint.setTextSize(32);

        canvas.drawText("Score:"+String.valueOf(score),600,1200,paint);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(b){
            ballspeedy = -20;
            return true;}
        else {return false;}

    }

    public int getScore() {
        return score;
    }

    public boolean isRight(Obstacles o){

        if(Math.abs(ballcx-ballrad)>o.getRight()){

            return true;
        }

        else return false;

    }

    public boolean collide(Obstacles o){

        float closex,closey,closey1;

        closex = Math.max(o.getLeft(),Math.min(ballcx,o.getRight()));
        closey = Math.max(o.getTop(),Math.min(ballcy,o.getBottom()));
        closey1 = Math.max(o.getTop2(),Math.min(ballcy,o.getBottom2()));


        float distx = (closex-ballcx)*(closex-ballcx);
        float disty = (closey-ballcy)*(closey-ballcy);
        float disty1 = (closey1-ballcy)*(closey1-ballcy);
        if(distx+disty<=(ballrad*ballrad) || distx+disty1<=(ballrad*ballrad)){

            return true;

        }
        else {
            return false;
        }


    }


    public void update(){
        ballspeedy= ballacc + ballspeedy;
        ballcx = ballcx + ballspeedx;
        ballcy = ballcy + ballspeedy;
        if(ballcy + ballrad>ymax){
            ballcy = ymax- ballrad;
            ballspeedy = -ballspeedy * e;
        }else if(ballcy - ballrad<ymin){

            ballcy =ymin +ballrad;
            ballspeedy = - ballspeedy * e;

        }



    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        xmax = w-1;
        ymax = h-1;
    }


}
