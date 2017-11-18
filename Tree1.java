package com.example.patrick.skijump;

/**
 * Tree1 object class written by Delila Lee
 * Tree1 is not jumpable and does not move
 */

public class Tree1 extends Obstacle {

    //Tree1 constructor uses the Obstacle constructor
    public Tree1(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Tree1 image
    public int getDrawable() {
        return R.drawable.tree1;
    }
}
