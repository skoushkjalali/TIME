package Rhythm;



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
    public void playRhythm(Rhythm r) throws InterruptedException {
        double[] absoluteRhythm = r.getAbsoluteRhythm(bpm);
        double[] rhythmToPlay = getIOIs(absoluteRhythm);

        double barDurationInMilliSecs = (60 / bpm) * 1000 * 4;
        long lastIOI = (long)(barDurationInMilliSecs - absoluteRhythm[absoluteRhythm.length-1]);


        // play the onsets of the rhythm
        for(double onsetDelay : rhythmToPlay){
            Thread.sleep((long)onsetDelay);
            BeepFactory.getBeep();
        }
        // wait for the length of time between the last onset and the end of the bar
        Thread.sleep(lastIOI);

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
