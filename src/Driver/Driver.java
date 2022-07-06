package Driver;


import Rhythm.*;
import Scorer.Scorer;


import java.util.ArrayList;

public class Driver {

    private static long bpm = 100;

    public void setBpm(long bpm) {
        Driver.bpm = bpm;
    }

    public static void main(String[] args) throws InterruptedException {

        RhythmPlayer rhythmPlayer = new RhythmPlayer(bpm);
        RhythmListener listener = new RhythmListener();
        Scorer scorer = new Scorer(40, 2000);

        Rhythm r = RhythmFactory.getRhythm(3);
        Metronome.playMetronome(bpm);
        rhythmPlayer.playRhythm(r);
        listener.userTaps();
        Thread.sleep(4500); // 4 beats of user input
        System.out.println(RhythmListener.userInput);
        System.out.println(scorer.scoreEqualK(r.getAbsoluteRhythm(bpm), RhythmListener.userInput ));








    }
}
