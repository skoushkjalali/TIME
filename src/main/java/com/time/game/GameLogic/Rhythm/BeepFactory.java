package com.time.game.GameLogic.Rhythm;
import javafx.scene.media.AudioClip;
import java.net.URL;


/*
    This class generates the sounds required for the application. The option to change the
    type of sound, and the process by which it is generated is done here.
 */
    public class BeepFactory {
        URL metronome1Path = getClass().getResource("/sounds/MetronomeClick.mp3");
        private final AudioClip metronome1AudioClip;

        {
            assert metronome1Path != null;
            metronome1AudioClip = new AudioClip(metronome1Path.toString());
        }

        URL userTap1Path = getClass().getResource("/sounds/Onset.mp3");
        private final AudioClip onsetAudioClip;

        {
            assert userTap1Path != null;
            onsetAudioClip = new AudioClip(userTap1Path.toString());
        }

        public void playMetronomeClickSound(){
            metronome1AudioClip.play();
        }

        public void playOnsetSound(){
            onsetAudioClip.play();
        }
    }
