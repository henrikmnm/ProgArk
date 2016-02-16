package com.example.henrikmnm.mygameofpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import sheep.collision.CollisionListener;
import sheep.game.Sprite;

/**
 * Created by henrikmnm on 08.02.2016.
 */
public class PongPad extends Sprite{

    private Paint p;

    private static final int Width = 80;
    private static final int Height = 400;

    public PongPad(float x, float y){

        p = new Paint();

        setPosition(x,y);

        p.setColor(Color.WHITE);

        super.setShape(Height, Width);
    }

    public void draw(Canvas cnv){
        super.draw(cnv);
        cnv.drawRect(getX(), getY(), getX()+Height, getY()+Width, p);
    }

    public void update(float dt){
        super.update(dt);
    }
}
