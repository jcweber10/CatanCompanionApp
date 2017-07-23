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
    private TextView nums,tallies,amounts;

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
        int catanGold = getResources().getColor(R.color.CatanGold);
        int[] amountsArr = GameModel.getNumStats();
        nums = (TextView)findViewById(R.id.nums);
        tallies = (TextView)findViewById(R.id.tallies);
        amounts = (TextView)findViewById(R.id.amount);
        GameModel.populateStats();
        String numsString="";
        String amtString="";
        for(int i=0;i<11;i++){
            numsString+=((i+2)+":\n");
            amtString+= amountsArr[i]+"\n";
        }
        if(!GameModel.getGameType().equals("Base")){
            numsString+="Black: "+"\n" + "Yellow: "+"\n" +"Blue: "+"\n" +"Green: ";
            amtString+=GameModel.getBlackEvent()+"\n"+ GameModel.getYellowEvent()+"\n"+GameModel.getGreenEvent()+"\n"+GameModel.getBlueEvent();
        }
        tallies.setTextColor(catanGold);
        amounts.setTextColor(catanGold);
        nums.setTextColor(catanGold);
        nums.setText(numsString);
        tallies.setText(GameModel.getStats());
        amounts.setText(amtString);

    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
