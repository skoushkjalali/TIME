package com.time.game.GameLogic.Scorer;
import java.util.ArrayList;

/*
    This class will score the user input against the sample.
 */
public class Scorer {
    private final int LOWER_BOUND;
    private final int UPPER_BOUND;

    public Scorer(int LOWER_BOUND, int UPPER_BOUND){
        this.LOWER_BOUND = LOWER_BOUND;
        this.UPPER_BOUND = UPPER_BOUND;
    }

    /*
        This method returns the correct scoring function result depending on the number of taps in the user input
        in relation to the sample input.
        Equal number of taps to the sample returns scoreEqualK(sampleRhythm, userInput), else
        scoreEqualK(sampleRhythm, userInput).
     */
    public double scoreInput(double[] sampleRhythm, ArrayList<Integer> userInput){
        if (userInput.size() == sampleRhythm.length){
            return scoreEqualK(sampleRhythm, userInput);
        }
        else if(userInput.size() > sampleRhythm.length){
            return scoreTooManyTaps(sampleRhythm, userInput);
        }
        return scoreTooFewTaps(sampleRhythm, userInput);
    }

    /*
        This method scores user input against the sample rhythm when there are fewer user input taps than
        there are onsets in the sample rhythm.

        The method calculates effective delta for each present tap using its nearest sample onset.
        It then calculates an onset score for each tap as the percentage of the upper bound
        effective delta represents, and weights the score using the number of expected taps, and sums the result.
        Thus, a score of 100 is impossible when user input is < perfect.
     */
    public double scoreTooFewTaps(double[] sampleRhythm, ArrayList<Integer> userInput){
        double tooFewTapsScore = 0.0;
        double onsetWeight = 1.0 / sampleRhythm.length;

        // iterate through each user tap
        for (int userTap : userInput) {

            // get sample onset to map to
            double onsetToMapTo = getNearestOnset(sampleRhythm, userTap);

            // calculate effective delta using this mapping
            double delta = calculateDelta(onsetToMapTo, userTap);
            double effectiveDelta = calculateEffectiveDelta(delta);

            // calculate onset handicap and onset score
            double onsetScore = 1 - (effectiveDelta / (float) UPPER_BOUND);

            // update score
            tooFewTapsScore += onsetScore * onsetWeight;
        }
        return Math.round(tooFewTapsScore*100)/100.0;
    }

    /*
        This method scores user input against the sample rhythm when there are more user input taps than
        there are onsets in the sample rhythm.

        The method calculates effective delta for each present tap using its nearest sample onset.
        It then calculates an onset handicap for each tap as the percentage of the upper bound
        effective delta represents. Each onset carries a maximum onset handicap of 1/(number of sample onsets).
        This handicap is summed and subtracted from 100, with a floor of 0.

        An additional handicap of 1/number of sample onsets is applied to each extra onset.
     */
    public double scoreTooManyTaps(double[] sampleRhythm, ArrayList<Integer> userInput){
        double tooManyTapsScore = 1.0;

        // iterate through each user tap
        for (int userTap : userInput) {
            // get sample onset to map to
            double onsetToMapTo = getNearestOnset(sampleRhythm, userTap);

            // calculate effective delta using this mapping
            double delta = calculateDelta(onsetToMapTo, userTap);
            double effectiveDelta = calculateEffectiveDelta(delta);

            // calculate onset handicap
            double onsetHandicap = (effectiveDelta / (float) UPPER_BOUND) / (double) sampleRhythm.length;

            // update score
            tooManyTapsScore -= onsetHandicap;
        }

        // apply a handicap for each additional onset with respect to the correct number of onsets
        int numOfExtraOnsets = userInput.size() - sampleRhythm.length; // num extra

        // a sample rhythm with 4 onsets = handicap of 25% per extra onset in user
        double additionalHandicapPerExtraOnset = 1 / (double) sampleRhythm.length;

        // apply handicap per extra onset in userInput
        double totalRhythmHandicapForExtraOnsets = numOfExtraOnsets * additionalHandicapPerExtraOnset;

        tooManyTapsScore -= totalRhythmHandicapForExtraOnsets;

        // cap the lowest score possible at 0
        double result = Math.max(tooManyTapsScore, 0);
        return Math.round(result*100)/100.0;
    }

    /*
        This method returns the sample onset in the sample rhythm that is the closest to
        @param userInputOnset.
     */
    public double getNearestOnset(double[] sampleRhythm, int userOnset) {

        // initialise first onset and delta as a benchmark to test against
        double onsetToMapTo = sampleRhythm[0];
        double smallestDelta = Math.abs(sampleRhythm[0] - userOnset);

        // find the nearest sample onset
        for (double onset : sampleRhythm) {
            double delta = Math.abs(userOnset - onset);
            if (delta < smallestDelta) {
                smallestDelta = delta;
                onsetToMapTo = onset;
            }
        }
        return onsetToMapTo;
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
