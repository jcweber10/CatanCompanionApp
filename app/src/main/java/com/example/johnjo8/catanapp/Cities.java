package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by dan97w on 6/7/2017.
 */

public class Cities extends Base {
    private ImageView eventView;
    private boolean checkForBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_activity);
        GameModel.setGameType("Cities");
        redView = (ImageView) findViewById(R.id.redViewCities);
        yellowView = (ImageView) findViewById(R.id.yellowViewCities);
        eventView= (ImageView) findViewById(R.id.eventView);
    }

    public void setTexts(View v){
        GameModel.generateRoll();
        //Rerolls 7s in first 2 rounds
        if(GameModel.getNumRound()>0&&GameModel.getNumRound()<3) {
            while (GameModel.getRollTotal() == 7) {
                GameModel.undo();
                GameModel.generateRoll();
                Log.v("debug","A 7 was rolled in round 1 or 2");
            }
        }
        if (GameModel.getBlackEvent() % 7 == 0 && GameModel.getBlackEvent() != 0&&GameModel.getCheckForBlack()){
            launchBarbarians(v);
            GameModel.setCheckForBlack(false);
        }
    }

    public void roll1000(View v){
        for(int i=0;i<1000;i++){
            setTexts(v);
        }
    }

    public void launchBarbarians(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this, Barbarians.class);
        startActivity(i);
    }


}
