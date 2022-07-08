package Driver;


import Rhythm.*;
import Scorer.Scorer;


import java.util.ArrayList;
import java.util.Arrays;

public class Driver {

    private static long bpm = 100;

    public void setBpm(long bpm) {
        Driver.bpm = bpm;
    }

    public static void main(String[] args) throws InterruptedException {

        // setup the player, listener and scorer
        RhythmPlayer rhythmPlayer = new RhythmPlayer(bpm);
        RhythmListener listener = new RhythmListener();
        Scorer scorer = new Scorer(40, 2000);

        // select a rhythm
        Rhythm r = RhythmFactory.getRhythm(1);
        Metronome.playMetronome(bpm);
        rhythmPlayer.playRhythm(r);
        listener.userTaps();
        Thread.sleep(4500); // 4 beats of user input
        System.out.println(RhythmListener.userInput);
        System.out.println(Arrays.toString(r.getAbsoluteRhythm(bpm)));
        System.out.println(scorer.scoreInput(r.getAbsoluteRhythm(bpm), RhythmListener.userInput ));








    }
}
