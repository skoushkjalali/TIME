package com.core.logic.Level;

import com.core.logic.Rhythm.*;
import com.core.logic.Scorer.Scorer;

/*
    This class takes a rhythm object and creates a level to be played, and then plays the level.
 */
public class LevelDriver {

    public static void playLevel(){

        int LOWER_BOUND = 40;
        int UPPER_BOUND = 750;

        for(int i = 7; i<=25;i++) {
            // run through all 25 rhythms
            Rhythm r = RhythmFactory.getRhythm(i);

            // pass rhythm, bpm, scoring bounds into level
            Level level = new Level(90, LOWER_BOUND, UPPER_BOUND, r);

            // set up the player, listener and scorer,
            RhythmPlayer rhythmPlayer = new RhythmPlayer(level.getBpm());
            RhythmListener listener = new RhythmListener();
            listener.setupForNewRhythmInput();
            Scorer scorer = new Scorer(level.getLOWER_BOUND(), level.getUPPER_BOUND());

            // play 4 clicks of metronome at the desired bpm
            Metronome.playMetronome(level.getBpm());

            // play the selected rhythm
            rhythmPlayer.playRhythm(r);

            // wait to get userInput from keyboard. This includes additional time added to allow userInput to be late
            // by a maximum of the upper bound.
            long endOfWaitTime = System.nanoTime() +
                    (level.getBarDurationInMilliSecs() * 1_000_000L) + UPPER_BOUND*1_000_000;
            if(endOfWaitTime != 0) {
                while (System.nanoTime() != endOfWaitTime) {
                    //wait for the prescribed number of nanoseconds
                }
            }

            // hen score the input
            double score = scorer.scoreInput(r.getAbsoluteRhythm(level.getBpm()), listener.getShiftedUserInput(level.getBpm(), 2));
            System.out.println("Rhythm "+i +": "+ (int)(score*100) + "%");
        }
    }
}
