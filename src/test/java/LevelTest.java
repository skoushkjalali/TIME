import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.Rhythm;
import com.time.game.Model.Rhythm.RhythmFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Rhythm r = RhythmFactory.getRhythm(10);
    Level level = new Level(r);
    int order = 1;

    @Test
    void testGetBarDurationInMilliSecs() {
        if (order == 1) {
            Level.setBpm(100);
            int duration = level.getBarDurationInMilliSecs();
            assertEquals(duration, 2400);
        }
        order+=1;
    }

    @Test
    void testGetBeatDurationInMilliSecs() {
        if (order == 2) {
            Level.setBpm(100);
            int duration = level.getBeatDurationInMilliSecs();
            assertEquals(duration, 600);
        }
        order +=1;
    }

    @Test
    void testgetLastScore() {
        if (order == 3) {
            assertEquals(0, Level.getLastScore());
        }
        order +=4;
    }

    @Test
    void testsetLastScore() {
        if (order == 4) {
            Level.setLastScore(99);
            assertEquals(99, Level.getLastScore());
        }
        order +=1;
    }

    @Test
    void testGetLevelNumber() {
        if (order == 5) {
        assertEquals(1, Level.getLevelNumber());
        }
        order +=1;
    }

    @Test
    void testSetLevelNumber() {
        if (order == 6) {
            Level.setLevelNumber(25);
            assertEquals(25, Level.getLevelNumber());
        }
        order +=1;
    }

    @Test
    void testGetBpm() {
            if (order == 7) {
                assertEquals(100, Level.getBpm());
            }
    }

    @Test
    void testSetBpm(){
        if (order == 8) {
            Level.setBpm(130);
            assertEquals(130, Level.getBpm());
        }
        order +=1;
    }

    @Test
    void testGetLOWER_BOUND(){
        if (order == 9) {
            assertEquals(20, level.getLOWER_BOUND());
            assertEquals(250, level.getUPPER_BOUND());
        }
        order +=1;
    }

    @Test
    void testGetUPPER_BOUND(){
        if (order == 10) {
            assertEquals(20, level.getLOWER_BOUND());
            assertEquals(250, level.getUPPER_BOUND());
        }
        order +=1;
    }

    @Test
    void testUserInputCaptureEnabled(){
        if (order == 11) {
            assertFalse(level.isUserInputCaptureEnabled());
        }
        order +=1;
    }

    @Test
    void testSetUserInputCaptureEnabled(){
        if (order == 12) {
            level.setUserInputCaptureEnabled(true);
            assertTrue(level.isUserInputCaptureEnabled());
        }
        order +=1;
    }

    @Test
    void testGetSampleRhythm(){
        if (order == 13) {
            Rhythm expectedRhythm = RhythmFactory.getRhythm(10);
            assertArrayEquals(expectedRhythm.getRelativeRhythm(), level.getSampleRhythm().getRelativeRhythm());
        }
        order +=1;
    }



}