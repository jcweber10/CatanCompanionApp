package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by johnjo8 on 6/6/2017.
 */

public class RollsLog extends Activity {
    private ScrollView scrollView;
    private LinearLayout linearLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rolls_log_activity);
        scrollView= (ScrollView) findViewById(R.id.rollsLogScrollView);
        linearLayout=(LinearLayout)findViewById(R.id.rollsLog);
        updateRollsLog(getWindow().getDecorView());
    }

    private void updateRollsLog(View v){
        for(String turn:GameModel.getTurnStringList()){
            TextView textView = new TextView(this);
            textView.setText(turn);
            textView.setTextColor(getResources().getColor(R.color.CatanGold));
            linearLayout.addView(textView);
        }
    }


}