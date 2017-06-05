package com.example.johnjo8.catanapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Created by johnjo8 on 6/5/2017.
 */

public class GameModel {
    static int redDie, yellowDie,rollTotal,numPlayers,numRound,numRolls;
    static int[] numStats;
    static String stats,event,gameType;
    static List<String> turnStrings;

    public GameModel(){
        numStats = new int[11];
        for(int i=0;i<numStats.length;i++){
            numStats[i]=0;
        }
        turnStrings = new ArrayList<String>();
        populateStats();
    }

    private void populateStats(){
        stats="";
        for(int i=0;i<numStats.length;i++){
            stats+= (i+2)+": " + numStats[i];
        }
    }

    public void roll(){
        Random r = new Random();
        redDie = r.nextInt(6)+1;
        yellowDie= r.nextInt(6)+1;
        rollTotal= redDie+yellowDie;
        int eventNum= r.nextInt(6);
        switch(eventNum){
            case 0:
            case 1:
            case 2: event="Black";
                break;
            case 3:event="Green";
                break;
            case 4: event="Yellow";
                break;
            case 5: event="Blue";
                break;
        }
        //----------------------------------------------//
        switch (gameType){
            case "Base":

                break;
            case "Cities":
        }




        //----------------------------------------------//
    }

}
