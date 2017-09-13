package com.example.johnjo8.catanapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by johnjo8 on 6/5/2017.
 */

public class GameModel {
    private static int redDie, yellowDie,rollTotal,numPlayers,
            numRound,numRolls,blackEvent,blueEvent,greenEvent,yellowEvent;
    private static int[] numStats;
    private static String stats,event,gameType;
    private static List<String> turnStringList;
    private static boolean checkForBlack,alchemistRoll;
    private static Random random;


    public GameModel(){
        numStats = new int[11];
        for(int i=0;i<numStats.length;i++){
            numStats[i]=0;
        }
        turnStringList = new ArrayList<String>();
        numRound=0;
        numRolls=0;
        event="";
        checkForBlack=true;
        alchemistRoll=false;
        random= new Random();
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

    public static int getBlackEvent(){return blackEvent;}

    public static int getYellowEvent(){return yellowEvent;}

    public static int getGreenEvent(){return greenEvent;}

    public static int getBlueEvent(){return blueEvent;}

    public static int getNumRound(){
        return numRound;
    }

    public static int getNumRolls(){return numRolls;}

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

    public static void incrementRolls(){numRolls++;}

    public static void setGameType(String type){
        gameType=type;
    }

    public static List<String> getTurnStringList(){
        return turnStringList;
    }

    public static void resetValues(){blackEvent=0;yellowEvent=0;blueEvent=0;greenEvent=0;}

    public static void setAlchemistRoll(boolean isAlchemist){alchemistRoll=isAlchemist;}

    public static boolean getCheckForBlack(){return checkForBlack;}

    public static boolean getAlchemistRoll(){return alchemistRoll;}

    public static void setCheckForBlack(boolean bool){checkForBlack=bool;}

    public static void setRedDie(int red){redDie = red;}

    public static void setYellowDie(int yellow){yellowDie = yellow;}


    public static void populateStats(){
        stats="";
        for(int i=0;i<numStats.length;i++){
            for(int j=0;j<numStats[i];j++){
                stats+="|";
            }
            stats+="\n";
        }

        if(gameType.equals("Cities")){
            for(int i=0;i<blackEvent;i++){
                stats+="|";
            }
            stats+="\n";
            for(int i=0;i<yellowEvent;i++) {
                stats += "|";
            }
            stats+="\n";
            for(int i=0;i<blueEvent;i++){
                stats+="|";
            }
            stats+="\n";
            for(int i=0;i<greenEvent;i++){
                stats+="|";
            }
        }
    }

    public static void generateRoll(){
        //Generates random numbers if it is not the alchemist roll
        if(!alchemistRoll) {
            redDie = random.nextInt(6) + 1;
            yellowDie = random.nextInt(6) + 1;
        }
        rollTotal = redDie + yellowDie;
        //Generates the event colors
        int eventNum= random.nextInt(6);
        if(numRound>2) {
            switch (eventNum) {
                case 0:
                case 1:
                case 2:
                    event = "Black";
                    blackEvent++;
                    checkForBlack=true;
                    break;
                case 3:
                    event = "Green";
                    greenEvent++;
                    break;
                case 4:
                    event = "Yellow";
                    yellowEvent++;
                    break;
                case 5:
                    event = "Blue";
                    blueEvent++;
                    break;
            }
        }
        //Generates and adds the turn to the list of rolls
        String turnString ="";
        switch (gameType){
            case "Base":
                turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + rollTotal;
                break;
            case "Cities":
                if(numRound<3){
                    turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + rollTotal;
                } else if(event.equals("Black")){
                    turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + "Black " + rollTotal;
                } else {
                    turnString = "Player " + ((numRolls % numPlayers) + 1)+": " + rollTotal +
                            " with a " + event +" " + redDie;
                }
        }

        // Does not track rolls in the 0 round
        if(numRound!=0) {
            numStats[rollTotal - 2]++;
            turnStringList.add(turnString);
        }

        //Rerolls a 7 in rounds 1 and 2
        if(numRound>0&&numRound<3) {
            while (rollTotal == 7) {
                numStats[5]--;
                turnStringList.remove(turnStringList.size()-1);
                GameModel.generateRoll();
                Log.v("debug","A 7 was rolled in round 1 or 2");
            }
        }
    }

    public static void rollTurn(){
        generateRoll();
        numRolls++;
        if (numRolls % numPlayers == 0) {
            numRound++;
        }
    }

    public static void reroll(){
        numRolls--;
        if(numRolls%numPlayers==numPlayers-1){
            numRound--;
        }
        if(numRound>0) {
            numStats[rollTotal - 2]--;
            turnStringList.remove(turnStringList.size() - 1);
        }
        if(gameType.equals("Cities")&&numRound>2){
            switch(event){
                case "Black":
                    blackEvent--;
                    break;
                case "Blue":
                    blueEvent--;
                    break;
                case "Green":
                    greenEvent--;
                    break;
                case "Yellow":
                    yellowEvent--;
                    break;
            }
        }

    }

}
