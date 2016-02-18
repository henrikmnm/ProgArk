package com.example.henrikmnm.mygameofpong;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import sheep.game.Game;

public class Main extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Game game = new Game(this, null);

        DisplayMetrics DM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(DM);

        Constants.windowheight = DM.heightPixels;
        Constants.windowwidth = DM.widthPixels;


        game.pushState(GameController.getInstance());

        setContentView(game);

    }

}
