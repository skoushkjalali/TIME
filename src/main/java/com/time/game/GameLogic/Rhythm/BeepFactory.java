package com.time.game.GameLogic.Rhythm;


import com.time.game.Model.Rhythm.RhythmFactory;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/*
    This class generates the sounds required for the application. The option to change the
    type of sound, and the process by which it is generated is done here.
 */
public class BeepFactory {

    URL metronome1Path = getClass().getResource("/sounds/MetronomeSound1.mp3");
    private final AudioClip metronome1AudioClip = new AudioClip(metronome1Path.toString());

    URL userTap1Path = getClass().getResource("/sounds/UserTapSound1.mp3");
    private final AudioClip userTap1AudioClip = new AudioClip(userTap1Path.toString());


    /*
        This method generates a beep sound.
     */
    public static void getBeep(){
        java.awt.Toolkit.getDefaultToolkit().beep();
    }

    /*
        This method generates a metronome sound
     */
    public void getBeep2(){
        metronome1AudioClip.play();
    }
    /*
        This method generates a snare drum sound
     */
    public void getBeep3(){
        userTap1AudioClip.play(0.85); // volume 85% max - reduces clipping
    }


}
