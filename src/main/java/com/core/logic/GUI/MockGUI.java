package com.core.logic.GUI;


import com.core.logic.Rhythm.BeepFactory;
import com.core.logic.Rhythm.RhythmListener;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
    This is a mock class that is to be used to allow keyboard user input functionality.
 */
public class MockGUI extends KeyAdapter {

    JFrame jFrame;
    Long startTime;

    public MockGUI() {
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
        startTime = System.nanoTime() / 1_000_000;
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
        RhythmListener.userInput.add((int) ((System.nanoTime() / 1_000_000) - startTime));
    }

    public static void getUserTaps() {
        MockGUI gui = new MockGUI();
    }
}



