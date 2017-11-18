package com.example.patrick.skijump;

/**
 * Rock3 object class written by Delila Lee
 * Rock3 is not jumpable and does not move
 */

public class Rock3 extends Obstacle {

    //Rock3 constructor uses the Obstacle constructor
    public Rock3(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Rock3 image
    public int getDrawable() {
        return R.drawable.rock3;
    }
}
