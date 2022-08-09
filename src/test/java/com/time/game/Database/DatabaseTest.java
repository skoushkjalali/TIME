package com.time.game.Database;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {



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


}