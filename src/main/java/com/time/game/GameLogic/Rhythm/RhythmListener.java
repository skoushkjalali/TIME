package com.time.game.GameLogic.Rhythm;
import java.util.ArrayList;

/*
    This class receives keyboard input from the UI representing a rhythm and records the temporal locations of the keyboard taps
 */
public class RhythmListener {
    private long startTime;
    private final ArrayList<Integer> userInput = new ArrayList<>();

    public void setupForNewRhythmInput(){
        startTime = System.nanoTime() / 1_000_000;
        userInput.clear();
    }

    public long getStartTime() {
        return startTime;
    }

    public ArrayList<Integer> getUserInput() {
        return userInput;
    }

    /*
                This method shifts the user tap input information forward by the number of bars specified using
                @param bars at the speed of @param bpm.
             */
    public ArrayList<Integer> getShiftedUserInput(double bpm, double bars){

        // number of milliseconds to shift each userTap
        int shift = (int) ((60 / bpm) * 1000 * 4 * bars);

        ArrayList<Integer> shiftedInput = new ArrayList<>();

        for (int tap : userInput){
            shiftedInput.add(tap - shift);
        }
        return shiftedInput;
    }
}

