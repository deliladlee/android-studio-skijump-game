package com.example.patrick.skijump;

/**
 * Fence object class written by Delila Lee
 * Fences are jumpable but do not move
 */

public class Fence extends Obstacle {

    //Fence constructor uses the Obstacle constructor
    public Fence(int lft, int tp, int rght, int bttm, long strttm){
        super(lft, tp, rght, bttm, strttm, true, false);
    }

    //Return the id of the Fence image
    public int getDrawable() {
        return R.drawable.fence;
    }
}
