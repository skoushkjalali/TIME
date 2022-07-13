package com.time.game.Model.Level;

import com.time.game.Model.Rhythm.Rhythm;


/*
    This class represents a level of a rhythm at a prescribed bpm.
 */
public class Level {

    private static int bpm = 100; // default
    private final int barDurationInMilliSecs = (int)((60 / (float)bpm) * 1000 * 4);

    private int LOWER_BOUND = 40;
    private int UPPER_BOUND = 750;

    private static int levelNumber = 1; // default

    private static int lastScore = 0;

    Rhythm sampleRhythm;

    public Level(Rhythm sampleRhythm){
        // bpm = 0 breaks the application
        this.sampleRhythm = sampleRhythm;
    }

    public static int getLastScore(){
        return Level.lastScore;
    }

    public static void setLastScore(int score){
        Level.lastScore = score;
    }

    public static int getLevelNumber() {
        return levelNumber;
    }

    public static void setLevelNumber(int levelNumber) {
        Level.levelNumber = levelNumber;
    }

    public Rhythm getSampleRhythm() {
        return sampleRhythm;
    }

    public int getBpm() {
        return bpm;
    }

    public static void setBpm(int bpm){
        Level.bpm = bpm;
    }

    public int getBarDurationInMilliSecs() {
        return barDurationInMilliSecs;
    }

    public void setBounds(int LOWER_BOUND, int UPPER_BOUND){
        this.LOWER_BOUND = LOWER_BOUND;
        this.UPPER_BOUND = UPPER_BOUND;
    }

    public int getLOWER_BOUND() {
        return LOWER_BOUND;
    }

    public int getUPPER_BOUND() {
        return UPPER_BOUND;
    }


}
