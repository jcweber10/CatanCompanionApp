package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dan97w on 6/7/2017.
 */

public class Cities extends Base {
    private ImageView eventView;
    private  TextView barbDistance;
    private boolean checkForBlack;
    private Button alchemistButton;
    private int lastEvent,currentEvent;
    private Timer eventTimer;
    private List<Integer> eventImages;
    Random r;
    MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities_activity);
        GameModel.setGameType("Cities");
        redView = (ImageView) findViewById(R.id.redViewCities);
        yellowView = (ImageView) findViewById(R.id.yellowViewCities);
        eventView= (ImageView) findViewById(R.id.eventView);
        barbDistance= (TextView)findViewById(R.id.barbTracker);
        r = new Random();
        turnTime = (Chronometer) findViewById(R.id.turnTimer);
        turnTimeText = (TextView) findViewById(R.id.turn);
        gameTime = (Chronometer) findViewById(R.id.gameTimer);
        gameTimeText = (TextView) findViewById(R.id.game);
        alchemistButton = (Button)findViewById(R.id.alchemist);
        alchemistButton.setClickable(false);
        eventImages = new ArrayList<Integer>();
        eventImages.add(R.drawable.eventyellow);
        eventImages.add(R.drawable.eventblack);
        eventImages.add(R.drawable.eventblue);
        eventImages.add(R.drawable.eventgreen);
        sound = MediaPlayer.create(Cities.this,R.raw.jaws);
        gameTime.start();

    }

    public void updateView(View v) throws InterruptedException{
        final View view = v;
        turnTimeText.setText("Player " + (GameModel.getNumRolls()%GameModel.getNumPlayers()+1));
        turnTime.setBase(SystemClock.elapsedRealtime());
        turnTime.start();
        //Displays the barbarian distance to be 7 as soon as the third round starts
        if(GameModel.getNumRound()==3&&barbDistance.getText().length()==0){
            barbDistance.setText("The barbarians are " + 7 + " spaces away.");
        }
        super.updateView(v);
        //Displays the event dice
        animationTime = 20;
        int lengthOfAnimation = r.nextInt(700) + 1000;
        if((GameModel.getNumRolls()-1)/GameModel.getNumPlayers()>2) {
            eventTimer = new Timer(lengthOfAnimation, animationTime) {
                public void onTick(long millisUntilFinished) {
                    currentEvent = r.nextInt(4);
                    while (currentEvent == lastEvent) {
                        currentEvent = r.nextInt(4);
                    }
                    eventView.setImageResource(eventImages.get(currentEvent));
                    lastEvent = currentEvent;
                    animationTime += 50;
                    eventTimer.changeInterval(animationTime);
                }

                //Sets the event die to the final image
                public void onFinish() {
                    //barbDistance.setText("The barbarians are 7 spaces away.");
                    switch (GameModel.getEvent()) {
                        case "Black":
                            //Sets die to be black
                            eventView.setImageResource(R.drawable.eventblack);
                            //Plays the sound and updates the barbDistance
                            if(GameModel.getBlackEvent()%7!=0){
                                sound.start();
                            }

                            //Launches barbarians at the appropriate time
                            if (GameModel.getBlackEvent() % 7 == 0 && GameModel.getBlackEvent() != 0&&GameModel.getCheckForBlack()){
                                launchBarbarians(view);
                                GameModel.setCheckForBlack(false);
                                barbDistance.setText("The barbarians are " + 7 + " spaces away.");
                            }
                            break;
                        case "Green":
                            eventView.setImageResource(R.drawable.eventgreen);
                            break;
                        case "Yellow":
                            eventView.setImageResource(R.drawable.eventyellow);
                            break;
                        case "Blue":
                            eventView.setImageResource(R.drawable.eventblue);
                            break;
                    }
                    //Sets text for the barbarian distance
                    if (7 - GameModel.getBlackEvent()% 7 == 1) {
                        barbDistance.setText("The barbarians are 1 space away.");
                    } else {//if (GameModel.getBlackEvent() % 7 != 0) {
                        barbDistance.setText("The barbarians are " + (7 - GameModel.getBlackEvent() % 7) + " spaces away.");
                    }
                }

            }.start();
        }
        GameModel.setAlchemistRoll(false);
        if(((GameModel.getNumRolls()+1)/GameModel.getNumPlayers())==4){
            alchemistButton.setClickable(true);
        }
    }



    public void launchBarbarians(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this, Barbarians.class);
        startActivity(i);
    }

    public void launchAlchemist(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this,Alchemist.class);
        startActivityForResult(i,1);
    }

    @Override
    public void reroll(View v) throws InterruptedException{
        if(GameModel.getNumRolls()>0) {
            GameModel.reroll();
            this.updateView(v);
        }
    }


    //Sets the images once the alchemist class closes
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){

        if(GameModel.getAlchemistRoll()) {
            try {
                updateView(this.getWindow().getDecorView().getRootView());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
