package com.example.patrick.skijump;

/**
 * Fox object class written by Delila Lee
 * Fox is not jumpable and is moving
 */

public class Fox extends Obstacle {
    //If moveLeft is true, object is moving left, otherwise object is moving right
    private boolean moveLeft;

    //Fox constructor uses the overloaded Obstacle constructor
    public Fox(int lft, int tp, int rght, int bttm, long strttm, boolean mvLft) {
        super(lft, tp, rght, bttm, strttm, false, true, mvLft);
        moveLeft = mvLft;
    }

    //Return the id of the appropriate Fox image based on its direction of movement
    public int getDrawable() {
        if(moveLeft) {
            return R.drawable.fox_l;
        } else {
            return R.drawable.fox_r;
        }
    }
}
