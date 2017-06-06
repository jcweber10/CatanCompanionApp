package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by johnjo8 on 6/6/2017.
 */

public class Base extends Activity{
    private TextView redText,yellowText,totalText;
    private ImageView redView;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        GameModel.setGameType("Base");
        redView= (ImageView) findViewById(R.id.redView);
        timer = new Timer();
        //redText = (TextView) findViewById(R.id.)
    }

    public void launchStats(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this, Stats.class);
        startActivity(i);
    }

    public void setTexts(View v){
        GameModel.generateRoll();
        //-------------------------------------------
        for(int i=0;i<10;i++){
            GameModel.generateRoll();
//            switch (GameModel.getRedDie()){
//                case 1: redView.setImageResource(R.drawable.red1);
//                    break;
//                case 2: redView.setImageResource(R.drawable.catan);
//                    break;
//                case 3: redView.setImageResource(R.drawable.catanmain);
//                    break;
//                case 4: redView.setImageResource(R.drawable.red4);
//                    break;
//            }
            redView.setImageResource(R.drawable.catanmain);
            timer.schedule(new Roll(),100*i);
            redView.setImageResource(R.drawable.red4);
        }

    }

    class Roll extends TimerTask{
        public void run(){

        }
    }
}
