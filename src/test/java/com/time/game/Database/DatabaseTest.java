package com.time.game.Database;
import com.time.game.Model.Profile.UserProfile;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.time.game.Database.DatabaseUtils.*;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    UserProfile userProfile = new UserProfile("Jason");



    @Test
    void testCompressUserLevelScores(){
        ArrayList<Integer> userScores = new ArrayList<>(List.of(34,74,0,100,6));
        String result = DatabaseUtils.compressUserLevelScores(userScores);
        assertEquals("23427410310016", result);
    }

    @Test
    void testCompressUserLevelScores1(){
        ArrayList<Integer> userScores = null;
        String result = DatabaseUtils.compressUserLevelScores(userScores);
        assertEquals("", result);
    }

    @Test
    void testCompressUserLevelScores2(){
        ArrayList<Integer> userScores = new ArrayList<>(List.of(0));
        String result = DatabaseUtils.compressUserLevelScores(userScores);
        assertEquals("10", result);
    }

    @Test
    void testDecompressUserLevelScores3(){
        ArrayList<Integer> result = DatabaseUtils.decompressUserLevelScores("23427410310016");
        ArrayList<Integer> expected = new ArrayList<>(List.of(34,74,0,100,6));
        assertEquals(expected,result);
    }

    @Test
    void testDecompressUserLevelScores4(){
        ArrayList<Integer> result = DatabaseUtils.decompressUserLevelScores("");
        ArrayList<Integer> expected = new ArrayList<>();
        assertEquals(expected,result);
    }

    @Test
    void testDecompressUserLevelScores5(){
        ArrayList<Integer> result = DatabaseUtils.decompressUserLevelScores("11255310010");
        ArrayList<Integer> expected = new ArrayList<>(List.of(1,55,100,0));
        assertEquals(expected,result);
    }

    @Test
    void testValidateExistingUserTrue() throws SQLException {
        assertTrue(DatabaseUtils.validateExistingUser("SashaKJ", "admin"));
    }

    @Test
    void testValidateExistingUserFalse() throws SQLException {
        assertFalse(DatabaseUtils.validateExistingUser("Simon", "Kablamo"));
    }

    @Test
    void testNewUsernameIsAvailableTrue() throws SQLException {
        assertTrue(DatabaseUtils.checkNewUsernameIsAvailable("JohnOTheGroats"));
    }

    @Test
    void testNewUsernameIsAvailableFalse1() throws SQLException {
        assertFalse(DatabaseUtils.checkNewUsernameIsAvailable("PhillippaKJ"));
    }

    @Test
    void testNewUsernameIsAvailableFalse() throws SQLException {
        assertFalse(DatabaseUtils.checkNewUsernameIsAvailable("SashaKJ"));
    }

    @Test
    void testGetUserProfileScoreDataAsStrings(){

        userProfile.resetMetrics();

        userProfile.updateUserScores(1,60);
        userProfile.updateUserScores(1,55);
        userProfile.updateUserScores(1,90);
        userProfile.updateUserScores(1,99);
        userProfile.updateUserScores(1,100);
        userProfile.updateUserScores(2,1);
        userProfile.updateUserScores(2,0);
        userProfile.updateUserScores(2,16);
        userProfile.updateUserScores(2,46);
        userProfile.updateUserScores(2,82);
        ArrayList<String>  output = getUserProfileScoreDataAsStrings(userProfile);
        assertEquals("2602552902993100", output.get(0));
        assertEquals("1110216246282", output.get(1));

    }

    @Test
    void testGetUserProfileScoreDataAsStrings1(){
        userProfile.resetMetrics();
        for(int i = 1; i<=25; i++) {
            userProfile.updateUserScores(i, i);
        }
        ArrayList<String>  output = getUserProfileScoreDataAsStrings(userProfile);
        assertEquals("11", output.get(0));
        assertEquals("12", output.get(1));
        assertEquals("13", output.get(2));
        assertEquals("14", output.get(3));
        assertEquals("15", output.get(4));
        assertEquals("16", output.get(5));
        assertEquals("17", output.get(6));
        assertEquals("18", output.get(7));
        assertEquals("19", output.get(8));
        assertEquals("210", output.get(9));
        assertEquals("211", output.get(10));
        assertEquals("212", output.get(11));
        assertEquals("213", output.get(12));
        assertEquals("214", output.get(13));
        assertEquals("215", output.get(14));
        assertEquals("216", output.get(15));
        assertEquals("217", output.get(16));
        assertEquals("218", output.get(17));
        assertEquals("219", output.get(18));
        assertEquals("220", output.get(19));
        assertEquals("221", output.get(20));
        assertEquals("222", output.get(21));
        assertEquals("223", output.get(22));
        assertEquals("224", output.get(23));
        assertEquals("225", output.get(24));


    }




}