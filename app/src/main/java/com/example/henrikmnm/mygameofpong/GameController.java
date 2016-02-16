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

    private GameModel model;
    private CollisionLayer collisionLayer;
    private Paint p;
    private Paint scorePaint;
    private ArrayList<Sprite> sprites;

    public GameController(){

        model = new GameModel();

        model.addSprite(new Wall(12, 0));

        model.addSprite(new Wall(Constants.windowwidth-12, 0));

        collisionLayer = new CollisionLayer();

        model.setBall(new Ball(Constants.windowwidth / 2, Constants.windowheight / 2));

        model.getBall().setSpeed(-70, -300);

        p = new Paint();

        p.setColor(Color.WHITE);

        scorePaint = new Paint();

        scorePaint.setColor(Color.WHITE);

        scorePaint.setTextSize(150);

        model.setPlayer1Pad(new PongPad(Constants.windowwidth/2, 10));

        model.setPlayer2Pad(new PongPad(Constants.windowwidth/2, Constants.windowheight-150));

        sprites = model.getSprites();

        for (int i = 0; i < sprites.size(); i++) {
            model.getSprites().get(i).addCollisionListener(this);
            collisionLayer.addSprite(model.getSprites().get(i));
        }
    }

    public void draw(Canvas cnv){
        cnv.drawColor(Color.BLACK);

        collisionLayer.draw(cnv, null);

        cnv.drawRect(0, Constants.windowheight / 2 + 5, Constants.windowwidth, Constants.windowheight / 2 - 5, p);

        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).draw(cnv);
        }

        cnv.save();
        cnv.rotate(90);
        cnv.drawText(model.getPlayer1Score()+"      "+model.getPlayer2Score(), Constants.windowwidth/2+230, -Constants.windowwidth/2-400,scorePaint);
        cnv.restore();

    }

    public void update(float dt){

        collisionLayer.update(dt);

        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).update(dt);
        }

        if(model.getBall().getY()-30 <= 0){
            model.incrementPlayer2();
            model.getBall().respawn(true);
        }else if (model.getBall().getY()+30 >= Constants.windowheight){
            model.incrementPlayer1();
            model.getBall().respawn(false);
        }

        if(model.getPlayer1Score() == 5 || model.getPlayer2Score() == 5){
            model.setPlayer1Score(0);
            model.setPlayer2Score(0);
        }

        if(model.getPadHits() == 7){
            model.getBall().setSpeed(model.getBall().getSpeed().getMultiplied(1.5f));
            model.setPadHits(0);
        }
    }

    @Override
    public boolean onTouchMove(MotionEvent event) {


        if(event.getY() < Constants.windowheight/2){

            model.getPlayer1Pad().setPosition(event.getX() - 200, model.getPlayer1Pad().getY());

        }else{

            model.getPlayer2Pad().setPosition(event.getX() - 200, model.getPlayer2Pad().getY());
        }

        return super.onTouchMove(event);
    }

    @Override
    public boolean onTouchDown(MotionEvent event) {

        if(event.getY() < Constants.windowheight/2){

            model.getPlayer1Pad().setPosition(event.getX() - 200, model.getPlayer1Pad().getY());

        }else{

            model.getPlayer2Pad().setPosition(event.getX() - 200, model.getPlayer2Pad().getY());

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
                model.incrementPadHits();
                sprite1.setSpeed(sprite1.getSpeed().getX(), -sprite1.getSpeed().getY());

            }

        }

    }
}
