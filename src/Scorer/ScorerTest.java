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
    ArrayList<Integer> userInput1 = new ArrayList<>(List.of(0, 100, 200, 300));
    ArrayList<Integer> userInput2 = new ArrayList<>(List.of(39, 61, 239, 261));
    ArrayList<Integer> userInput3 = new ArrayList<>(List.of(39, 99, 199, 299));
    ArrayList<Integer> userInput4 = new ArrayList<>(List.of(8, 90, 192, 286));
    ArrayList<Integer> userInput5 = new ArrayList<>(List.of(-20, 70, 165, 268));
    ArrayList<Integer> userInput6 = new ArrayList<>(List.of(-30, 102, 198, 336));

    // not full score but very close
    ArrayList<Integer> userInput7 = new ArrayList<>(List.of(-40, 140, 200, 300));
    ArrayList<Integer> userInput8 = new ArrayList<>(List.of(40, 100, 240, 300));
    ArrayList<Integer> userInput9 = new ArrayList<>(List.of(40, 140, 240, 300));
    ArrayList<Integer> userInput10 = new ArrayList<>(List.of(40, 140, 240, 340));
    ArrayList<Integer> userInput11 = new ArrayList<>(List.of(80, 180, 280, 380));

    // zero percent
    ArrayList<Integer> userInput12 = new ArrayList<>(List.of(2000, 2500, 3000, 4000));
    ArrayList<Integer> userInput13 = new ArrayList<>(List.of(3000, 4000, 5000, 6000));


    // middling scores int[] sampleRhythm3 = {200, 201, 202, 203};
    ArrayList<Integer> userInput14 = new ArrayList<>(List.of(10, 150, 300, 1400));
    ArrayList<Integer> userInput15 = new ArrayList<>(List.of(220, 300, 2200, 2210));
    ArrayList<Integer> userInput16 = new ArrayList<>(List.of(200, 240, 500, 590));
    ArrayList<Integer> userInput17 = new ArrayList<>(List.of(0, 100, 300, 700));





    @Test
    void testCalcDelta1(){
        double delta = scorer.calculateDelta(0, 0 );
        assertEquals(0, delta);
    }

    @Test
    void testCalcDelta2(){
        double delta = scorer.calculateDelta(0, 40 );
        assertEquals(40, delta);
    }

    @Test
    void testCalcDelta3(){
        double delta = scorer.calculateDelta(0, 100 );
        assertEquals(100, delta);
    }

    @Test
    void testCalcDelta4(){
        double delta = scorer.calculateDelta(0, -100 );
        assertEquals(100, delta);
    }

    @Test
    void testCalcDelta5(){
        double delta = scorer.calculateDelta(1250, 1000 );
        assertEquals(250, delta);
    }

    @Test
    void testCalcDelta6(){
        double delta = scorer.calculateDelta(100, -2000 );
        assertEquals(2100, delta);
    }



    @Test
    void testCalcEffectiveDelta1(){
        double effectiveDelta = scorer.calculateEffectiveDelta(40);
        assertEquals(40, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta2(){
        double effectiveDelta = scorer.calculateEffectiveDelta(39);
        assertEquals(0, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta3(){
        double effectiveDelta = scorer.calculateEffectiveDelta(41);
        assertEquals(41, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta4(){
        double effectiveDelta = scorer.calculateEffectiveDelta(100);
        assertEquals(100, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta5(){
        double effectiveDelta = scorer.calculateEffectiveDelta(2000);
        assertEquals(2000, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta6(){
        double effectiveDelta = scorer.calculateEffectiveDelta(2001);
        assertEquals(2000, effectiveDelta);
    }

    @Test
    void testCalcEffectiveDelta7(){
        double effectiveDelta = scorer.calculateEffectiveDelta(2100);
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



    double[] sampleRhythm5 = {533.34, 1200, 1733.34, 1866.67, 2266.67};
    ArrayList<Integer> userInput18 = new ArrayList<>(List.of(160, 507, 693, 1360, 1653, 1947, 2187));

    double[] sampleRhythm6 = {0, 300, 600, 900};
    ArrayList<Integer> userInput19 = new ArrayList<>(List.of(300, 350, 600, 650, 750, 800));


    double[] sampleRhythm7 = {1000, 2000, 2500};
    ArrayList<Integer> userInput20 = new ArrayList<>(List.of(50,100,150,200,250,300,350,400,450));
    ArrayList<Integer> userInput21 = new ArrayList<>(List.of(1000, 2000, 2500, 4500));




    @Test
    void scoreTooManyUserTapsZeroPercent1() {
        double score = scorer.scoreTooManyUserTaps(sampleRhythm7, userInput20);
        assertEquals(0.0, score);
    }

    @Test
    void scoreTooManyUserTapsZeroPercent2() {
        double score = scorer.scoreTooManyUserTaps(sampleRhythm7, userInput21);
        assertEquals(0.0, score);
    }

    @Test
    void scoreTooManyUserTaps1() {
        double score = scorer.scoreTooManyUserTaps(sampleRhythm5, userInput18);
        assertEquals(0.53, score);
    }

    @Test
    void scoreTooManyUserTaps2() {
        double score = scorer.scoreTooManyUserTaps(sampleRhythm6, userInput19);
        assertEquals(0.83, score);
    }

    @Test
    void scoreTooManyUserTapsCloseButNoCigar() {
        double[] sampleRhythm = {500, 1000.8, 1074.5, 1900};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(500, 1000, 1075, 2000, 1975));

        double score = scorer.scoreTooManyUserTaps(sampleRhythm, userInput);
        assertEquals(0.91, score);
    }


    @Test
    void scoreTooFewUserTaps() {

    }


}