package com.example.patrick.skijump;

/**
 * Rocks object class written by Delila Lee
 * Rock piles are jumpable but do not move
 */

public class Rocks extends Obstacle {

    //Rocks constructor uses the Obstacle constructor
    public Rocks(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, true, false);
    }

    //Return the id of the Rocks image
    public int getDrawable() {
        return R.drawable.rocks;
    }
}
