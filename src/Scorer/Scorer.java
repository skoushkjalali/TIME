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
        This method scores user input against the sample rhythm when there are fewer or greater user input taps than
        there are onsets in the sample rhythm.

        The method calculates effective delta for each present tap using its nearest sample onset.
        When too many taps - it calculates an onset handicap for each tap as the percentage of the upper bound
        effective delta represents. This handicap is then summed, and subtracted from 100, with a floor of 0.
        When too few taps - it calculates an onset score for each tap as the percentage of the upper bound
        effective delta represents, but then weights the score using the number of expected taps, and sums the result.
        Thus, a score of 100 is impossible when user input is < perfect.
     */
    public double scoreUnEqualK(double[] sampleRhythm, ArrayList<Integer> userInput){
        double tooFewTapsScore = 0.0;
        double tooManyTapsScore = 1.0;

        double onsetWeight = 1.0 / sampleRhythm.length;
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

            // calculate onset handicap and onset score
            double onsetScore = 1 - (effectiveDelta / (float) UPPER_BOUND);
            double onsetHandicap = effectiveDelta / (float) UPPER_BOUND;

            // update scores
            tooFewTapsScore += onsetScore * onsetWeight;
            tooManyTapsScore -= onsetHandicap;

        }

        // select the correct score based on user k (number of taps)
        double result = tooFewTapsScore;
        if(userInput.size() > sampleRhythm.length){
            result = Math.max(tooManyTapsScore, 0);
        }

        return Math.round(result*100)/100.0;
    }


    /*
     This method scores user input against the sample rhythm when there are the same number of
     user taps as there are onsets in the sample rhythm.
     The score is the average temporal distance of user taps from sample onsets, but allowing for a window of size
     LOWER_BOUND in which taps are deemed "perfect".

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
