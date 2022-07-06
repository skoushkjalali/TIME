package GUI;


import Rhythm.RhythmListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
        System.out.println("PRESSED!");
        RhythmListener.userInput.add((System.currentTimeMillis() - startTime));
    }

    public static void getUserTaps() {
        MockGUI gui = new MockGUI();
    }
}



