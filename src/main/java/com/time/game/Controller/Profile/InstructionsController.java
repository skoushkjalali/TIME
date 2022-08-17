package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class InstructionsController {

    @FXML
    private Button profileButton;

    @FXML
    private Rectangle tapPad;
    @FXML
    private Rectangle beat1;
    @FXML
    private Rectangle beat2;
    @FXML
    private Rectangle beat3;
    @FXML
    private Rectangle beat4;
    @FXML
    private Line userOnset1;
    @FXML
    private Line userOnset2;
    @FXML
    private Line userOnset3;
    @FXML
    private Line userOnset4;

    @FXML
    private Line sampleOnset1;
    @FXML
    private Line sampleOnset2;
    @FXML
    private Line sampleOnset3;
    @FXML
    private Line sampleOnset4;

    @FXML
    private Line sampleLine;
    @FXML
    private Line userLine;

    @FXML
    private Text tapPadText;
    @FXML
    private Text metronomeText;
    @FXML
    private Text userLineText;
    @FXML
    private Text sampleLineText;
    @FXML
    private Text computerOnsetLineText;
    @FXML
    private Text userOnsetLineText;
    @FXML
    private Line sampleOnsetPointer;

    @FXML
    private Line metronomePointer;
    @FXML
    private Text oneText;
    @FXML
    private Text twoText;
    @FXML
    private Text threeText;
    @FXML
    private Text fourText;


    @FXML
    protected void onProfileButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void highlightMetronomeElements(){
        beat1.setFill(Color.web("#ffd85d"));
        beat1.setStroke(Color.web("#ffd85d"));
        beat2.setFill(Color.web("#ffd85d"));
        beat2.setStroke(Color.web("#ffd85d"));
        beat3.setFill(Color.web("#ffd85d"));
        beat3.setStroke(Color.web("#ffd85d"));
        beat4.setFill(Color.web("#ffd85d"));
        beat4.setStroke(Color.web("#ffd85d"));
        metronomePointer.setStroke(Color.web("#ffd85d"));
        metronomeText.setFill(Color.web("#ffd85d"));
        oneText.setFill(Color.web("#ffd85d"));
        twoText.setFill(Color.web("#ffd85d"));
        threeText.setFill(Color.web("#ffd85d"));
        fourText.setFill(Color.web("#ffd85d"));
    }

    @FXML
    protected void unHighlightMetronomeElements(){
        beat1.setFill(Color.web("#121212"));
        beat1.setStroke(Color.web("#ffd85d"));
        beat2.setFill(Color.web("#121212"));
        beat2.setStroke(Color.web("#ffd85d"));
        beat3.setFill(Color.web("#121212"));
        beat3.setStroke(Color.web("#ffd85d"));
        beat4.setFill(Color.web("#121212"));
        beat4.setStroke(Color.web("#ffd85d"));
        metronomePointer.setStroke(Color.WHITE);
        metronomeText.setFill(Color.WHITE);
        oneText.setFill(Color.WHITE);
        twoText.setFill(Color.WHITE);
        threeText.setFill(Color.WHITE);
        fourText.setFill(Color.WHITE);
    }

    @FXML
    protected void highlightSampleRhythmOnsetElements(){
        sampleOnset1.setStroke(Color.web("#ffd85d"));
        sampleOnset2.setStroke(Color.web("#ffd85d"));
        sampleOnset3.setStroke(Color.web("#ffd85d"));
        sampleOnset4.setStroke(Color.web("#ffd85d"));
        computerOnsetLineText.setFill(Color.web("#ffd85d"));
        sampleOnsetPointer.setStroke(Color.web("#ffd85d"));
    }

    @FXML
    protected void unHighlightSampleRhythmOnsetElements(){
        sampleOnset1.setStroke(Color.WHITE);
        sampleOnset2.setStroke(Color.WHITE);
        sampleOnset3.setStroke(Color.WHITE);
        sampleOnset4.setStroke(Color.WHITE);
        computerOnsetLineText.setFill(Color.WHITE);
        sampleOnsetPointer.setStroke(Color.WHITE);
    }



}
