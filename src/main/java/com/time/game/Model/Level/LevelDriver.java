package com.time.game.Model.Level;

import com.time.game.Model.Rhythm.*;
import com.time.game.Model.Scorer.Scorer;


/*
    This class takes a rhythm object and creates a level to be played, and then plays the level.
 */
public class LevelDriver {

    private static int levelNumber = 1;

    public static int getLevelNumber() {
        return levelNumber;
    }

    public static void setLevelNumber(int levelNumber) {
        LevelDriver.levelNumber = levelNumber;
    }

    public static void playLevel(int levelNo){

        // get a Rhythm object from the model corresponding to the levelNo
        Rhythm r = RhythmFactory.getRhythm(levelNo);

        // pass rhythm, bpm, scoring bounds into level
        Level level = new Level(90, r);

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
                (level.getBarDurationInMilliSecs() * 1_000_000L) + level.getUPPER_BOUND()* 1_000_000L;
        if(endOfWaitTime != 0) {
            while (System.nanoTime() != endOfWaitTime) {
                //wait for the prescribed number of nanoseconds
            }
        }

        // score the input
        double score = scorer.scoreInput(r.getAbsoluteRhythm(level.getBpm()), listener.getShiftedUserInput(level.getBpm(), 2));
        System.out.println("Level "+levelNo +": "+ (int)(score*100) + "%");

    }
}
