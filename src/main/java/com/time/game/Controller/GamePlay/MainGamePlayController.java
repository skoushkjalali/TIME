package com.time.game.Controller.GamePlay;
import com.time.game.Model.Rhythm.BeepFactory;
import com.time.game.Model.Rhythm.RhythmListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class MainGamePlayController implements Initializable {
    @FXML
    private Label welcomeText;
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
    private TextArea textArea;
    @FXML
    private Line sampleLine;
    @FXML
    private Line userLine;
    @FXML
    private TextArea score;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // add things later if needed
    }

    @FXML
    protected void userInputKeyPressed(){
        BeepFactory.getBeep();
        RhythmListener.userInput.add((int) ((System.nanoTime() / 1_000_000) - RhythmListener.startTime));
        makeTapPadBlack();
    }

    @FXML
    protected void userInputKeyReleased(){
        makeTapPadWhite();
    }

    @FXML
    protected void showScore(double score){
        textArea.setText("Score: "+score*100 +"%");
    }

    @FXML
    protected void makeTapPadBlack(){
        tapPad.setFill(Color.BLACK);
    }

    @FXML
    protected void makeTapPadWhite(){
        tapPad.setFill(Color.WHITE);
    }


    protected void makeBeat1Black(){
        beat1.setFill(Color.BLACK);
    }

    protected void makeBeat1White(){
        beat1.setFill(Color.WHITE);
    }


}