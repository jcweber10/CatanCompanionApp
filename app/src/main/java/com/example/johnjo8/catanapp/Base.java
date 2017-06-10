package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;


/**
 * Created by johnjo8 on 6/6/2017.
 */

public class Base extends Activity{
    private TextView redText,yellowText,totalText,turnTimeText,gameTimeText;
    Chronometer turnTime,gameTime;
    protected ImageView redView,yellowView;

    Random r;
    Random y;
    int lastRed;
    int currentRed;
    int lastYellow;
    int currentYellow;
    int animationTime;
    int yellowAnimationTime;
    int playerNumber;
    Timer redTimer;
    Timer yellowTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        GameModel.setGameType("Base");
        redView = (ImageView) findViewById(R.id.redViewBase);
        yellowView = (ImageView) findViewById(R.id.yellowViewBase);
        r = new Random();
        y = new Random();
        turnTime = (Chronometer) findViewById(R.id.turnTimer);
        turnTimeText = (TextView) findViewById(R.id.turn);
        gameTime = (Chronometer) findViewById(R.id.gameTimer);
        gameTimeText = (TextView) findViewById(R.id.game);
        playerNumber=0;
        gameTime.start();


    }

    public void launchStats(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this, Stats.class);
        startActivity(i);
    }

    public void launchRollsLog(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this, RollsLog.class);
        startActivity(i);
    }

    public void updateView(View v) throws InterruptedException {
        turnTimeText.setText("Player " + (GameModel.getNumRolls()%GameModel.getNumPlayers()+1));
        turnTime.setBase(SystemClock.elapsedRealtime());
        turnTime.start();
        GameModel.generateRoll();
        //Rerolls a 7 in the first 2 rounds
        if(GameModel.getNumRound()>0&&GameModel.getNumRound()<3) {
            while (GameModel.getRollTotal() == 7) {
                GameModel.undo();
                GameModel.generateRoll();
                Log.v("debug","A 7 was rolled in round 1 or 2");
            }
        }
        final List<Integer> redImages = new ArrayList<Integer>();
        redImages.add(R.drawable.red1);
        redImages.add(R.drawable.red2);
        redImages.add(R.drawable.red3);
        redImages.add(R.drawable.red4);
        redImages.add(R.drawable.red5);
        redImages.add(R.drawable.red6);
        animationTime = 20;

          //  for(int i = 0;i<100;i++) {
               redTimer= new Timer(r.nextInt(1500)+1000, animationTime) {

                   //int x = r.nextInt(6);

                    public void onTick(long millisUntilFinished) {
                        currentRed = r.nextInt(6);
                        if(currentRed!=lastRed) {
                            redView.setImageResource(redImages.get(currentRed));
                        } else{
                            redView.setImageResource(redImages.get(r.nextInt(6)));
                        }
                        lastRed = currentRed;
//                        x++;
//                        if(x==6){
//                            x=0;
//                        }
                        animationTime+=50;
                        redTimer.changeInterval(animationTime);
                    }

                    public void onFinish() {
                        switch (GameModel.getRedDie()){
                            case 1: redView.setImageResource(R.drawable.red1);
                                break;
                            case 2: redView.setImageResource(R.drawable.red2);
                                break;
                            case 3: redView.setImageResource(R.drawable.red3);
                                break;
                            case 4: redView.setImageResource(R.drawable.red4);
                                break;
                            case 5: redView.setImageResource(R.drawable.red5);
                                break;
                            case 6: redView.setImageResource(R.drawable.red6);
                                break;
                        }
                    }

                }.start();
            //}

            final List<Integer> yellowImages = new ArrayList<Integer>();
        yellowImages.add(R.drawable.yellow1);
        yellowImages.add(R.drawable.yellow2);
        yellowImages.add(R.drawable.yellow3);
        yellowImages.add(R.drawable.yellow4);
        yellowImages.add(R.drawable.yellow5);
        yellowImages.add(R.drawable.yellow6);
       yellowAnimationTime = 20;

        //  for(int i = 0;i<100;i++) {
        yellowTimer= new Timer(r.nextInt(1500)+1000, yellowAnimationTime) {

            //int x = r.nextInt(6);
            int x = 0;
            public void onTick(long millisUntilFinished) {
                currentYellow = y.nextInt(6);
                if(currentYellow!=lastYellow) {
                    yellowView.setImageResource(yellowImages.get(currentYellow));
                } else{
                    yellowView.setImageResource(yellowImages.get(y.nextInt(6)));
                }
                lastRed = currentRed;
//                        x++;
//                        if(x==6){
//                            x=0;
//                        }
                yellowAnimationTime+=50;
                yellowTimer.changeInterval(yellowAnimationTime);
            }

            public void onFinish() {
                    switch (GameModel.getYellowDie()){
                        case 1: yellowView.setImageResource(R.drawable.yellow1);
                            break;
                        case 2: yellowView.setImageResource(R.drawable.yellow2);
                            break;
                        case 3: yellowView.setImageResource(R.drawable.yellow3);
                            break;
                        case 4: yellowView.setImageResource(R.drawable.yellow4);
                            break;
                        case 5: yellowView.setImageResource(R.drawable.yellow5);
                            break;
                        case 6: yellowView.setImageResource(R.drawable.yellow6);
                            break;
                    }
                }

            }.start();
       // }




    }


    @Override
    public void onBackPressed(){
    }

    public void endGame(View v){
        Log.v("TAG", "swapped new activity");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        GameModel.resetValues();
        startActivity(intent);
    }
}
