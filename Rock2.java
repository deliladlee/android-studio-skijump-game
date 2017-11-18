package com.example.patrick.skijump;

/**
 * Rock2 object class written by Delila Lee
 * Rock2 is jumpable but does not move
 */

public class Rock2 extends Obstacle {

    //Rock2 constructor uses the Obstacle constructor
    public Rock2(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, true, false);
    }

    //Return the id of the Rock2 image
    public int getDrawable() {
        return R.drawable.rock2;
    }
}
