package com.time.game.Controller.GamePlay;

import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.RhythmFactory;
import javafx.animation.KeyFrame;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;


class MainGamePlayControllerTest {

    MainGamePlayController controller = new MainGamePlayController();


    @Test
    void getMetronomeKeyFrames1() {
        controller.setLevel(7);
        KeyFrame[] keyFrames = controller.getMetronomeKeyFrames(1,50);
        double[] onsets = new double[8];
        for(int i = 0; i< keyFrames.length; i++){
            onsets[i] = keyFrames[i].getTime().toMillis();
        }
        double[] correctOnsets = {0, 50, 600, 650, 1200, 1250, 1800, 1850};
        assert Arrays.equals(onsets, correctOnsets);
    }




}