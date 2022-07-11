package com.core.logic.Rhythm;


import java.util.ArrayList;

/*
    This class receives keyboard input representing a rhythm and records the temporal locations of the keyboard taps
 */
public class RhythmListener {

    public static ArrayList<Integer> userInput = new ArrayList<>();


    /*
        This method first clears the static variable userTaps, then captures any user inputs by calling
        UserTapFactory.getUserTaps().
        The implementation of getUserTaps() is able to change without recompiling this class.
     */
    public void userTaps(){
        userInput.clear();
        UserTapFactory.getUserTaps();
    }

    /*
        This method shifts the user tap input information forward by the number of bars specified using
        @param bars at the speed of @param bpm. This allows RhythmListener to be setup to listen to key input before
        the first beat of the required bar.
     */
    public ArrayList<Integer> getShiftedUserInput(double bpm, int bars){
        // number of bars to shift the start of the bar to
        int shift = (int) ((60 / bpm) * 1000 * 4 * bars);

        ArrayList<Integer> shiftedInput = new ArrayList<>();

        for (int tap : userInput){
            shiftedInput.add(tap - shift);
        }
        return shiftedInput;

    }

}

