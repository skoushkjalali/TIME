import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.Rhythm;
import com.time.game.Model.Rhythm.RhythmFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {
    Rhythm r = RhythmFactory.getRhythm(10);

    Level level = new Level(r);

    @Test
    void getBarDurationInMilliSecs() {
        Level.setBpm(100);
        int duration = level.getBarDurationInMilliSecs();
        assertEquals(duration, 2400);

    }

    @Test
    void getBeatDurationInMilliSecs() {
        Level.setBpm(100);
        int duration = level.getBeatDurationInMilliSecs();
        assertEquals(duration, 600);
    }
}