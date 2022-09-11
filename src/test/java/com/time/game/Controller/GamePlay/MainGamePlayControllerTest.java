package com.time.game.Controller.GamePlay;
import com.time.game.Model.Level.Level;
import javafx.animation.KeyFrame;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import javafx.scene.shape.Line;


public class MainGamePlayControllerTest{

    static MainGamePlayController controller = new MainGamePlayController();


    @BeforeAll
    static void setUp() throws IllegalAccessException {

        // injection of private startOfBar and endOfBar fields using apache commons framework to
        // match the running application
        Line mockedStartOfBarLine = new Line();
        mockedStartOfBarLine.setLayoutX(230);
        mockedStartOfBarLine.setLayoutY(420);
        FieldUtils.writeField(controller,"startOfBarLine", mockedStartOfBarLine, true);

        Line mockedEndOfBarLine = new Line();
        mockedEndOfBarLine.setLayoutX(1230);
        mockedEndOfBarLine.setLayoutY(420);
        FieldUtils.writeField(controller,"endOfBarLine", mockedEndOfBarLine, true);
    }


    @Test
    public void getMetronomeKeyFrames1() {
        controller.setLevel(7);
        KeyFrame[] keyFrames = controller.getMetronomeKeyFrames(1);
        double[] onsets = new double[8];
        for(int i = 0; i< keyFrames.length; i++){
            onsets[i] = keyFrames[i].getTime().toMillis();
        }
        double[] correctOnsets = {0, 50, 600, 650, 1200, 1250, 1800, 1850};
        assert Arrays.equals(onsets, correctOnsets);
    }




    @Test
    public void testGetSampleOnsetXCoordinates1() {
        controller.setLevel(1);
        Level.setBpm(100);
        double[] result = controller.getSampleOnsetXCoordinates();
        double[] expected = {230.0, 480.0, 605.0, 730.0, 980.0};
        assert Arrays.equals(result,expected);

    }

    @Test
    public void testGetSampleOnsetXCoordinates3(){
        controller.setLevel(3);
        Level.setBpm(100);
        double[] result = controller.getSampleOnsetXCoordinates();
        double[] expected = {230.0, 355.0, 605.0, 730.0, 980.0};
        assert Arrays.equals(result,expected);
    }

    @Test
    public void testGetSampleOnsetXCoordinates17(){
        controller.setLevel(17);
        Level.setBpm(200);
        double[] result = controller.getSampleOnsetXCoordinates();
        double[] expected = {230.0, 480.0, 605.0, 730.0, 813.3333333333333, 896.6666666666666, 980.0, 1042.5, 1105.0, 1167.5};
        assert Arrays.equals(result,expected);
    }

    @Test
    public void testGetSampleOnsetXCoordinates25(){
        controller.setLevel(25);
        Level.setBpm(100);
        double[] result = controller.getSampleOnsetXCoordinates();
        double[] expected = {230.0, 292.5, 355.0, 396.6666666666667, 438.33333333333337, 563.3333333333334,
                646.6666666666667, 688.3333333333334, 730.0, 830.0, 930.0, 980.0, 1030.0, 1130.0, 1180.0};
        assert Arrays.equals(result, expected);
    }


}