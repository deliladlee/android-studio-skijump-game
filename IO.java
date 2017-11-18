package com.example.patrick.skijump;

import android.content.res.AssetManager;
import android.os.Environment;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.nio.channels.FileLockInterruptionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 * IO class manages all functions related to reading and writing to the file
 * Written by Delila Lee and edited by Patrick O'Gorman
 * Some functions were taken from our Contacts Manager program (Assignment 4) and
 * modified for this program
 */

public class IO {
    private ArrayList<Long> highScores;
    private String FILENAME = "highScores.txt";
    private Context myContext;

    //IO constructor reads in the list of high scores from the text file and adds them to
    //an ArrayList
    //Written by Delila Lee
    //Edited by Patrick O'Gorman
    public IO(Context context) {
        BufferedReader br = null;
        highScores = new ArrayList<Long>();
        myContext = context;

        try {
            createFileIfNeeded(context, FILENAME);
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line;

            while((line=br.readLine()) != null) {
                long score = Long.parseLong(line);
                System.out.println("===>" + score);
                highScores.add(score);
            }
            br.close();
        }
        catch (IOException e){
            System.out.println(">>>HighScores text file failed to open: " + e);
        }
    }

    //Sorts the ArrayList of high scores in descending order and prints to the file
    //Written by Delila Lee and edited by Patrick O'Gorman
    public void writeToFile(ArrayList<Long> list){
        try {
            Collections.sort(highScores, Collections.reverseOrder());
            FileOutputStream fos = myContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);

            for(Long s : list) {
                String str = s + "\n";
                fos.write(str.getBytes());
                System.out.println("<---" + s);
            }
            fos.close();
        }
        catch(Exception ex){
            System.out.println(">>>write file error:" + ex);
        }
    }

    //Checks to see if the file exists. If not, creates the file
    //Written by Patrick O'Gorman
    public void createFileIfNeeded(Context context, String filename) {
        File file = context.getFileStreamPath(filename);
        System.out.println(">>>" + filename + "-total:" + file.getTotalSpace() + "-length:" + file.length());

        if(file == null || !file.exists()) {
            System.out.println(">>>File did not exist");
            try {
                FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
                fos.close();
            } catch(Exception ex){
                System.out.println(">>>Create file error:" + ex);
            }
        }
    }

    //Get method for the ArrayList of high scores
    //Written by Delila Lee
    public ArrayList<Long> getHighScores() { return highScores; }

    //Adds the new score to the ArrayList, and writes the new list to the file
    //Written by Delila Lee
    public void add(long score) {
        highScores.add(score);
        writeToFile(highScores);
    }
}
