package com.example.henrikmnm.mygameofpong;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import java.util.ArrayList;
import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.input.TouchListener;

/**
 * Created by henrikmnm on 08.02.2016.
 */
public class GameController extends sheep.game.State implements TouchListener, CollisionListener{

    private PongPad playerPad;
    private PongPad CPUPad;
    private int padHits = 0;
    private Wall leftWall;
    private Wall rightWall;
    private CollisionLayer collisionLayer;
    private Ball ball;
    private int CPUScore = 0;
    private Paint p;
    private Paint paint;
    private Paint scorePaint;
    private ArrayList<Sprite> sprites;
    private int playerScore = 0;

    public GameController(){

        collisionLayer = new CollisionLayer();

        ball = new Ball(Constants.windowwidth/2, Constants.windowheight/2);

        ball.setSpeed(-70, -300);

        p = new Paint();

        paint = new Paint();

        scorePaint = new Paint();

        scorePaint.setColor(Color.WHITE);

        scorePaint.setTextSize(150);

        p.setColor(Color.WHITE);

        playerPad = new PongPad(Constants.windowwidth/2, 10);

        CPUPad = new PongPad(Constants.windowwidth/2, Constants.windowheight-150);

        leftWall = new Wall(12, 0);

        rightWall = new Wall(Constants.windowwidth-12, 0);


        sprites = new ArrayList<>();

        sprites.add(playerPad);
        sprites.add(CPUPad);
        sprites.add(leftWall);
        sprites.add(rightWall);
        sprites.add(ball);

        for(Sprite sprite: sprites){
            collisionLayer.addSprite(sprite);
            sprite.addCollisionListener(this);
        }
    }

    public void draw(Canvas cnv){
        cnv.drawColor(Color.BLACK);

        collisionLayer.draw(cnv, null);

        cnv.drawRect(0, Constants.windowheight / 2 + 5, Constants.windowwidth, Constants.windowheight / 2 - 5, p);

        for (Sprite sprite: sprites){
            sprite.draw(cnv);
        }

        cnv.save();
        cnv.rotate(90);
        cnv.drawText(this.playerScore+"      "+this.CPUScore, Constants.windowwidth/2+230, -Constants.windowwidth/2-400,scorePaint);
        cnv.restore();

    }

    public void update(float dt){

        collisionLayer.update(dt);

        for (Sprite sprite: sprites){
            sprite.update(dt);
        }

        if(ball.getY()-30 <= 0){
            this.CPUScore++;
            ball.respawn(true);
        }else if (ball.getY()+30 >= Constants.windowheight){
            this.playerScore++;
            ball.respawn(false);
        }

        if(CPUScore == 5 || playerScore == 5){
            CPUScore = 0;
            playerScore = 0;
        }

        if(padHits == 7){
            ball.setSpeed(ball.getSpeed().getMultiplied(1.5f));
            padHits = 0;
        }
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {


        if(event.getY() < Constants.windowheight/2){

            playerPad.setPosition(event.getX()-200, playerPad.getY());

        }else{

            CPUPad.setPosition(event.getX()-200, CPUPad.getY());
        }

        return super.onTouchMove(event);
    }

    @Override
    public boolean onTouchDown(MotionEvent event) {

        if(event.getY() < Constants.windowheight/2){

            playerPad.setPosition(event.getX()-200, playerPad.getY());

        }else{

            CPUPad.setPosition(event.getX()-200, CPUPad.getY());

        }

        return super.onTouchDown(event);
    }

    @Override
    public void collided(Sprite sprite, Sprite sprite1) {

        if(sprite instanceof Wall) {

            if (sprite1 instanceof Ball) {

                sprite1.setSpeed(-sprite1.getSpeed().getX(), sprite1.getSpeed().getY());
            }

        }
        else if (sprite instanceof PongPad){

            if(sprite1 instanceof Ball){
                padHits++;
                sprite1.setSpeed(sprite1.getSpeed().getX(), -sprite1.getSpeed().getY());

            }

        }

    }
}
