package com.time.game.Controller.GamePlay;
import com.time.game.Model.Level.LevelDriver;
import com.time.game.Model.Rhythm.BeepFactory;
import com.time.game.Model.Rhythm.RhythmListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class GamePlayController implements Initializable {
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
    @FXML
    private ChoiceBox<Integer> levelSelector;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // populate dropdown menu for levelSelector
        for(int i = 1; i<= 25; i++) {
            levelSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        levelSelector.setOnAction(this::onLevelSelection);
    }

    @FXML
    protected void onLevelSelection(ActionEvent event ){
        int levelSelection = levelSelector.getValue();
        LevelDriver.setLevelNumber(levelSelection);
    }

    @FXML
    protected void onPlayButtonClick() {
        // start application driver
        Thread t1 = new Thread(() -> LevelDriver.playLevel(LevelDriver.getLevelNumber()));
        t1.start();
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