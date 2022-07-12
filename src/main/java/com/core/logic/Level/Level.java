package com.core.logic.Level;

import com.core.logic.Rhythm.*;


/*
    This class represents a level of a rhythm at a prescribed bpm.
 */
public class Level {

    private int bpm = 100; // default
    private final int barDurationInMilliSecs = (int)((60 / (float)bpm) * 1000 * 4);

    private int LOWER_BOUND = 40;
    private int UPPER_BOUND = 750;

    Rhythm sampleRhythm;

    public Level(int bpm, Rhythm sampleRhythm){
        // bpm = 0 breaks the application
        if(bpm > 0){
            this.bpm = bpm;
        }

        this.sampleRhythm = sampleRhythm;
    }

    public int getBpm() {
        return bpm;
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
