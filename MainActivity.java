package com.example.patrick.skijump;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;
import java.util.ArrayList;

/*
 * Main activity for the application
 * Written by Patrick O'Gorman and Delila Lee
 */

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    DrawView mDrawView = null;

    private SensorManager mSensorManager;
    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];
    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;
    private int screenW;
    private int screenH;
    private IO myIO;
    private Skier skier = null;
    private Game game = null;

    /** Called when the activity is first created. */
    //Written by Patrick O'Gorman and edited by Delila Lee
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mDrawView = new DrawView(this);
        setContentView(mDrawView);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.skilogo1);
        //skilogo1 has a clear background, skilogo2 has a white background

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        int heightAdj = 100; //this.getWindow().getDecorView().getBottom();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenW = metrics.widthPixels;
        screenH = metrics.heightPixels-heightAdj;

        myIO = new IO(MainActivity.this);
        Log.w(">>>>>>>>>>>>>>>>>>>",String.valueOf(myIO.getHighScores().size()));
        skier = new Skier(screenW/2,screenH/2,50,50);
        game = new Game(1850);
    }

    //Adds icons to the action bar
    //Written by Delila Lee
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_high_scores, menu);
        inflater.inflate(R.menu.menu_exit, menu);
        return true;
    }

    //Event handler for clicking icons in the action bar
    //Written by Delila Lee
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            //Get a list of (up to) the top ten high scores
            case R.id.high_scores:
                mDrawView.interrupt(); //Pause the game without calling onResume to avoid resetting the course
                ArrayList<Long> myList = myIO.getHighScores();
                int id = 1;
                String hs = "";
                if(myList.size() >= 10) {
                    for (int i=0; i<10; i++) {
                        hs = hs + id + ": " + myList.get(i) + "\n";
                        id++;
                    }
                } else {
                    for(int i=0; i<myList.size(); i++) {
                        hs = hs + id + ": " + myList.get(i) + "\n";
                        id++;
                    }
                }
                //Alert Box shows the top ten high scores, user must click to exit and resume game
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Top 10 High Scores")
                        .setMessage(hs)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface di, int n) {
                                mDrawView.resumeGame();
                            }
                        }).show();
                return true;
            // Exit the game
            case R.id.app_exit:
                onStop();
                finish();
                System.exit(0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Written by Patrick O'Gorman
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(game.gameOver() && event.getAction() == MotionEvent.ACTION_DOWN){
            Log.w(">>>>>>>>>>>>>>>>>>>","Touched");
            mDrawView.pause();
            mDrawView.resume();
            return true;
        }
        return false;
    }

    //Written by Patrick O'Gorman
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mAccelerometerReading, 0, mAccelerometerReading.length);
            if(event.values[2] > 15) { // Jump event
                skier.jumpUp();
            }
        }
        else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mMagnetometerReading, 0, mMagnetometerReading.length);
        }

        mSensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        mSensorManager.getOrientation(mRotationMatrix, mOrientationAngles);

        skier.updateXPos(screenW, screenH, mOrientationAngles[2]);
        skier.updateYPos(screenW, screenH, mOrientationAngles[1]);
    }

    // I've chosen to not implement this method
    public void onAccuracyChanged(Sensor arg0, int arg1)
    {

    }

    //Written by Patrick O'Gorman
    @Override
    protected void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_FASTEST);
        mDrawView.resume();
    }

    //Written by Patrick O'Gorman
    @Override
    protected void onPause()
    {
        super.onPause();
        mDrawView.pause();
    }

    //Written by Patrick O'Gorman
    @Override
    protected void onStop()
    {
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    //Written by Patrick O'Gorman and edited by Delila Lee
    public class DrawView extends SurfaceView implements Runnable
    {
        Thread thrd = null;
        SurfaceHolder hldr;
        boolean canDraw = false;
        boolean interrupted = false;
        Paint p = new Paint();

        //Written by Patrick O'Gorman
        public DrawView(Context context){
            super(context);
            hldr = getHolder();
        }

        //Written by Patrick O'Gorman
        public Obstacle[] createCourse(){
            int fenceH = 40;
            int tree1H = 100;
            int foxH = 30;
            Obstacle obs[] = {
                    new Fence(10,screenH,200,screenH+fenceH,0),
                    new Fence(screenW-210,screenH,screenW-10,screenH+fenceH,300),
                    new Fox(-60,screenH,0,screenH+foxH,400,false),
                    new Tree1(((screenW/2)-250),screenH,((screenW/2)-200),screenH+tree1H,450),
                    new Fence(((screenW/2)-100),screenH,((screenW/2)+100),screenH+fenceH,600)
            };
            return obs;
        }

        //Written by Patrick O'Gorman and edited by Delila Lee
        @Override
        public void run() {
            skier = new Skier(screenW/2,screenH/2,100,50);
            game = new Game(1850);
            Drawable skiUser = ContextCompat.getDrawable(MainActivity.this, R.drawable.skier);;
            Obstacle[] obs = createCourse();

            // Main game animation loop
            while(canDraw) {
                while (!interrupted) {
                    if (!hldr.getSurface().isValid()) {
                        continue;
                    }
                    Canvas canvas = hldr.lockCanvas();
                    canvas.drawARGB(255, 255, 255, 255); // color background to clear previous

                    // Draw obstacles
                    for (int i = 0; i < obs.length; i++) {
                        Drawable obstacle = ContextCompat.getDrawable(MainActivity.this, obs[i].getDrawable());
                        obstacle.setBounds(obs[i].getL(), obs[i].getT(), obs[i].getR(), obs[i].getB());
                        obstacle.draw(canvas);
                    }

                    // Draw player/skier
                    skier.decreaseJump();
                    if (skier.jumping()) {
                        skiUser = ContextCompat.getDrawable(MainActivity.this, R.drawable.jump);
                    } else {
                        skiUser = ContextCompat.getDrawable(MainActivity.this, R.drawable.skier);
                    }
                    skiUser.setBounds(skier.getL(), skier.getT(),
                            skier.getL() + skiUser.getIntrinsicWidth() / 2,
                            skier.getT() + skiUser.getIntrinsicHeight() / 2);
                    skiUser.draw(canvas);

                    //check for collision with objects
                    for (int i = 0; i < obs.length; i++) {
                        if (obs[i].isCollision(skier.getL(), skier.getT(), skier.getR(), skier.getB(),
                                game.getGameTime(), screenH, skier.getJumpHeight())) {
                            game.setCrashed();
                            break;
                        }
                    }

                    // print crashed on the screen
                    if (game.crashed) {
                        p.setColor(Color.RED);
                        p.setTextSize(50);
                        long score = game.score();
                        myIO.add(score);
                        int rank = myIO.getHighScores().indexOf(score) + 1;
                        p.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText("Crashed! Your Score: " + score, screenW / 2, 65, p);
                        canvas.drawText("Your Rank: " + rank, screenW / 2, 125, p);
                        p.setColor(Color.GREEN);
                        canvas.drawText("Touch to Restart", screenW / 2, 185, p);
                        hldr.unlockCanvasAndPost(canvas);
                        break;
                    }

                    // print win on the screen
                    if (game.skierWins()) {
                        long score = game.score();
                        myIO.add(score);
                        int rank = myIO.getHighScores().indexOf(score) + 1;
                        p.setColor(Color.BLACK);
                        p.setTextSize(50);
                        p.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText("Victory! Your Score: " + game.score(), screenW / 2, 65, p);
                        canvas.drawText("Your Rank: " + rank, screenW / 2, 125, p);
                        p.setColor(Color.GREEN);
                        canvas.drawText("Touch to Play Again", screenW / 2, 185, p);
                        hldr.unlockCanvasAndPost(canvas);
                        break;
                    }

                    // increment all obstacles to move them up
                    for (int i = 0; i < obs.length; i++) {
                        if (obs[i].hasStarted(game.getGameTime())) {
                            obs[i].increment();
                        }
                    }
                    game.incrementTime();
                    hldr.unlockCanvasAndPost(canvas);
                }
            }
        }

        //Pauses in the middle of a game when High Scores action bar icon is pressed
        //Written by Delila Lee
        public void interrupt(){
            interrupted = true;
        }

        //Resumes the game where it left off after viewing High Scores
        //Written by Delila Lee
        public void resumeGame(){
            interrupted = false;
        }

        //Written by Patrick O'Gorman
        public void pause(){
            canDraw = false;
            while(true){
                try{
                    thrd.join();
                    break;
                }catch(Exception ex){
                    ex.printStackTrace();
                }finally {
                    thrd = null;
                }
            }
        }

        //Written by Patrick O'Gorman
        public void resume(){
            canDraw = true;
            thrd = new Thread(this);
            thrd.start();
        }
    }
}
