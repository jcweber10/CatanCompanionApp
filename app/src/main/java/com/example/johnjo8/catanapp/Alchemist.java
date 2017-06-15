package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private TextWatcher textWatcher;
    private int redNum,yellowNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alchemist_activity);
        redRoll = (EditText) findViewById(R.id.redDie);
        yellowRoll = (EditText) findViewById(R.id.yellowDie);
        roller = (Button) findViewById(R.id.roll);
        roller.setClickable(false);
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    redNum = Integer.parseInt((redRoll.getText().toString()));
                    yellowNum =Integer.parseInt((yellowRoll.getText().toString()));
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
                if(redNum>0&&redNum<7&&yellowNum>0&&yellowNum<7){
                    roller.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        redRoll.addTextChangedListener(textWatcher);
        yellowRoll.addTextChangedListener(textWatcher);
    }
    public void returnToCities(View v){
        GameModel.setRedDie(redNum);
        GameModel.setYellowDie(yellowNum);
        GameModel.setAlchemistRoll(true);
        Intent intent = new Intent();
        intent.putExtra("result",1);
        setResult(RESULT_OK);
        finish();

    }
}
