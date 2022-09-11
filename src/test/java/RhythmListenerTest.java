
import com.time.game.GameLogic.Rhythm.RhythmListener;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;


class RhythmListenerTest {

    RhythmListener listener = new RhythmListener();


    @Test
    void getShiftedUserInput0() {
        listener.getUserInput().clear();
        listener.getUserInput().add(4800);
        listener.getUserInput().add(7200);
        listener.getUserInput().add(9600);
        ArrayList<Integer> result = listener.getShiftedUserInput(100, 2);
        assertEquals(0, result.get(0));
    }

    @Test
    void getShiftedUserInput1() {
        listener.getUserInput().clear();
        listener.getUserInput().add(4800);
        listener.getUserInput().add(7200);
        listener.getUserInput().add(9600);
        ArrayList<Integer> result = listener.getShiftedUserInput(100, 2);
        assertEquals(2400, result.get(1));
    }

    @Test
    void getShiftedUserInput2() {
        listener.getUserInput().clear();
        listener.getUserInput().add(4800);
        listener.getUserInput().add(7200);
        listener.getUserInput().add(9600);
        ArrayList<Integer> result = listener.getShiftedUserInput(100, 2);
        assertEquals(4800, result.get(2));
    }

    @Test
    void getShiftedUserInput3() {
        listener.getUserInput().clear();
        listener.getUserInput().add(7000);
        listener.getUserInput().add(20000);
        listener.getUserInput().add(30000);
        ArrayList<Integer> result = listener.getShiftedUserInput(80, 3);
        assertEquals(-2000, result.get(0));
    }

    @Test
    void getShiftedUserInput4() {
        listener.getUserInput().clear();
        listener.getUserInput().add(10000);
        listener.getUserInput().add(20000);
        listener.getUserInput().add(30000);
        ArrayList<Integer> result = listener.getShiftedUserInput(80, 3);
        assertEquals(11000, result.get(1));
    }

    @Test
    void getShiftedUserInput5() {
        listener.getUserInput().clear();
        listener.getUserInput().add(10000);
        listener.getUserInput().add(20000);
        listener.getUserInput().add(30000);
        ArrayList<Integer> result = listener.getShiftedUserInput(80, 3);
        assertEquals(21000, result.get(2));
    }

    @Test
    void testSetupForNewRhythmInput(){
        long timeNow = System.nanoTime() / 1_000_000;
        listener.setupForNewRhythmInput();
        assertEquals(timeNow, listener.getStartTime());
    }


}