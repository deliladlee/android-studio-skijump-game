package com.example.patrick.skijump;

/**
 * Skier object manages all variables relating to the position of the skier in the game
 * Written by Patrick O'Gorman
 */

public class Skier {
    private int left;
    private int top;
    private int height;
    private int width;
    private int jumpHeight;

    //Skier constructor sets the skiers original position, sets jumpHeight to 0 (skier is not jumping)
    //Written by Patrick O'Gorman
    public Skier(int lft, int tp, int hght, int wdth) {
        left = lft;
        top = tp;
        height = hght;
        width = wdth;
        jumpHeight = 0;
    }

    //Getter and setter methods for Skier variables
    public int getL(){return left;}
    private void setL(int l){left = l;}
    public int getT(){return top;}
    private void setT(int t){top = t;}
    public int getH(){return height;}
    public int getW(){return width;}
    public int getR(){return left + width;}
    public int getB(){return top + height;}
    public int getJumpHeight(){return jumpHeight;}
    public void decreaseJump(){if(jumpHeight > 0) jumpHeight--;} //gradual jump descent
    public boolean jumping(){return jumpHeight > 0;} //is the skier jumping

    //Causes the skier to jump, if not already jumping
    //Written by Patrick O'Gorman
    public boolean jumpUp(){
        boolean jumped = false;

        if(jumpHeight <= 0){
            jumped = true;
            jumpHeight = 30;
        }
        return jumped;
    }

    //Update the position of the skier in the x-axis
    //Written by Patrick O'Gorman
    public void updateXPos(int screenW, int screenH, double xOrientationAngle) {
        int movAdd = 30;
        this.setL(left + (int) (movAdd * xOrientationAngle));
        if (this.getL() > screenW - this.getW()) {
            this.setL(screenW - this.getW());
        } else if (this.getL() < 0) {
            this.setL(0);
        }
    }

    //Update the position of the skier in the y-axis
    //Written by Patrick O'Gorman
    public void updateYPos(int screenW, int screenH, double yOrientationAngle) {
        int movAdd = 30;
        this.setT(this.getT() + (int) (movAdd * -yOrientationAngle));
        if (this.getT() > screenH - this.getH()) {
            this.setT(screenH - this.getH());
        } else if (this.getT() < 0) {
            this.setT(0);
        }
    }
}
