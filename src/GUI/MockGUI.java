package GUI;


import Rhythm.BeepFactory;
import Rhythm.RhythmFactory;
import Rhythm.RhythmListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
    This is a mock class that is to be used to allow keyboard user input functionality.
 */
public class MockGUI extends KeyAdapter {

    JFrame jFrame;
    Long startTime;

    MockGUI() {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'q') {
            jFrame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        BeepFactory.getBeep();
        RhythmListener.userInput.add((int) (System.currentTimeMillis() - startTime));
    }

    public static void getUserTaps() {
        MockGUI gui = new MockGUI();
    }
}



