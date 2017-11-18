package com.example.patrick.skijump;

/**
 * Snowman object class written by Delila Lee
 * Snowmen are not jumpable and do not move
 */

public class Snowman extends Obstacle {

    //Snowman constructor uses the Obstacle constructor
    public Snowman(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Snowman image
    public int getDrawable() {
        return R.drawable.snowman;
    }
}
