package Driver;


import Rhythm.*;
import Scorer.Scorer;


/*
    This class drives the game.
 */
public class Driver {

    private final int bpm;
    private final int barDurationInMilliSecs;

    private final int LOWER_BOUND;
    private final int UPPER_BOUND;

    Rhythm sampleRhythm;

    public Driver(int bpm, int lowerBound, int upperBound, Rhythm sampleRhythm){
        this.bpm = bpm;
        this.LOWER_BOUND = lowerBound;
        this.UPPER_BOUND = upperBound;
        this.sampleRhythm = sampleRhythm;
        this.barDurationInMilliSecs = (int)((60 / (float)bpm) * 1000 * 4);
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
