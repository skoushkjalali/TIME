package Scorer;

import java.util.ArrayList;

/*
    This class will score the user input against the sample
 */
public class Scorer {

    /*
        This method scores user input against the sample rhythm when there are fewer user input taps than
        there are onsets in the sample rhythm.
     */
    public double scoreTooFewUserTaps(int[] sampleRhythm, ArrayList<Long> userInput){
        return 0.0;
    }

    /*
     This method scores user input against the sample rhythm when there are more user input taps than
     there are onsets in the sample rhythm.
  */
    public double scoreTooManyUserTaps(int[] sampleRhythm, ArrayList<Long> userInput){
        return 0.0;
    }


    /*
     This method scores user input against the sample rhythm when there are the same number of
      user taps as there are onsets in the sample rhythm.
  */
    public double scoreEqualK(int[] sampleRhythm, ArrayList<Long> userInput){
        return 0.0;
    }





}
