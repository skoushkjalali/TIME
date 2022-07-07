package Scorer;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class ScorerTest {
    Scorer scorer = new Scorer(40, 2000);

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
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput1 = new ArrayList<>(List.of(0, 100, 200, 300));

        double score = scorer.scoreEqualK(sampleRhythm, userInput1);
        assertEquals(1.0, score);

    }

    @Test
    void scoreEqualK100Percent2() {
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput2 = new ArrayList<>(List.of(39, 61, 239, 261));
        double score = scorer.scoreEqualK(sampleRhythm, userInput2);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent3() {
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput3 = new ArrayList<>(List.of(39, 99, 199, 299));
        double score = scorer.scoreEqualK(sampleRhythm, userInput3);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent4() {
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput4 = new ArrayList<>(List.of(8, 90, 192, 286));
        double score = scorer.scoreEqualK(sampleRhythm, userInput4);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent5() {
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput5 = new ArrayList<>(List.of(-20, 70, 165, 268));
        double score = scorer.scoreEqualK(sampleRhythm, userInput5);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualK100Percent6() {
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput6 = new ArrayList<>(List.of(-30, 102, 198, 336));
        double score = scorer.scoreEqualK(sampleRhythm, userInput6);
        assertEquals(1.0, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose1(){
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput7 = new ArrayList<>(List.of(-40, 140, 200, 300));
        double score = scorer.scoreEqualK(sampleRhythm, userInput7);
        assertEquals(0.99, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose2(){
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput8 = new ArrayList<>(List.of(40, 100, 240, 300));
        double score = scorer.scoreEqualK(sampleRhythm, userInput8);
        assertEquals(0.99, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose3(){
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput9 = new ArrayList<>(List.of(40, 140, 240, 300));
        double score = scorer.scoreEqualK(sampleRhythm, userInput9);
        assertEquals(0.99, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose4(){
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput10 = new ArrayList<>(List.of(40, 140, 240, 340));
        double score = scorer.scoreEqualK(sampleRhythm, userInput10);
        assertEquals(0.98, score);
    }

    @Test
    void scoreEqualKNot100PercentButClose5(){
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput11 = new ArrayList<>(List.of(80, 180, 280, 380));
        double score = scorer.scoreEqualK(sampleRhythm, userInput11);
        assertEquals(0.96, score);
    }

    @Test
    void scoreEqualK0Percent1(){
        double[] sampleRhythm = {0, 500, 1000, 2000};
        ArrayList<Integer> userInput12 = new ArrayList<>(List.of(2000, 2500, 3000, 4000));
        double score = scorer.scoreEqualK(sampleRhythm, userInput12);
        assertEquals(0.0, score);
    }

    @Test
    void scoreEqualK0Percent2(){
        double[] sampleRhythm = {0, 500, 1000, 2000};
        ArrayList<Integer> userInput13 = new ArrayList<>(List.of(3000, 4000, 5000, 6000));
        double score = scorer.scoreEqualK(sampleRhythm, userInput13);
        assertEquals(0.0, score);
    }


    @Test
    void scoreEqualKMiddlingScores1(){
        double[] sampleRhythm = {200, 201, 202, 203};
        ArrayList<Integer> userInput14 = new ArrayList<>(List.of(10, 150, 300, 1400));
        double score = scorer.scoreEqualK(sampleRhythm, userInput14);
        assertEquals(0.81, score);
    }

    @Test
    void scoreEqualKMiddlingScores2(){
        double[] sampleRhythm = {200, 201, 202, 203};
        ArrayList<Integer> userInput15 = new ArrayList<>(List.of(220, 300, 2200, 2210));
        double score = scorer.scoreEqualK(sampleRhythm, userInput15);
        assertEquals(0.49, score);
    }

    @Test
    void scoreEqualKMiddlingScores3(){
        double[] sampleRhythm = {200, 201, 202, 203};
        ArrayList<Integer> userInput16 = new ArrayList<>(List.of(200, 240, 500, 590));
        double score = scorer.scoreEqualK(sampleRhythm, userInput16);
        assertEquals(0.91, score);
    }

    @Test
    void scoreEqualKMiddlingScores4(){
        double[] sampleRhythm = {200, 201, 202, 203};
        ArrayList<Integer> userInput17 = new ArrayList<>(List.of(0, 100, 300, 700));
        double score = scorer.scoreEqualK(sampleRhythm, userInput17);
        assertEquals(0.89, score);
    }


    @Test
    void scoreTooManyUserTapsZeroPercent1() {
        double[] sampleRhythm7 = {1000, 2000, 2500};
        ArrayList<Integer> userInput20 = new ArrayList<>(List.of(50,100,150,200,250,300,350,400,450));
        double score = scorer.scoreUnEqualK(sampleRhythm7, userInput20);
        assertEquals(0.0, score);
    }

    @Test
    void scoreTooManyUserTapsZeroPercent2() {
        double[] sampleRhythm7 = {1000, 2000, 2500};
        ArrayList<Integer> userInput21 = new ArrayList<>(List.of(1000, 2000, 2500, 4500));
        double score = scorer.scoreUnEqualK(sampleRhythm7, userInput21);
        assertEquals(0.0, score);
    }

    @Test
    void scoreTooManyUserTaps1() {
        double[] sampleRhythm5 = {533.34, 1200, 1733.34, 1866.67, 2266.67};
        ArrayList<Integer> userInput18 = new ArrayList<>(List.of(160, 507, 693, 1360, 1653, 1947, 2187));
        double score = scorer.scoreUnEqualK(sampleRhythm5, userInput18);
        assertEquals(0.53, score);
    }

    @Test
    void scoreTooManyUserTaps2() {
        double[] sampleRhythm6 = {0, 300, 600, 900};
        ArrayList<Integer> userInput19 = new ArrayList<>(List.of(300, 350, 600, 650, 750, 800));
        double score = scorer.scoreUnEqualK(sampleRhythm6, userInput19);
        assertEquals(0.83, score);
    }

    @Test
    void scoreTooManyUserTapsCloseButNoCigar() {
        double[] sampleRhythm = {500, 1000.8, 1074.5, 1900};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(500, 1000, 1075, 2000, 1975));

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.91, score);
    }


    @Test
    void scoreTooFewUserTaps0Percent1() {
        double[] sampleRhythm = {100, 900, 1000, 1500, 2000};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(348, 1036));

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.38, score);
    }

    @Test
    void scoreTooFewUserTaps0Percent2() {
        double[] sampleRhythm = {100, 900, 1000, 1500, 2000};
        ArrayList<Integer> userInput = new ArrayList<>(List.of());

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.0, score);
    }

    @Test
    void scoreTooFewUserTaps0Percent3() {
        double[] sampleRhythm = {100, 900, 1000, 1500, 2000};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(-2000));

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.0, score);
    }

    @Test
    void scoreTooFewUserTapsHighMarks1() {
        double[] sampleRhythm = {100, 900, 1000, 1500, 2000};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(120,940,1000,1500));

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.8, score);
    }

    @Test
    void scoreTooFewUserTapsHighMarks2() {
        double[] sampleRhythm = {100, 900, 1000, 1500, 2000};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(100,900, 1000));

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.6, score);
    }

    @Test
    void scoreTooFewUserTapsLowMarks1() {
        double[] sampleRhythm = {50, 100, 274, 800};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(-300, 900));

        double score = scorer.scoreUnEqualK(sampleRhythm, userInput);
        assertEquals(0.44, score);
    }

    @Test
    void testScoreInputLessThan1() {
        double[] sampleRhythm = {50, 100, 274, 800};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(-300, 900));

        double score = scorer.scoreInput(sampleRhythm, userInput);
        assertEquals(0.44, score);
    }

    @Test
    void testScoreInputLessThan2() {
        double[] sampleRhythm = {100, 900, 1000, 1500, 2000};
        ArrayList<Integer> userInput = new ArrayList<>(List.of(348, 1036));

        double score = scorer.scoreInput(sampleRhythm, userInput);
        assertEquals(0.38, score);
    }

    @Test
    void testScoreInputEqualK1(){
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput10 = new ArrayList<>(List.of(40, 140, 240, 340));
        double score = scorer.scoreEqualK(sampleRhythm, userInput10);
        assertEquals(0.98, score);
    }

    @Test
    void testScoreInputEqualK2() {
        double[] sampleRhythm = {0, 100, 200, 300};
        ArrayList<Integer> userInput3 = new ArrayList<>(List.of(39, 99, 199, 299));
        double score = scorer.scoreEqualK(sampleRhythm, userInput3);
        assertEquals(1.0, score);
    }

    @Test
    void testScoreInputTooMany1() {
        double[] sampleRhythm5 = {533.34, 1200, 1733.34, 1866.67, 2266.67};
        ArrayList<Integer> userInput18 = new ArrayList<>(List.of(160, 507, 693, 1360, 1653, 1947, 2187));
        double score = scorer.scoreInput(sampleRhythm5, userInput18);
        assertEquals(0.53, score);
    }

    @Test
    void testScoreInputTooMany2() {
        double[] sampleRhythm6 = {0, 300, 600, 900};
        ArrayList<Integer> userInput19 = new ArrayList<>(List.of(300, 350, 600, 650, 750, 800));
        double score = scorer.scoreInput(sampleRhythm6, userInput19);
        assertEquals(0.83, score);
    }











}