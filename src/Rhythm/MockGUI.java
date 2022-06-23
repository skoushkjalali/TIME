package Rhythm;


import Driver.Driver;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class MockGUI extends KeyAdapter {

    JFrame jFrame;
    public static ArrayList<Long> userTaps;
    Long startTime;


    MockGUI() {
        Driver.waitForUserInput = true;
        jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.addKeyListener(this);
        userTaps = new ArrayList<>();
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
        userTaps.add((System.currentTimeMillis() - startTime));

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }



    public static void getUserTaps() {
        MockGUI gui = new MockGUI();
    }
}



