package com.example.patrick.skijump;

/**
 * Tree2 object class written by Delila Lee
 * Tree2 is not jumpable and does not move
 */

public class Tree2 extends Obstacle {

    //Tree2 constructor uses the Obstacle constructor
    public Tree2(int lft, int tp, int rght, int bttm, long strttm) {
        super(lft, tp, rght, bttm, strttm, false, false);
    }

    //Return the id of the Tree2 image
    public int getDrawable() {
        return R.drawable.tree2;
    }
}
