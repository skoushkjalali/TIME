package Scorer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class ScorerTest {

    Scorer scorer = new Scorer(40, 2000);
    int[] sampleRhythm1 = {0, 100, 200, 300};
    int[] sampleRhythm2 = {0, 500, 1000, 2000};
    int[] sampleRhythm3 = {200, 201, 202, 203};
    int[] sampleRhythm4 = {1000, 1050, 1250, 1500};

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

    // middling score
//    ArrayList<Long> userInput11 = new ArrayList<>(List.of(200L, 500L, 600L, 500L));
//    ArrayList<Long> userInput12 = new ArrayList<>(List.of(-300L, 0L, 50L, 2000L));

    // zero score



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
    void scoreTooFewUserTaps() {

    }


    @Test
    void scoreTooManyUserTaps() {
    }


}