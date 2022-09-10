package com.time.game.GameLogic.Rhythm;
import java.util.ArrayList;

/*
    This class receives keyboard input from the UI representing a rhythm and records the temporal locations of the keyboard taps
 */
public class RhythmListener {
    private int startTime;
    private final ArrayList<Integer> userInput = new ArrayList<>();

    public void setupForNewRhythmInput(){
        startTime = (int) (System.nanoTime() / 1_000_000);
        userInput.clear();
    }

    public long getStartTime() {
        return startTime;
    }

    public ArrayList<Integer> getUserInput() {
        return userInput;
    }

    /*
       This method shifts the startTime forward by the number of bars specified using
       @param bars at the speed of @param bpm, then calculates the time from the start of the play bar
       for each onset
    */
    public ArrayList<Integer> getShiftedUserInput(double bpm, double bars){

        // length of the number of bars passed as @param bars in milliseconds
        int shift = (int) ((60 / bpm) * 1000 * 4 * bars);
        int startOfPlayBar = startTime + shift;

        ArrayList<Integer> shiftedInput = new ArrayList<>();

        for (int tap : userInput){
            shiftedInput.add(tap - startOfPlayBar);
        }
        return shiftedInput;
    }
}

