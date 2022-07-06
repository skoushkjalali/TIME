package Rhythm;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MetronomeTest {

    @Test
    void testMetronome() throws InterruptedException {
        Metronome.playMetronome(100);
       System.out.println(Arrays.toString(Metronome.beepLocations));
       assertTrue(Metronome.beepLocations[0] <= 605 && Metronome.beepLocations[0] >= 595);
       assertTrue(Metronome.beepLocations[1] <= 605 && Metronome.beepLocations[1] >= 595);
       assertTrue(Metronome.beepLocations[2] <= 605 && Metronome.beepLocations[2] >= 595);
       assertTrue(Metronome.beepLocations[3] <= 605 && Metronome.beepLocations[3] >= 595);
    }

}