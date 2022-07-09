package Driver;


import Rhythm.*;
import Scorer.Scorer;


/*
    This class drives the game.
 */
public class Driver {

    private int bpm = 100; // default
    private final int barDurationInMilliSecs = (int)((60 / (float)bpm) * 1000 * 4);

    private final int LOWER_BOUND;
    private final int UPPER_BOUND;

    Rhythm sampleRhythm;

    public Driver(int bpm, int lowerBound, int upperBound, Rhythm sampleRhythm){
        // bpm = 0 breaks the application
        if(bpm > 0){
            this.bpm = bpm;
        }
        this.LOWER_BOUND = lowerBound;
        this.UPPER_BOUND = upperBound;
        this.sampleRhythm = sampleRhythm;
    }

    public int getBpm() {
        return bpm;
    }

    public int getBarDurationInMilliSecs() {
        return barDurationInMilliSecs;
    }

    public int getLOWER_BOUND() {
        return LOWER_BOUND;
    }

    public int getUPPER_BOUND() {
        return UPPER_BOUND;
    }

    public Rhythm getSampleRhythm() {
        return sampleRhythm;
    }



}
