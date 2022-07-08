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
        int barDurationInMilliSecs = (int)(60 / (float)bpm) * 1000 * 4;

        // select a rhythm
        Rhythm r = RhythmFactory.getRhythm(1);
        listener.userTaps();
        System.out.println("Listening");
        // play 4 clicks of metronome
        Metronome.playMetronome(bpm);

        // play the selected rhythm
        rhythmPlayer.playRhythm(r);

        // wait to get userInput from keyboard, including waiting for enough time for the input
        Thread.sleep(4500);


        System.out.println(RhythmListener.userInput);
        System.out.println(listener.getShiftedUserInput(bpm, 2));
        System.out.println(Arrays.toString(r.getAbsoluteRhythm(bpm)));
        System.out.println(scorer.scoreInput(r.getAbsoluteRhythm(bpm), listener.getShiftedUserInput(bpm, 2)));








    }
}
