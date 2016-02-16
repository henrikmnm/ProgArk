package com.example.henrikmnm.mygameofpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;

/**
 * Created by henrikmnm on 08.02.2016.
 */
public class Wall extends sheep.game.Sprite implements CollisionListener{

    public static final int THICKNESS = 20;
    public static final int WIDTH = Constants.windowheight-20;

    private Paint p;



    public Wall(float xPos, float yPos){

        setPosition(xPos,yPos);

        p = new Paint();

        p.setColor(Color.WHITE);

        super.setShape(THICKNESS, WIDTH);

    }


    public void draw(Canvas cnv){
        super.draw(cnv);

        cnv.drawRect(getX(), getY(), getX()+THICKNESS, getY()+WIDTH,p);

    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

    }
}
