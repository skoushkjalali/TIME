package Driver;


import Rhythm.*;
import Scorer.Scorer;


import java.util.ArrayList;
import java.util.Arrays;

/*
    This class drives the game.
 */
public class Driver {

    private static final int bpm = 100;
    private static final int barDurationInMilliSecs = (int)((60 / (float)bpm) * 1000 * 4);

    public static void main(String[] args) throws InterruptedException {

        // setup the player, listener and scorer,
        RhythmPlayer rhythmPlayer = new RhythmPlayer(bpm);
        RhythmListener listener = new RhythmListener();
        Scorer scorer = new Scorer(40, 1000);

        // select a rhythm
        Rhythm r = RhythmFactory.getRhythm(4);
        listener.userTaps();

        // play 4 clicks of metronome
        Metronome.playMetronome(bpm);

        // play the selected rhythm
        rhythmPlayer.playRhythm(r);

        // wait to get userInput from keyboard, including waiting for enough time for the input
        Thread.sleep(barDurationInMilliSecs+1000);
        double score = scorer.scoreInput(r.getAbsoluteRhythm(bpm), listener.getShiftedUserInput(bpm, 2));
        System.out.println(score);

    }
}
