package Scorer;

import java.util.ArrayList;

import static java.lang.Math.round;

/*
    This class will score the user input against the sample
 */
public class Scorer {

    int LOWER_BOUND;
    int UPPER_BOUND;


    public Scorer(int LOWER_BOUND, int UPPER_BOUND){
        this.LOWER_BOUND = LOWER_BOUND;
        this.UPPER_BOUND = UPPER_BOUND;
    }

    /*
        This method scores user input against the sample rhythm when there are fewer user input taps than
        there are onsets in the sample rhythm.
     */
    public double scoreTooFewUserTaps(double[] sampleRhythm, ArrayList<Integer> userInput){
        return 0.0;
    }

    /*
     This method scores user input against the sample rhythm when there are more user input taps than
     there are onsets in the sample rhythm.
     The method maps each user tap with the nearest sample onset. It then calculates effective delta for each
     user tap.
     Then it calculates an onset handicap for each tap (this is an inaccurate input) as the percentage
     of the upper bound effective delta represents.
     This handicap is then summed, and subtracted from 100, with a floor of 0.
  */
    public double scoreTooManyUserTaps(double[] sampleRhythm, ArrayList<Integer> userInput){
        double score = 1.0;
        // iterate through each user tap
        for (int userTap : userInput) {

            // initialise first onset and delta as a benchmark to test against
            double onsetToMapTo = sampleRhythm[0];
            double smallestDelta = Math.abs(sampleRhythm[0] - userTap);

            // find the nearest sample onset
            for (double onset : sampleRhythm) {
                double delta = Math.abs(userTap - onset);
                if (delta < smallestDelta) {
                    smallestDelta = delta;
                    onsetToMapTo = onset;
                }
            }
            // calculate effective delta using this mapping
            double delta = calculateDelta(onsetToMapTo, userTap);
            double effectiveDelta = calculateEffectiveDelta(delta);

            // calculate onset handicap
            double onsetHandicap = effectiveDelta / (float) UPPER_BOUND;

            // update score
            score -= onsetHandicap;
        }

            score = Math.max(score, 0);

            return Math.round(score*100)/100.0;
        }



    /*
     This method scores user input against the sample rhythm when there are the same number of
      user taps as there are onsets in the sample rhythm.
  */
    public double scoreEqualK(double[] sampleRhythm, ArrayList<Integer> userInput){
        double sumOfScores = 0.0;

        for(int i = 0; i< sampleRhythm.length; i++){
            double delta = calculateDelta(sampleRhythm[i], userInput.get(i));
            double effectiveDelta = calculateEffectiveDelta(delta);
            double onsetScore = 1 - (effectiveDelta / (double) UPPER_BOUND);
            sumOfScores += onsetScore;
        }

        double score = sumOfScores / sampleRhythm.length;

        return Math.round(score*100)/100.0;

    }

    public double calculateDelta(double sampleOnset, double userOnset){
        return Math.abs(sampleOnset - userOnset);
    }

    public double calculateEffectiveDelta(double delta){
        if(delta > UPPER_BOUND) return UPPER_BOUND;
        if(delta < LOWER_BOUND) return 0;
        return delta;
    }









}
