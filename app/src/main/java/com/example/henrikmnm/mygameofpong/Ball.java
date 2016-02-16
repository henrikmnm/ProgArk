package com.example.henrikmnm.mygameofpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.graphics.Image;

/**
 * Created by henrikmnm on 08.02.2016.
 */
public class Ball extends Sprite{

    private static final float radius = 30;

    private Paint p;


    public Ball(float x, float y){
        super();

        setPosition(x,y);

        super.setShape(60, 60);

        p = new Paint();

        p.setColor(Color.RED);
    }

    public void respawn(boolean direction){
        setPosition(Constants.windowwidth/2, Constants.windowheight/2);

        if(direction){
            setSpeed(70,300);
        }else{
            setSpeed(-70,-300);
        }
    }

    public void draw(Canvas cnv){
        super.draw(cnv);
        cnv.drawCircle(getX(), getY(), this.radius, this.p);
    }

    public void update(float dt){
        super.update(dt);
    }

}
