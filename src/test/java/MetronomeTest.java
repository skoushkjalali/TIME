import com.time.game.GameLogic.Rhythm.Metronome;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class MetronomeTest {

    @Test
    void testMetronome1() {
        Metronome.playMetronomeTester(100);
        System.out.println(Arrays.toString(Metronome.beepLocations));
        assertEquals(600, Metronome.beepLocations[0]);
        assertEquals(600, Metronome.beepLocations[1]);
        assertEquals(600, Metronome.beepLocations[2]);
        assertEquals(600, Metronome.beepLocations[3]);
    }

    @Test
    void testMetronome2() {
        Metronome.playMetronomeTester(80);
        System.out.println(Arrays.toString(Metronome.beepLocations));
        assertEquals(750, Metronome.beepLocations[0]);
        assertEquals(750, Metronome.beepLocations[1]);
        assertEquals(750, Metronome.beepLocations[2]);
        assertEquals(750, Metronome.beepLocations[3]);

    }

    @Test
    void testMetronome3() {
        Metronome.playMetronomeTester(250);
        System.out.println(Arrays.toString(Metronome.beepLocations));
        assertEquals(240, Metronome.beepLocations[0]);
        assertEquals(240, Metronome.beepLocations[1]);
        assertEquals(240, Metronome.beepLocations[2]);
        assertEquals(240, Metronome.beepLocations[3]);
    }

    @Test
    void testMetronome4() {
        Metronome.playMetronomeTester(10);
        System.out.println(Arrays.toString(Metronome.beepLocations));
        assertEquals(6000, Metronome.beepLocations[0]);
        assertEquals(6000, Metronome.beepLocations[1]);
        assertEquals(6000, Metronome.beepLocations[2]);
        assertEquals(6000, Metronome.beepLocations[3]);

    }
}





