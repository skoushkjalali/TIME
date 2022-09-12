import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.Rhythm;
import com.time.game.Model.Rhythm.RhythmFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Rhythm r = RhythmFactory.getRhythm(10);
    Level level = new Level(r);
    int order = 3;



    @Test
    void testGetBarDurationInMilliSecs10() {
        Level.setBpm(10);
        assertEquals(24000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs10() {
        Level.setBpm(10);
        assertEquals(6000, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs20() {
        Level.setBpm(20);
        assertEquals(12000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs20() {
        Level.setBpm(20);
        assertEquals(3000, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs30() {
        Level.setBpm(30);
        assertEquals(8000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs30() {
        Level.setBpm(30);
        assertEquals(2000, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs40() {
        Level.setBpm(40);
        assertEquals(6000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs40() {
        Level.setBpm(40);
        assertEquals(1500, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs50() {
        Level.setBpm(50);
        assertEquals(4800, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs50() {
        Level.setBpm(50);
        assertEquals(1200, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs60() {
        Level.setBpm(60);
        assertEquals(4000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs60() {
        Level.setBpm(60);
        assertEquals(1000, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs70() {
        Level.setBpm(70);
        assertEquals(3428, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs70() {
        Level.setBpm(70);
        assertEquals(857, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs80() {
        Level.setBpm(80);
        assertEquals(3000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBeatDurationInMilliSecs80() {
        Level.setBpm(80);
        assertEquals(750, level.getBeatDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs90() {
        Level.setBpm(90);
        assertEquals(2666, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs100() {
        Level.setBpm(100);
        assertEquals(2400, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs110() {
        Level.setBpm(110);
        assertEquals(2181, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs120() {
        Level.setBpm(120);
        assertEquals(2000, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs130() {
        Level.setBpm(130);
        assertEquals(1846, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs140() {
        Level.setBpm(140);
        assertEquals(1714, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs150() {
        Level.setBpm(150);
        assertEquals(1600, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs160() {
        Level.setBpm(160);
        assertEquals(1500, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs170() {
        Level.setBpm(170);
        assertEquals(1411, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs180() {
        Level.setBpm(180);
        assertEquals(1333, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs190() {
        Level.setBpm(190);
        assertEquals(1263, level.getBarDurationInMilliSecs());
    }

    @Test
    void testGetBarDurationInMilliSecs200() {
        Level.setBpm(200);
        assertEquals(1200, level.getBarDurationInMilliSecs());
    }


    @Test
    void testGetLastScore() {
        assertEquals(0, Level.getLastScore());
    }

    @Test
    void testSetLastScore() {
        Level.setLastScore(99);
        assertEquals(99, Level.getLastScore());
    }

    @Test
    void testGetLevelNumber() {
        Level.setLevelNumber(1);
        assertEquals(1, Level.getLevelNumber());
    }

    @Test
    void testSetLevelNumber() {
        Level.setLevelNumber(25);
        assertEquals(25, Level.getLevelNumber());
    }

    @Test
    void testGetBpm() {
        Level.setBpm(100);
        assertEquals(100, Level.getBpm());
    }

    @Test
    void testSetBpm(){
        Level.setBpm(130);
        assertEquals(130, Level.getBpm());
    }

    @Test
    void testGetLOWER_BOUND(){
        assertEquals(20, level.getLOWER_BOUND());
    }

    @Test
    void testGetUPPER_BOUND(){
        assertEquals(250, level.getUPPER_BOUND());
    }

    @Test
    void testUserInputCaptureEnabled(){
        assertFalse(level.isUserInputCaptureEnabled());
    }

    @Test
    void testSetUserInputCaptureEnabled(){
        level.setUserInputCaptureEnabled(true);
        assertTrue(level.isUserInputCaptureEnabled());
    }

    @Test
    void testGetSampleRhythm(){
        Rhythm expectedRhythm = RhythmFactory.getRhythm(10);
        assertArrayEquals(expectedRhythm.getRelativeRhythm(), level.getSampleRhythm().getRelativeRhythm());
    }



}