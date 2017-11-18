package com.example.patrick.skijump;

/**
 * Logs object class written by Delila Lee
 * Log piles are not jumpable and do not move
 */

public class Logs extends Obstacle {

    //Logs constructor uses the Obstacle constructor
    public Logs(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Logs image
    public int getDrawable() {
        return R.drawable.logs;
    }
}
