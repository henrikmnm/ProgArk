package com.example.henrikmnm.mygameofpong;

import java.util.ArrayList;

import sheep.game.Sprite;

/**
 * Created by henrikmnm on 16.02.2016.
 */
public class GameModel {

    private PongPad player1Pad;

    private PongPad player2Pad;

    private Ball ball;

    private ArrayList<Sprite> sprites;

    private int player1Score = 0;

    private int player2Score = 0;

    private int padHits = 0;


    public GameModel(){
        sprites = new ArrayList<>();
    }

    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }


    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void incrementPlayer1(){
        player1Score++;
    }

    public void incrementPlayer2(){
        player2Score++;
    }

    public ArrayList<Sprite> getSprites(){
        return sprites;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        addSprite(ball);
        this.ball = ball;
    }

    public PongPad getPlayer1Pad() {
        return player1Pad;
    }

    public void setPlayer1Pad(PongPad player1Pad) {
        addSprite(player1Pad);
        this.player1Pad = player1Pad;
    }

    public PongPad getPlayer2Pad() {
        return player2Pad;
    }

    public void setPlayer2Pad(PongPad player2Pad) {
        addSprite(player2Pad);
        this.player2Pad = player2Pad;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }
    public void incrementPadHits(){
        padHits++;
    }

    public int getPadHits() {
        return padHits;
    }

    public void setPadHits(int padHits) {
        this.padHits = padHits;
    }
}
