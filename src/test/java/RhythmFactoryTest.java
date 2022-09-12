import com.time.game.Model.Rhythm.Rhythm;
import com.time.game.Model.Rhythm.RhythmFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RhythmFactoryTest {

    @Test
    void testGetRhythm(){
        Rhythm r  = RhythmFactory.getRhythm(1);
        assertEquals(Rhythm.class, r.getClass());
    }

    @Test
    void testGetRhythm1(){
        int[] data = RhythmFactory.getRhythm(4).getRelativeRhythm();
        int[] expected = {0,3,4,5,7};
        assertArrayEquals(expected, data);
    }

    @Test
    void testGetRhythm2(){
        int[] data = RhythmFactory.getRhythm(100).getRelativeRhythm();
        int[] expected = {0,2,3,4,6};
        assertArrayEquals(expected, data);
    }

    @Test
    void testGetRhythm3(){
        int[] data = RhythmFactory.getRhythm(-5).getRelativeRhythm();
        int[] expected = {0,2,3,4,6};
        assertArrayEquals(expected, data);
    }

    @Test
    void testGetLastPossibleRhythmNumber(){
        assertEquals(25, RhythmFactory.getLastPossibleRhythmNumber());
    }

}
