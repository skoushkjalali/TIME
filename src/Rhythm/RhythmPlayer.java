package Rhythm;



/*
    This class receives a ready-made rhythm from Rhythm Factory and plays it.
 */
public class RhythmPlayer {

    private double bpm;


    RhythmPlayer(double bpm){
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
        double[] rhythmToPlay = getCumulativeOnsets(absoluteRhythm);
        for(double onsetDelay : rhythmToPlay){
            Thread.sleep((long)onsetDelay);
            BeepFactory.getBeep();
        }
    }

    public double[] getCumulativeOnsets(double[] absoluteRhythm){
        double[] cumulativeOnsets = new double[absoluteRhythm.length];
        double prevOnset = 0.0;
        for (int i = 0; i< absoluteRhythm.length; i++){
            cumulativeOnsets[i] = absoluteRhythm[i] - prevOnset;
            prevOnset = absoluteRhythm[i];
        }
        return cumulativeOnsets;

    }

    public static void main(String[] args) throws InterruptedException {
        RhythmPlayer rp = new RhythmPlayer(100);
        Rhythm r = RhythmFactory.getRhythm(4);
        Metronome.playMetronome(rp.getBpm());
        rp.playRhythm(r);

    }




}
