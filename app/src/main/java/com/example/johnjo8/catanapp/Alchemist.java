package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by dan97w on 6/8/2017.
 */

public class Alchemist extends Activity {

    EditText redRoll,yellowRoll;
    Button roller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alchemist_activity);
//        GameModel.setGameType("Cities");
        redRoll = (EditText) findViewById(R.id.redDie);
        yellowRoll = (EditText) findViewById(R.id.yellowDie);
        roller = (Button) findViewById(R.id.roll);
    }
    public void returnToCities(View v){
        GameModel.setRedDie(Integer.parseInt((redRoll.getText().toString())));
        GameModel.setYellowDie(Integer.parseInt((yellowRoll.getText().toString())));
        GameModel.setAlchemistRoll(true);
        Intent intent = new Intent();
        intent.putExtra("result",1);
        setResult(RESULT_OK);
        finish();

    }
}
