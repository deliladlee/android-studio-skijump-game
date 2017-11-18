package com.example.patrick.skijump;

/**
 * Log object class written by Delila Lee
 * Logs are jumpable but do not move
 */

public class Log extends Obstacle {

    //Log constructor uses the Obstacle constructor
    public Log(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, true, false);
    }

    //Return the id of the Log image
    public int getDrawable() {
        return R.drawable.log;
    }
}
