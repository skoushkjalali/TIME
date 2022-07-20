package com.time.game.Controller.GamePlay;
import com.time.game.GameLogic.Level.LevelDriver;
import com.time.game.GameLogic.Rhythm.BeepFactory;
import com.time.game.GameLogic.Rhythm.RhythmListener;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.RhythmFactory;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
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

    Timeline timeline;

    private Level level;

    protected KeyFrame[] getMetronomeKeyFrames(int numOfBars, int flashLength){

        KeyFrame[] metronomeEvents = new KeyFrame[numOfBars*4*2];
        int clickIOI = level.getBeatDurationInMilliSecs();

        // beat1 in all bars
        for(int i = 0; i < metronomeEvents.length; i+=8){
            int cumulativeDuration = (i/2)*clickIOI;
            metronomeEvents[i] = new KeyFrame(Duration.millis(cumulativeDuration), actionEvent -> makeBeat1Black());
            metronomeEvents[i+1] = new KeyFrame(Duration.millis(cumulativeDuration+flashLength), actionEvent -> makeBeat1White());
        }

        // beat2 in all bars
        for(int i = 2; i < metronomeEvents.length; i+=8){
            int cumulativeDuration = (i/2)*clickIOI;
            metronomeEvents[i] = new KeyFrame(Duration.millis(cumulativeDuration), actionEvent -> makeBeat2Black());
            metronomeEvents[i+1] = new KeyFrame(Duration.millis(cumulativeDuration+flashLength), actionEvent -> makeBeat2White());
        }

        // beat3 in all bars
        for(int i = 4; i < metronomeEvents.length; i+=8){
            int cumulativeDuration = (i/2)*clickIOI;
            metronomeEvents[i] = new KeyFrame(Duration.millis(cumulativeDuration), actionEvent -> makeBeat3Black());
            metronomeEvents[i+1] = new KeyFrame(Duration.millis(cumulativeDuration+flashLength), actionEvent -> makeBeat3White());
        }

        // beat4 in all bars
        for(int i = 6; i < metronomeEvents.length; i+=8){
            int cumulativeDuration = (i/2)*clickIOI;
            metronomeEvents[i] = new KeyFrame(Duration.millis(cumulativeDuration), actionEvent -> makeBeat4Black());
            metronomeEvents[i+1] = new KeyFrame(Duration.millis(cumulativeDuration+flashLength), actionEvent -> makeBeat4White());
        }

        return metronomeEvents;
    }

    public void setLevel(int i){
        level = new Level(RhythmFactory.getRhythm(i));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        level = new Level(RhythmFactory.getRhythm(Level.getLevelNumber()));
        
        timeline = new Timeline();

        // add all metronome clicks and flashes to timeline
        timeline.getKeyFrames().addAll(getMetronomeKeyFrames(4,50));




        timeline.play();



//        // update the score
//        // todo make this a Task object
//        Thread t = new Thread(() -> {
//            while(true){
//                while(Level.isRunning()){
//                    scoreText.setText(" ");
//                }
//                scoreText.setText("Score: "+Level.getLastScore() +"%");
//            }
//        });
//        t.start();
//
//
//        Level level = new Level(RhythmFactory.getRhythm(Level.getLevelNumber()));
//
////         start playing level that has been selected by the user
//        // todo move this over to an Animation
//        Thread t1 = new Thread(() -> LevelDriver.playLevel(level));
//        t1.start();
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

    @FXML
    protected void makeBeat1Black() {
        BeepFactory.getBeep();
        beat1.setFill(Color.BLACK);

    }

    @FXML
    protected void makeBeat1White(){
        beat1.setFill(Color.WHITE);
    }


    @FXML
    protected void makeBeat2Black(){
        BeepFactory.getBeep();
        beat2.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat2White(){
        beat2.setFill(Color.WHITE);
    }

    @FXML
    protected void makeBeat3Black(){
        BeepFactory.getBeep();
        beat3.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat3White(){
        beat3.setFill(Color.WHITE);
    }

    @FXML
    protected void makeBeat4Black(){
        BeepFactory.getBeep();
        beat4.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat4White(){
        beat4.setFill(Color.WHITE);
    }












}