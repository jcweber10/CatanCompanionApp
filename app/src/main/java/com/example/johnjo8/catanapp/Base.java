package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Handler;


/**
 * Created by johnjo8 on 6/6/2017.
 */

public class Base extends Activity{
    private TextView redText,yellowText,totalText;
    protected ImageView redView,yellowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        GameModel.setGameType("Base");
        redView = (ImageView) findViewById(R.id.redViewBase);
        yellowView = (ImageView) findViewById(R.id.yellowViewBase);

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

    public void setTexts(View v){
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
        for (int i = 0; i<10;i++) {
            new CountDownTimer(500, 500) {
                Random r = new Random();

                public void onTick(long millisUntilFinished) {
                    redView.setImageResource(redImages.get(r.nextInt(6)));
                }

                public void onFinish() {
                    redView.setImageResource(redImages.get(3));
                }
            }.start();
        }
        for(int i=0;i<10;i++){
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
