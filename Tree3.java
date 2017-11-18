package com.example.patrick.skijump;

/**
 * Tree3 object class written by Delila Lee
 * Tree3 is not jumpable and does not move
 */

public class Tree3 extends Obstacle {

    //Tree3 constructor uses the Obstacle constructor
    public Tree3(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Tree3 image
    public int getDrawable() {
        return R.drawable.tree3;
    }
}
