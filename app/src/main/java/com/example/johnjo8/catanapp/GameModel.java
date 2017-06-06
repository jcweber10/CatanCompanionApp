package com.example.johnjo8.catanapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by johnjo8 on 6/5/2017.
 */

public class GameModel {
    private static int redDie, yellowDie,rollTotal,numPlayers,numRound,numRolls;
    private static int[] numStats;
    private static String stats,event,gameType;
    private static List<String> turnStringList;

    public GameModel(){
        numStats = new int[11];
        for(int i=0;i<numStats.length;i++){
            numStats[i]=0;
        }
        turnStringList = new ArrayList<String>();
        populateStats();
        numRound=0;
        numRolls=0;
    }


    public static int getRedDie(){
        return redDie;
    }

    public static int getYellowDie(){
        return yellowDie;
    }

    public static int getRollTotal(){
        return redDie+yellowDie;
    }

    public static int getNumPlayers(){
        return numPlayers;
    }

    public static int[] getNumStats(){
        return numStats;
    }

    public static String getStats(){
        return stats;
    }

    public static String getEvent(){
        return event;
    }

    public static String getGameType(){
        return gameType;
    }

    public static void setNumPlayers(int players){
        numPlayers=players;
    }

    public static void setGameType(String type){
        gameType=type;
    }
    public static List<String> getTurnStringList(){
        return turnStringList;
    }




    public static void populateStats(){
        stats="";
        for(int i=0;i<numStats.length;i++){
            stats+= (i+2)+": " + numStats[i]+"\n";
        }
    }

    public static void generateRoll(){
        //Generates the dice numbers
        Random r = new Random();
        redDie = r.nextInt(6)+1;
        yellowDie= r.nextInt(6)+1;
        rollTotal= redDie+yellowDie;

        //Generates the event colors
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
        //Generates and adds the turn to the list of rolls
        String turnString ="";
        switch (gameType){
            case "Base":
                turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + rollTotal;
                break;
            case "Cities":
                if(event.equals("Black")){
                    turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + "Black " + rollTotal;
                } else {
                    turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + rollTotal + " with a " + event + redDie;
                }
        }
        turnStringList.add(turnString);
        numRolls++;
        if (numRolls % numPlayers == 0) {
            numRound++;
        }

    }

}