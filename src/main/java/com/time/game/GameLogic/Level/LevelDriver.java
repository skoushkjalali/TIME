package com.time.game.GameLogic.Level;

import com.time.game.GameLogic.Rhythm.Metronome;
import com.time.game.GameLogic.Rhythm.RhythmListener;
import com.time.game.GameLogic.Rhythm.RhythmPlayer;
import com.time.game.Model.Level.Level;
import com.time.game.GameLogic.Scorer.Scorer;


/*
    This class takes a Level object and plays that level.
 */
public class LevelDriver {


    public static void playLevel(Level level){
        Level.setRunning(true);

        // set up the player, listener and scorer
        RhythmPlayer rhythmPlayer = new RhythmPlayer(level.getBpm());
        RhythmListener listener = new RhythmListener();
        listener.setupForNewRhythmInput();
        Scorer scorer = new Scorer(level.getLOWER_BOUND(), level.getUPPER_BOUND());


        // play 4 clicks of metronome at the desired bpm
        Metronome.playMetronome(level.getBpm());

        // play the selected rhythm
        rhythmPlayer.playRhythm(level.getSampleRhythm());

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
        double score = scorer.scoreInput(level.getSampleRhythm().getAbsoluteRhythm(level.getBpm()),
                listener.getShiftedUserInput(level.getBpm(), 2));
//        System.out.println("Level "+Level.getLevelNumber() +": "+ (int)(score*100) + "%");
        Level.setLastScore((int)(score*100));
        Level.setRunning(false);
    }
}
