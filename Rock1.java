package com.example.patrick.skijump;

/**
 * Rock1 object class written by Delila Lee
 * Rock1 is not jumpable and does not move
 */

public class Rock1 extends Obstacle {

    //Rock1 constructor uses the Obstacle constructor
    public Rock1(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Rock1 image
    public int getDrawable() {
        return R.drawable.rock1;
    }
}
