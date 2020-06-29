package com.example.project;

import android.app.Application;
import android.content.SharedPreferences;

public class SavingDataActivity extends Application {
    private static final String PREF_NAME =  "com.example.project.Points";
    private int points;
    private int prevPoints;

    //getting points.
    public int getPoints() {
        SharedPreferences pref = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        int point = pref.getInt("points",0);
        return point;
    }
    //Saving points.
    public void setPoints(int points) {
        this.points = points;
        prevPoints = getPoints();
        SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME,MODE_PRIVATE).edit();
        editor.putInt("points",prevPoints+points);
        editor.commit();
    }
}
