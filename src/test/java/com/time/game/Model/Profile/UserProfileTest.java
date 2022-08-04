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
    void testNumLevelsCompleted(){
        userProfile.setupBlankScoreData();
        userProfile.updateUserScores(1, 10);
        userProfile.updateUserScores(2, 100);
        userProfile.updateUserScores(3, 23);
        userProfile.updateUserScores(25, 98);
        int levelsCompleted = userProfile.getNumLevelsCompleted();
        assertEquals(4, levelsCompleted);
    }

}