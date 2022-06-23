package Rhythm;



/*
    This class receives a ready-made rhythm from Rhythm Factory and plays it.
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

    public void playRhythm(Rhythm r) throws InterruptedException {
        double[] absoluteRhythm = r.getAbsoluteRhythm(bpm);
        double[] rhythmToPlay = getIncrementalOnsets(absoluteRhythm);
        for(double onsetDelay : rhythmToPlay){
            Thread.sleep((long)onsetDelay);
            BeepFactory.getBeep();
        }
    }

    public double[] getIncrementalOnsets(double[] absoluteRhythm){
        double[] cumulativeOnsets = new double[absoluteRhythm.length];
        double prevOnset = 0.0;
        for (int i = 0; i< absoluteRhythm.length; i++){
            cumulativeOnsets[i] = absoluteRhythm[i] - prevOnset;
            prevOnset = absoluteRhythm[i];
        }
        return cumulativeOnsets;

    }

    public static void main(String[] args) throws InterruptedException {
        RhythmPlayer rp = new RhythmPlayer(80);
        Rhythm r = RhythmFactory.getRhythm(2);
        Metronome.playMetronome(rp.getBpm());
        rp.playRhythm(r);

    }




}
