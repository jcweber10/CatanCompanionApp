package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.logging.Handler;


/**
 * Created by johnjo8 on 6/6/2017.
 */

public class Base extends Activity{
    private TextView redText,yellowText,totalText;
    private ImageView redView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        GameModel.setGameType("Base");
        redView= (ImageView) findViewById(R.id.redView);
        //redText = (TextView) findViewById(R.id.)
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
        if(GameModel.getNumRound()>0&&GameModel.getNumRound()<3) {
            while (GameModel.getRollTotal() == 7) {
                GameModel.undo();
                GameModel.generateRoll();
                Log.v("debug","A 7 was rolled in round 1 or 2");
            }
        }
        for(int i=0;i<10;i++){
            switch (GameModel.getRedDie()){
                case 1: redView.setImageResource(R.drawable.red1);
                    break;
                case 2: redView.setImageResource(R.drawable.catan);
                    break;
                case 3: redView.setImageResource(R.drawable.catanmain);
                    break;
                case 4: redView.setImageResource(R.drawable.red4);
                    break;
                default: redView.setImageResource(R.drawable.red1);
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
        startActivity(intent);
    }
}
