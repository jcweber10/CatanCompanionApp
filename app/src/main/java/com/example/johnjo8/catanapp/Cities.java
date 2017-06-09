package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dan97w on 6/7/2017.
 */

public class Cities extends Base {
    private ImageView eventView;
    private TextView barbDistance;
    private boolean checkForBlack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_activity);
        GameModel.setGameType("Cities");
        redView = (ImageView) findViewById(R.id.redViewCities);
        yellowView = (ImageView) findViewById(R.id.yellowViewCities);
        eventView= (ImageView) findViewById(R.id.eventView);
        barbDistance= (TextView)findViewById(R.id.barbTracker);
    }

    public void setTexts(View v){
        //Displays the barbarian distance to be 7 as soon as the third round starts
        if(GameModel.getNumRound()==3&&barbDistance.getText().length()==0){
            barbDistance.setText("The barbarians are " + 7 + " spaces away.");
        }
        GameModel.generateRoll();
        //Rerolls 7s in first 2 rounds
        if(GameModel.getNumRound()>0&&GameModel.getNumRound()<3) {
            while (GameModel.getRollTotal() == 7) {
                //CHECK ON THE UNDO. THE ERROR IS BECAUSE IT'S SAYING GET(-1) WHEN LENGTH IS 0

                GameModel.undo();
                GameModel.generateRoll();
                Log.v("debug","A 7 was rolled in round 1 or 2");
            }
        }
        //Launches Barbarian attack page when the 7th black is rolled
        if (GameModel.getBlackEvent() % 7 == 0 && GameModel.getBlackEvent() != 0&&GameModel.getCheckForBlack()){
            launchBarbarians(v);
            GameModel.setCheckForBlack(false);
            barbDistance.setText("The barbarians are " + 7 + " spaces away.");
        }


        //Displays the Barbarian distance
        if(GameModel.getEvent().equals("Black")){
            if (7 - GameModel.getBlackEvent()% 7 == 1) {
                barbDistance.setText("The barbarians are 1 space away.");
            } else if (GameModel.getBlackEvent() % 7 != 0) {
                barbDistance.setText("The barbarians are " + (7 - GameModel.getBlackEvent() % 7) + " spaces away.");
            }
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

    public void launchAlchemist(View v){
        Intent i = new Intent(this,Alchemist.class);
        startActivity(i);
    }

}
