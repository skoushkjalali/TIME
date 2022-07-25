package com.time.game.Controller.GamePlay;
import com.time.game.GameLogic.Rhythm.BeepFactory;
import com.time.game.GameLogic.Rhythm.RhythmListener;
import com.time.game.GameLogic.Scorer.Scorer;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.Rhythm;
import com.time.game.Model.Rhythm.RhythmFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


public class MainGamePlayController implements Initializable {

    @FXML
    private Pane mainPane;

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
    private Line startOfBarLine;
    @FXML
    private Line endOfBarLine;

    @FXML
    private Text centralText;

    Timeline timeline;

    private Level level;

    private RhythmListener rhythmListener;
    private Scorer scorer;

    private BeepFactory beepFactory;

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

    protected ArrayList<KeyFrame> getSampleRhythmKeyFrames() {
        int oneBarOffsetDuration = level.getBarDurationInMilliSecs();
        ArrayList<KeyFrame> sampleOnsetEvents = new ArrayList<>();
        Rhythm r = level.getSampleRhythm();
        double[] sampleOnsets = r.getAbsoluteRhythm(level.getBpm());

        for (var onset : sampleOnsets) {
            sampleOnsetEvents.add(new KeyFrame(Duration.millis(onset + oneBarOffsetDuration), actionEvent -> sampleOnsetBeep()));
        }
    return sampleOnsetEvents;
    }

    protected int scoreLevel(){
        double score = scorer.scoreInput(level.getSampleRhythm().getAbsoluteRhythm(level.getBpm()),
                rhythmListener.getShiftedUserInput(level.getBpm(), 1));
        return (int)(score*100);
    }

    protected void markLevelAsStarted(){
        Level.setRunning(true);
        centralText.setText("Listen");
    }

    protected void markLevelAsEnded(){
        Level.setRunning(false);
        centralText.setText(Level.getLastScore() +"%");
    }

    protected void setScore(){
        Level.setLastScore(scoreLevel());
    }

    public Pane getMainPane() {
        return mainPane;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        beepFactory = new BeepFactory();
        level = new Level(RhythmFactory.getRhythm(Level.getLevelNumber()));
        rhythmListener = new RhythmListener();
        scorer = new Scorer(level.getLOWER_BOUND(), level.getUPPER_BOUND());
        timeline = new Timeline();
        drawSampleOnsets();

        // mark level as inProgress
        KeyFrame startLevel = new KeyFrame(Duration.millis(0), e-> markLevelAsStarted());
        timeline.getKeyFrames().add(startLevel);
        // add all metronome clicks and flashes to timeline
        timeline.getKeyFrames().addAll(getMetronomeKeyFrames(3,50));
        // add all sample onsets to timeline
        timeline.getKeyFrames().addAll(getSampleRhythmKeyFrames());

//         initialise start of listening to user input
        KeyFrame userInputStartLocation = new KeyFrame(Duration.millis(level.getBarDurationInMilliSecs()),
                e -> rhythmListener.setupForNewRhythmInput());
        timeline.getKeyFrames().add(userInputStartLocation);
        KeyFrame setScore = new KeyFrame(Duration.millis(level.getBarDurationInMilliSecs()*3 + level.getUPPER_BOUND()), e -> setScore());
        timeline.getKeyFrames().add(setScore);
        // mark level as ended
        KeyFrame endLevel = new KeyFrame(Duration.millis((level.getBarDurationInMilliSecs()*3) + (level.getUPPER_BOUND()*1.5)),
                e -> markLevelAsEnded());
        timeline.getKeyFrames().add(endLevel);
        timeline.play();

    }

    /*
        This function calculates the GUI x-coordinate locations of the sample rhythm for the level currently selected
     */
    protected double[] getSampleOnsetXCoordinates(double pixelsPerBar, double startOfBarXCoordinate){
        double pixelsPerSegment = pixelsPerBar / level.getSampleRhythm().getSegments();

        return Arrays.stream(level.getSampleRhythm().getRelativeRhythm()).mapToDouble(relOnset -> (pixelsPerSegment * relOnset) +
                startOfBarXCoordinate).toArray();
    }

    protected void drawSampleOnsets(){
        for(double onset : getSampleOnsetXCoordinates(1000, 230)){
            Line newOnset = new Line();
            newOnset.setLayoutX(onset);
            newOnset.setLayoutY(467);
            newOnset.setStartX(-100);
            newOnset.setStartY(6);
            newOnset.setEndX(-100);
            newOnset.setEndY(60);
            newOnset.setStrokeWidth(2);
            mainPane.getChildren().add(newOnset);
        }
    }

    protected void sampleOnsetBeep(){
        beepFactory.getBeep3();
    }


    @FXML
    protected void userInputKeyPressed(){
        beepFactory.getBeep3();
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
        beepFactory.getBeep2();
        beat1.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat1White(){
        beat1.setFill(Color.WHITE);
    }


    @FXML
    protected void makeBeat2Black(){
        beepFactory.getBeep2();
        beat2.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat2White(){
        beat2.setFill(Color.WHITE);
    }

    @FXML
    protected void makeBeat3Black(){
        beepFactory.getBeep2();
        beat3.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat3White(){
        beat3.setFill(Color.WHITE);
    }

    @FXML
    protected void makeBeat4Black(){
        beepFactory.getBeep2();
        beat4.setFill(Color.BLACK);
    }

    @FXML
    protected void makeBeat4White(){
        beat4.setFill(Color.WHITE);
    }


}