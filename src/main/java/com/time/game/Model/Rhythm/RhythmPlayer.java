package com.time.game.Model.Rhythm;



/*
    This class receives a ready-made rhythm object from RhythmFactory and plays it.
 */
public class RhythmPlayer {

    private double bpm;



    public RhythmPlayer(double bpm){
        this.bpm = bpm;
    }

    public void setBpm(double bpm){
        this.bpm = bpm;
    }

    public double getBpm(){
        return this.bpm;
    }


    /*
        This method takes a Rhythm object as @param r and plays the rhythm at the bpm passed to
        RhythmPlayer in the constructor.
     */
    public void playRhythm(Rhythm r){
        double[] absoluteRhythm = r.getAbsoluteRhythm(bpm);
        double[] rhythmToPlay = getIOIs(absoluteRhythm);

        double barDurationInMilliSecs = (60 / bpm) * 1000 * 4;
        long lastIOI = (long)(barDurationInMilliSecs - absoluteRhythm[absoluteRhythm.length-1]);

        // play the onsets of the rhythm
        for(double onsetDelay : rhythmToPlay){

            // if onsetDelay = 0, more than 1 nanosecond passes between line X and line Y, causing
            // the targeted nanosecond to be missed by the while loop, so we get stuck in an infinite loop.
            if(onsetDelay!=0.0) {
                long endOfWaitTime = System.nanoTime() + ((long) onsetDelay * 1_000_000);
                while (System.nanoTime() != endOfWaitTime) {
                    //wait for the prescribed number of nanoseconds
                }
            }
            BeepFactory.getBeep();
        }
        // wait for the length of time between the last onset and the end of the bar
        // if this isn't here then the first beat of the next bar will be shifted forwards by the duration of the
        // lastIOI, leading to incorrect scoring of the user input.
        long endOfWaitTime = System.nanoTime() + (lastIOI * 1_000_000);
        if(lastIOI != 0) {
            while (System.nanoTime() != endOfWaitTime) {
                //wait for the prescribed number of nanoseconds
            }
        }

    }

    /*
        This method converts the absolute onset locations passed in as @param absoluteRhythm to their respective
        absolute IOIs (inter onset intervals) in milliseconds. This is the duration between the previous onset and
        the current onset.
     */
    public double[] getIOIs(double[] absoluteRhythm){
        double[] IOIs = new double[absoluteRhythm.length];
        double prevOnset = 0.0;
        for (int i = 0; i< absoluteRhythm.length; i++){
            IOIs[i] = absoluteRhythm[i] - prevOnset;
            prevOnset = absoluteRhythm[i];
        }
        return IOIs;

    }
}
