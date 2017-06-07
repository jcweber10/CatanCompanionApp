package com.example.johnjo8.catanapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by johnjo8 on 6/6/2017.
 */

public class Stats extends Activity{
    private TextView stats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats_activity);
        View v = this.getWindow().getDecorView().getRootView();
        if(GameModel.getGameType().equals("Base")){
            v.setBackgroundColor(Color.parseColor("#b71f2e"));
        } else{
            v.setBackgroundColor(Color.parseColor("#008345"));
        }
        stats = (TextView)findViewById(R.id.stats);
        GameModel.populateStats();
        stats.setText(GameModel.getStats());
        stats.setTextColor(getResources().getColor(R.color.CatanGold));
    }
}
