package com.time.game.Controller.GamePlay;

import com.time.game.Model.Rhythm.BeepFactory;
import javafx.fxml.FXML;

public class GamePlayMetronomeController {
    public static long[] beepLocations = new long[4]; // for testing

    /*
        This method plays 4 beeps at the bpm @param bpm
     */
    @FXML
    protected void playMetronome(double bpm) {

        long durationOfBeatInMilliSecs = (long) ((4 / bpm) * 60 * 1000) / 4;
        for (int i = 0; i < 4; i++) {
            BeepFactory.getBeep();
            long time = System.nanoTime();
            while (System.nanoTime() != time + durationOfBeatInMilliSecs * 1000000) {
                // wait
            }
        }
    }

    /*
        This method is the same as playMetronome, but additionally places the length of time each beat
        takes during runtime in beepLocations for testing. This testing functionality is not
        in playMetronome as it is not needed and may increase temporal variability.
     */
    public static void playMetronomeTester(double bpm) {
        long durationOfBeat = (long) ( (4 / bpm) * 60 * 1000) / 4;

        for(int i = 0; i<4; i++){
            long time = System.nanoTime();
            BeepFactory.getBeep();
            while(System.nanoTime() != time + durationOfBeat*1000000){
                // wait
            }
            beepLocations[i] = ((System.nanoTime() - time) / 1_000_000);
        }
    }
}
