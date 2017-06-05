package com.example.johnjo8.catanapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnjo8 on 6/5/2017.
 */

public class GameModel  {
    static int redDie, yellowDie,rollTotal,numPlayers,numRound;
    static int[] numStats;
    static String stats;
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

}
