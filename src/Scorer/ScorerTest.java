package Scorer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;



class ScorerTest {

    Scorer scorer = new Scorer();
    int[] sampleRhythm1 = {0, 100, 200, 300};
    int[] sampleRhythm2 = {0, 500, 1000, 2000};
    int[] sampleRhythm3 = {200, 201, 202, 203};
    int[] sampleRhythm4 = {1000, 1050, 1250, 1500};

    // Full marks
    ArrayList<Long> userInput1 = new ArrayList<>(List.of(0L, 100L, 200L, 300L));
    ArrayList<Long> userInput2 = new ArrayList<>(List.of(40L, 140L, 240L, 340L));
    ArrayList<Long> userInput3 = new ArrayList<>(List.of(39L, 99L, 199L, 299L));
    ArrayList<Long> userInput4 = new ArrayList<>(List.of(8L, 90L, 192L, 286L));
    ArrayList<Long> userInput5 = new ArrayList<>(List.of(-20L, 70L, 165L, 268L));
    ArrayList<Long> userInput6 = new ArrayList<>(List.of(-40L, 60L, 160L, 260L));

    // not full score but very close
    ArrayList<Long> userInput7 = new ArrayList<>(List.of(-41L, 59L, 159L, 259L));
    ArrayList<Long> userInput8 = new ArrayList<>(List.of(41L, 141L, 241L, 341L));

    // very low score
    ArrayList<Long> userInput9 = new ArrayList<>(List.of(200L, 500L, 600L, 500L));
    ArrayList<Long> userInput10 = new ArrayList<>(List.of(-300L, 0L, 50L, 2000L));

    @Test
    void scoreEqualK100Percent1() {
        scorer.scoreEqualK(sampleRhythm1, userInput1);
    }

    @Test
    void scoreEqualK100Percent2() {
        scorer.scoreEqualK(sampleRhythm1, userInput2);
    }

    @Test
    void scoreEqualK100Percent3() {
        scorer.scoreEqualK(sampleRhythm1, userInput3);
    }

    @Test
    void scoreEqualK100Percent4() {
        scorer.scoreEqualK(sampleRhythm1, userInput4);
    }

    @Test
    void scoreEqualK100Percent5() {
        scorer.scoreEqualK(sampleRhythm1, userInput5);
    }

    @Test
    void scoreEqualK100Percent6() {
        scorer.scoreEqualK(sampleRhythm1, userInput6);
    }

    @Test
    void scoreEqualKNot100PercentButClose1(){
        scorer.scoreEqualK(sampleRhythm1, userInput7);
    }

    @Test
    void scoreEqualKNot100PercentButClose2(){
        scorer.scoreEqualK(sampleRhythm1, userInput8);
    }

    @Test
    void scoreEqualKLowScore1(){
        scorer.scoreEqualK(sampleRhythm1, userInput9);
    }

    @Test
    void scoreEqualKLowScore2(){
        scorer.scoreEqualK(sampleRhythm1, userInput10);
    }





    @Test
    void scoreTooFewUserTaps() {

    }


    @Test
    void scoreTooManyUserTaps() {
    }


}