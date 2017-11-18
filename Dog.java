package com.example.patrick.skijump;

/**
 * Dog object class written by Delila Lee
 * Dog is not jumpable and is moving
 */

public class Dog extends Obstacle {
    //If moveLeft is true, object is moving left, otherwise object is moving right
    private boolean moveLeft;

    //Dog constructor uses the overloaded Obstacle constructor
    public Dog(int lft, int tp, int rght, int bttm, long strttm, boolean mvLft) {
        super(lft, tp, rght, bttm, strttm, false, true, mvLft);
        moveLeft = mvLft;
    }

    //Return the id of the appropriate Dog image based on its direction of movement
    public int getDrawable() {
        if(moveLeft) {
            return R.drawable.dog_l;
        } else {
            return R.drawable.dog_r;
        }
    }
}
