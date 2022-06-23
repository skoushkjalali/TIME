package Driver;


import Rhythm.MockGUI;
import Rhythm.Rhythm;
import Rhythm.RhythmListener;
import Rhythm.RhythmPlayer;

import java.util.ArrayList;

public class Driver {

    public static boolean waitForUserInput = false;

    public static void main(String[] args) throws InterruptedException {

        RhythmListener listener = new RhythmListener();
        listener.userTaps();
        Thread.sleep(10000);
        for (var tap : MockGUI.userTaps) {
            System.out.println(tap);
        }

    }
}
