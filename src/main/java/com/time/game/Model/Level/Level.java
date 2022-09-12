package com.time.game.Model.Level;

import com.time.game.Model.Rhythm.Rhythm;


/*
    This class represents a level of a rhythm at a prescribed bpm.
 */

public class Level {
    private boolean userInputCaptureEnabled = false;
    private static int bpm = 100;
    private final int LOWER_BOUND = 20;
    private final int UPPER_BOUND = 250;
    private static int levelNumber = 1;
    private static int lastScore = 0;
    private final Rhythm sampleRhythm;

    public Level(Rhythm sampleRhythm){
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

    public static int getBpm() {
        return bpm;
    }

    public static void setBpm(int bpm){
        Level.bpm = bpm;
    }

    public int getBarDurationInMilliSecs() {
        return (int) ( (60 / (float) bpm) * 1000 * 4);
    }

    public int getBeatDurationInMilliSecs(){
        return getBarDurationInMilliSecs() / 4;
    }

    public int getLOWER_BOUND() {
        return LOWER_BOUND;
    }

    public int getUPPER_BOUND() {
        return UPPER_BOUND;
    }

    public boolean isUserInputCaptureEnabled() {
        return userInputCaptureEnabled;
    }

    public void setUserInputCaptureEnabled(boolean status) {
        userInputCaptureEnabled = status;
    }
}
