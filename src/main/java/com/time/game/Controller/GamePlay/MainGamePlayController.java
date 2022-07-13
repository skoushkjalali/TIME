package com.time.game.Controller.GamePlay;
import com.time.game.GameLogic.Level.LevelDriver;
import com.time.game.GameLogic.Rhythm.BeepFactory;
import com.time.game.GameLogic.Rhythm.Metronome;
import com.time.game.GameLogic.Rhythm.RhythmListener;

import com.time.game.GameLogic.Rhythm.RhythmPlayer;
import com.time.game.GameLogic.Scorer.Scorer;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.RhythmFactory;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    private Line sampleLine;
    @FXML
    private Line userLine;
    @FXML
    private Text scoreText;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // update the score
        Thread t = new Thread(() -> {
            while(true){
                while(Level.isRunning()){
                    scoreText.setText(" ");
                }
                scoreText.setText("Score: "+Level.getLastScore() +"%");
            }
        });
        t.start();


        Level level = new Level(RhythmFactory.getRhythm(Level.getLevelNumber()));

//         start playing level that has been selected by the user
        Thread t1 = new Thread(() -> LevelDriver.playLevel(level));
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