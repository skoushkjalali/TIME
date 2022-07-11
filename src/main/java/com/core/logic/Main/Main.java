package com.core.logic.Main;

import com.core.logic.Driver.Driver;
import com.core.logic.Rhythm.*;
import com.core.logic.Scorer.Scorer;

public class Main {

    public static void main(String[] args){

        for(int i = 19; i<=25;i++) {
            // run through all 25 rhythms
            Rhythm r = RhythmFactory.getRhythm(i);

            // pass rhythm, bpm, scoring bounds into gameDriver
            Driver gameDriver = new Driver(90, 40, 750, r);

            // set up the player, listener and scorer,
            RhythmPlayer rhythmPlayer = new RhythmPlayer(gameDriver.getBpm());
            RhythmListener listener = new RhythmListener();
            Scorer scorer = new Scorer(gameDriver.getLOWER_BOUND(), gameDriver.getUPPER_BOUND());

            // initialise keyboard listening
            listener.userTaps();

            // play 4 clicks of metronome
            Metronome.playMetronome(gameDriver.getBpm());

            // play the selected rhythm
            rhythmPlayer.playRhythm(r);

            // wait to get userInput from keyboard, including waiting for enough time for the input
            long endOfWaitTime = System.nanoTime() + (gameDriver.getBarDurationInMilliSecs() * 1_000_000L) + 760_000_000;
            if(endOfWaitTime != 0) {
                while (System.nanoTime() != endOfWaitTime) {
                    //wait for the prescribed number of nanoseconds
                }
            }

            double score = scorer.scoreInput(r.getAbsoluteRhythm(gameDriver.getBpm()), listener.getShiftedUserInput(gameDriver.getBpm(), 2));
            System.out.println("Rhythm "+i +": "+ (int)(score*100) + "%");
        }
    }
}
