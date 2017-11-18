package com.example.patrick.skijump;

/**
 * Game class manages the game specific variables such as time, winning, and crashing
 * Written by Patrick O'Gorman
 */

public class Game{
    long gameTime;
    long endTime;
    boolean crashed; //skier crashed

    //Game constructor class
    //Written by Patrick O'Gorman
    public Game(long endT){
        gameTime = 0;
        endTime = endT;
        crashed = false;
    }

    //Getter and setter methods for Game object variables
    //Written by Patrick O'Gorman
    public boolean gameStarted(){return gameTime > 0;}
    public boolean gameOver(){return skierWins() || getCrashed();}
    public boolean skierWins(){return gameTime >= endTime;}
    public boolean getCrashed(){return crashed;}
    public void setCrashed(){crashed = true;}
    public void incrementTime(){if(!gameOver()) gameTime++;}
    public long getGameTime(){return gameTime;}
    public long getEndTime(){return endTime;}
    public long score(){return ((gameTime * 1000) / endTime);}
}
