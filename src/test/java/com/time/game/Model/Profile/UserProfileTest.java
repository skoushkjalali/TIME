package com.time.game.Model.Profile;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    UserProfile userProfile = new UserProfile("John");


    @Test
    void testLevelScoresSetup1(){
        userProfile.setupBlankScoreData();
        assertEquals(25, userProfile.getLevelScores().size());
    }

    @Test
    void testLevelScoresSetup2(){
        userProfile.setupBlankScoreData();
        boolean isNull = true;
        for(int i = 1; i<= userProfile.getLevelScores().size(); i++){
            if(userProfile.getLevelScores().get(i) != null){
                isNull = false;
            }
        }
        assertTrue(isNull);
    }

    @Test
    void updateUserScores() {
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 95);
        userProfile.updateUserScores(1, 97);
        userProfile.updateUserScores(1, 99);
        ArrayList<Integer> scores = userProfile.getLevelScoreAttempts(1);
        ArrayList<Integer> expectedScores = new ArrayList<>();
        expectedScores.add(95);
        expectedScores.add(97);
        expectedScores.add(99);
        assertEquals(scores, expectedScores);
    }

    @Test
    void getHighestLevelScore() {
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 95);
        userProfile.updateUserScores(1, 97);
        userProfile.updateUserScores(1, 99);
        int highScore = userProfile.getHighestLevelScore(1);
        assertEquals(99, highScore);
    }

    @Test
    void getHighestLevelScore1() {
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(17, 1);
        userProfile.updateUserScores(17, 27);
        userProfile.updateUserScores(17, 33);

        int highScore = userProfile.getHighestLevelScore(17);
        assertEquals(33, highScore);
    }

    @Test
    void getHighestLevelScore2() {
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 1);
        userProfile.updateUserScores(2, 100);
        userProfile.updateUserScores(5, 33);
        userProfile.updateUserScores(5, 99);

        int highScore = userProfile.getHighestLevelScore(5);
        assertEquals(99, highScore);
    }

    @Test
    void getHighestLevelScore3() {
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 1);
        userProfile.updateUserScores(2, 100);
        userProfile.updateUserScores(5, 33);
        userProfile.updateUserScores(5, 99);

        int highScore = userProfile.getHighestLevelScore(20);
        assertEquals(-1, highScore);
    }



    @Test
    void testNumLevelsCompleted(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 10);
        userProfile.updateUserScores(2, 100);
        userProfile.updateUserScores(3, 23);
        userProfile.updateUserScores(25, 98);
        int levelsCompleted = userProfile.getNumLevelsCompleted();
        assertEquals(4, levelsCompleted);
    }

    @Test
    void testGetHighScores(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 10);
        userProfile.updateUserScores(1, 100);
        userProfile.updateUserScores(2, 23);
        userProfile.updateUserScores(2, 98);
        userProfile.updateUserScores(5, 53);
        userProfile.updateUserScores(5, 10);

        assert userProfile.getHighScores().get(0) == 100;
        assert userProfile.getHighScores().get(1) == 98;
        assert userProfile.getHighScores().get(2) == 53;
    }



    @Test
    void testGetAverageHighestScore(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 100);
        userProfile.updateUserScores(17, 100);
        userProfile.updateUserScores(12, 100);
        userProfile.updateUserScores(20, 100);
        int score = userProfile.getAverageHighestScore();
        assertEquals(100, score);
    }

    @Test
    void testGetAverageHighestScore1(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 14);
        userProfile.updateUserScores(1, 88);
        userProfile.updateUserScores(1, 100);
        userProfile.updateUserScores(1, 94);
        userProfile.updateUserScores(1, 76);
        userProfile.updateUserScores(1, 20);
        int score = userProfile.getAverageHighestScore();
        assertEquals(100, score);
    }

    @Test
    void testGetAverageHighestScore2(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 14);
        userProfile.updateUserScores(1, 88);
        userProfile.updateUserScores(1, 100);
        userProfile.updateUserScores(2, 99);
        userProfile.updateUserScores(2, 76);
        userProfile.updateUserScores(2, 20);
        int score = userProfile.getAverageHighestScore();
        assertEquals(100, score);
    }

    @Test
    void testGetAverageHighestScore3(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(5, 22);
        userProfile.updateUserScores(5, 50);
        userProfile.updateUserScores(5, 97);
        userProfile.updateUserScores(6, 12);
        userProfile.updateUserScores(6, 87);
        userProfile.updateUserScores(6, 20);
        userProfile.updateUserScores(7, 99);
        userProfile.updateUserScores(7, 56);
        int score = userProfile.getAverageHighestScore();
        assertEquals(94, score);
    }

    @Test
    void testGetAverageHighestScore4(){
        userProfile.setupBlankScoreData();
        int score = userProfile.getAverageHighestScore();
        assertEquals(0, score);
    }

}