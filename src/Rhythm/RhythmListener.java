package Rhythm;


import java.util.ArrayList;

/*
    This class receives keyboard input representing a rhythm and records the temporal locations of the keyboard taps
 */
public class RhythmListener {

    public static ArrayList<Integer> userInput = new ArrayList<>();


    public void userTaps(){
        userInput.clear();
        UserTapFactory.getUserTaps();
    }

    public ArrayList<Integer> getUserInput(){
        return  userInput;
    }





}

