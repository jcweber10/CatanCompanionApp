package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        //redText = (TextView) findViewById(R.id.)
    }

    public void launchStats(View v){
        Log.v("TAG", "swapped new activity");
        Intent i = new Intent(this, Stats.class);
        startActivity(i);
    }

    public void setTexts(View v){
        GameModel.generateRoll();
        switch(GameModel.getRedDie()){
            case 1: redView.setImageResource(R.drawable.red1);
            default: redView.setImageResource(R.drawable.catan);

        }

    }
}
