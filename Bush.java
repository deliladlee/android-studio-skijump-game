package com.example.patrick.skijump;

/**
 * Bush object class written by Delila Lee
 * Bushes are not jumpable and do not move
 */

public class Bush extends Obstacle {

    //Bush constructor uses the Obstacle constructor
    public Bush(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Bush image
    public int getDrawable() {
        return R.drawable.bush;
    }
}
