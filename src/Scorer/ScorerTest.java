package Scorer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class ScorerTest {

    Scorer scorer = new Scorer(40, 2000);
    double[] sampleRhythm1 = {0, 100, 200, 300};
    double[] sampleRhythm2 = {0, 500, 1000, 2000};
    double[] sampleRhythm3 = {200, 201, 202, 203};
    double[] sampleRhythm4 = {1000, 1050, 1250, 1500};

    // Full marks
    ArrayList<Long> userInput1 = new ArrayList<>(List.of(0L, 100L, 200L, 300L));
    ArrayList<Long> userInput2 = new ArrayList<>(List.of(39L, 61L, 239L, 261L));
    ArrayList<Long> userInput3 = new ArrayList<>(List.of(39L, 99L, 199L, 299L));
    ArrayList<Long> userInput4 = new ArrayList<>(List.of(8L, 90L, 192L, 286L));
    ArrayList<Long> userInput5 = new ArrayList<>(List.of(-20L, 70L, 165L, 268L));
    ArrayList<Long> userInput6 = new ArrayList<>(List.of(-30L, 102L, 198L, 336L));

    // not full score but very close
    ArrayList<Long> userInput7 = new ArrayList<>(List.of(-40L, 140L, 200L, 300L));
    ArrayList<Long> userInput8 = new ArrayList<>(List.of(40L, 100L, 240L, 300L));
    ArrayList<Long> userInput9 = new ArrayList<>(List.of(40L, 140L, 240L, 300L));
    ArrayList<Long> userInput10 = new ArrayList<>(List.of(40L, 140L, 240L, 340L));
    ArrayList<Long> userInput11 = new ArrayList<>(List.of(80L, 180L, 280L, 380L));

    // zero percent
    ArrayList<Long> userInput12 = new ArrayList<>(List.of(2000L, 2500L, 3000L, 4000L));
    ArrayList<Long> userInput13 = new ArrayList<>(List.of(3000L, 4000L, 5000L, 6000L));


    // middling scores int[] sampleRhythm3 = {200, 201, 202, 203};
    ArrayList<Long> userInput14 = new ArrayList<>(List.of(10L, 150L, 300L, 1400L));
    ArrayList<Long> userInput15 = new ArrayList<>(List.of(220L, 300L, 2200L, 2210L));
    ArrayList<Long> userInput16 = new ArrayList<>(List.of(200L, 240L, 500L, 590L));
    ArrayList<Long> userInput17 = new ArrayList<>(List.of(0L, 100L, 300L, 700L));





    @Test
    void testCalcDelta1(){
        long delta = scorer.calculateDelta(0, 0 );
        assertEquals(0, delta);
    }

    @Test
    void testCalcDelta2(){
        long delta = scorer.calculateDelta(0, 40 );
        assertEquals(40, delta);
    }

    @Test
    void testCalcDelta3(){
        long delta = scorer.calculateDelta(0, 100 );
        assertEquals(100, delta);
    }

    @Test
    void testCalcDelta4(){
        long delta = scorer.calculateDelta(0, -100 );
        assertEquals(100, delta);
    }

    @Test
    void testCalcDelta5(){
        long delta = scorer.calculateDelta(1250, 1000 );
        assertEquals(250, delta);
    }

    @Test
    void testCalcDelta6(){
        long delta = scorer.calculateDelta(100, -2000 );
        assertEquals(2100, delta);
    }



    @Test
    void testCalcEffectiveDelta1(){
        long effectiveDelta = scorer.calculateEffectiveDelta(40);
        assertEquals(40, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta2(){
        long effectiveDelta = scorer.calculateEffectiveDelta(39);
        assertEquals(0, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta3(){
        long effectiveDelta = scorer.calculateEffectiveDelta(41);
        assertEquals(41, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta4(){
        long effectiveDelta = scorer.calculateEffectiveDelta(100);
        assertEquals(100, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta5(){
        long effectiveDelta = scorer.calculateEffectiveDelta(2000);
        assertEquals(2000, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta6(){
        long effectiveDelta = scorer.calculateEffectiveDelta(2001);
        assertEquals(2000, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta7(){
        long effectiveDelta = scorer.calculateEffectiveDelta(2100);
        assertEquals(2000, effectiveDelta);
    }


    @Test
    void scoreEqualK100Percent1() {
        double score = scorer.scoreEqualK(sampleRhythm1, userInput1);
        assertEquals(1.0, score);

    }

    @Test
    void scoreEqualK100Percent2() {
        double score = scorer.scoreEqualK(sampleRhythm1, userInput2);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent3() {
        double score = scorer.scoreEqualK(sampleRhythm1, userInput3);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent4() {
        double score = scorer.scoreEqualK(sampleRhythm1, userInput4);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent5() {
        double score = scorer.scoreEqualK(sampleRhythm1, userInput5);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent6() {
        double score = scorer.scoreEqualK(sampleRhythm1, userInput6);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose1(){
        double score = scorer.scoreEqualK(sampleRhythm1, userInput7);
        assertEquals(0.99, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose2(){
        double score = scorer.scoreEqualK(sampleRhythm1, userInput8);
        assertEquals(0.99, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose3(){
        double score = scorer.scoreEqualK(sampleRhythm1, userInput9);
        assertEquals(0.99, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose4(){
        double score = scorer.scoreEqualK(sampleRhythm1, userInput10);
        assertEquals(0.98, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose5(){
        double score = scorer.scoreEqualK(sampleRhythm1, userInput11);
        assertEquals(0.96, score);
    }

    @Test
    void scoreEqualK0Percent1(){
        double score = scorer.scoreEqualK(sampleRhythm2, userInput12);
        assertEquals(0.0, score);
    }

    @Test
    void scoreEqualK0Percent2(){
        double score = scorer.scoreEqualK(sampleRhythm2, userInput13);
        assertEquals(0.0, score);
    }


    @Test
    void scoreEqualKMiddlingScores1(){
        double score = scorer.scoreEqualK(sampleRhythm3, userInput14);
        assertEquals(0.81, score);
    }

    @Test
    void scoreEqualKMiddlingScores2(){
        double score = scorer.scoreEqualK(sampleRhythm3, userInput15);
        assertEquals(0.49, score);
    }

    @Test
    void scoreEqualKMiddlingScores3(){
        double score = scorer.scoreEqualK(sampleRhythm3, userInput16);
        assertEquals(0.91, score);
    }

    @Test
    void scoreEqualKMiddlingScores4(){
        double score = scorer.scoreEqualK(sampleRhythm3, userInput17);
        assertEquals(0.89, score);
    }






    @Test
    void scoreTooFewUserTaps() {

    }


    @Test
    void scoreTooManyUserTaps() {
    }


}