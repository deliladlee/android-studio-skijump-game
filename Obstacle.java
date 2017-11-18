package com.example.patrick.skijump;

/**
 * Obstacle class acts as a superclass for all obstacle classes
 * Written by Patick O'Gorman and edited by Delila Lee
 */

public class Obstacle{
    private int left;
    private int top;
    private int right;
    private int bottom;
    private long startTime;
    private boolean jumpable;
    private boolean moving;
    private boolean moveLeft;

    //Obstacle constructor sets the position, start time (at what point in the level the obstacle
    //appears), and attributes of the object
    //Written by Patrick O'Gorman and edited by Delila Lee
    public Obstacle(int lft, int tp, int rght, int bttm, long strttm, boolean isJumpable, boolean isMoving){
        left = lft;
        top = tp;
        right = rght;
        bottom = bttm;
        startTime = strttm;
        jumpable = isJumpable;
        moving = isMoving;
    }

    //Overloaded Obstacle constructor for moving obstacles
    //Also sets the position, start time, and attributes of the obstacle, but also stores the
    //direction the obstacle is moving
    //Written by Delila Lee and edited by Patrick O'Gorman
    public Obstacle(int lft, int tp, int rght, int bttm, long strttm, boolean isJumpable, boolean isMoving, boolean goLeft){
        left = lft;
        top = tp;
        right = rght;
        bottom = bttm;
        startTime = strttm;
        jumpable = isJumpable;
        moving = isMoving;
        moveLeft = goLeft;
    }

    //If the game has started, returns true. Otherwise returns false
    //Written by Patrick O'Gorman
    public boolean hasStarted(long gameTime){
        if(startTime < gameTime){
            return true;
        }
        return false;
    }

    //If the game is done, return true. Otherwise return false
    //Written by Patrick O'Gorman
    private boolean isComplete(long gameTime, int screenHeight){
        if(startTime + screenHeight < gameTime){
            return true;
        }
        return false;
    }

    //Checks for collisions between the skier and an obstacle
    //Written by Patrick O'Gorman and edited by Delila Lee
    public boolean isCollision(int l, int t, int r, int b,
                               long gameTime, int screenHeight, int jumpHeight){
        boolean collision = false;

        if (hasStarted(gameTime) && !isComplete(gameTime, screenHeight) &&
                ((jumpHeight > 0 && !jumpable) || (jumpHeight <= 0)) &&
                ((l > left && l < right) || (r > left && r < right)) &&
                ((t > top && t < bottom) || (b > top && b < bottom))){
            collision = true;
        }

        return collision;
    }

    //Used to move obstacles up the screen as the skier moves down
    //Also used to move moving obstacles across the screen
    //Written by Patrick O'Gorman and edited by Delila Lee
    public void increment(){
        top--;
        bottom--;
        if(moving) {
            if(moveLeft) {
                left--;
                right--;
            }
            else {
                left++;
                right++;
            }
        }
    }

    //Return the id of the default Obstacle image
    //This function is overwritten in all Obstacle subclasses
    //Written by Delila Lee
    public int getDrawable() {
        return R.drawable.obstacle;
    }

    //Getter methods for Obstacle variables
    public int getL(){return left;}
    public int getT(){return top;}
    public int getR(){return right;}
    public int getB(){return bottom;}
    public long getStartTime(){return startTime;}
    public boolean getJumpable(){return jumpable;}
    public boolean getMoving(){return moving;}
}
