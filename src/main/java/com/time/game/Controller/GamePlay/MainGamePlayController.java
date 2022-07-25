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
    private Text levelID;

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

    protected void setScore(){
        Level.setLastScore(scoreLevel());
        centralText.setText(Level.getLastScore() +"%");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        level = new Level(RhythmFactory.getRhythm(Level.getLevelNumber()));
        levelID.setText("Level "+Level.getLevelNumber());
        beepFactory = new BeepFactory();
        rhythmListener = new RhythmListener();
        scorer = new Scorer(level.getLOWER_BOUND(), level.getUPPER_BOUND());
        timeline = new Timeline();
        drawSampleOnsets();


        // add all metronome clicks and flashes to timeline
        timeline.getKeyFrames().addAll(getMetronomeKeyFrames(3,50));
        // add all sample onsets to timeline
        timeline.getKeyFrames().addAll(getSampleRhythmKeyFrames());

        // add required user action description as text at start of each bar
        timeline.getKeyFrames().add(new KeyFrame(Duration.ZERO, e -> centralText.setText("Ready")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(level.getBarDurationInMilliSecs()),
                e -> centralText.setText("Listen")));
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(level.getBarDurationInMilliSecs()*2),
                e -> centralText.setText("Play")));


        //  initialise rhythm listener 0 marker
        KeyFrame userInputStartLocation = new KeyFrame(Duration.millis(level.getBarDurationInMilliSecs()),
                e -> rhythmListener.setupForNewRhythmInput());
        timeline.getKeyFrames().add(userInputStartLocation);

        // start userInput 1/2 a beat before the start of the 3rd Bar
        // i.e will only draw an onset up to 1/2 a beat early
        KeyFrame startUserInput = new KeyFrame(Duration.millis(((level.getBarDurationInMilliSecs()*2) -
                (0.5 * level.getBeatDurationInMilliSecs()))), e-> Level.setUserInputCaptureEnabled(true));
        timeline.getKeyFrames().add(startUserInput);

        // end userInput 1/2 a beat after end of 3rd bar
        // i.e will only draw an onset up to 1/2 a beat late
        KeyFrame endUserInput = new KeyFrame(Duration.millis((level.getBarDurationInMilliSecs()*3) +
                (0.5 * level.getBeatDurationInMilliSecs())), e-> Level.setUserInputCaptureEnabled(false));
        timeline.getKeyFrames().add(endUserInput);

        // score level
        KeyFrame setScore = new KeyFrame(Duration.millis((level.getBarDurationInMilliSecs()*3) +
                level.getUPPER_BOUND()), e -> setScore());
        timeline.getKeyFrames().add(setScore);
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
            drawOnsetLine(onset, 467);
        }
    }

    /*
        This method draws a user tap onset during the 3rd bar of the level. It allows an onset to be draws a maximum
        of a half a beat before the first beat, and a half a beat after the bar has ended.
     */
    protected void drawUserOnset(){
        if(Level.isUserInputCaptureEnabled()) {
            double delayFromStartOfBar = (System.nanoTime() / 1_000_000.0) - (RhythmListener.startTime + level.getBarDurationInMilliSecs());
            double xAxisLocation = ((delayFromStartOfBar / level.getBarDurationInMilliSecs()) * 1000) + 230;
            drawOnsetLine(xAxisLocation, 582);
        }
    }

    private void drawOnsetLine(double xAxisLocation, double yAxisLocation) {
        Line newOnset = new Line();
        newOnset.setLayoutX(xAxisLocation);
        newOnset.setLayoutY(yAxisLocation);
        newOnset.setStartX(-100);
        newOnset.setStartY(6);
        newOnset.setEndX(-100);
        newOnset.setEndY(60);
        newOnset.setStrokeWidth(2);
        mainPane.getChildren().add(newOnset);
    }

    protected void sampleOnsetBeep(){
        beepFactory.getBeep3();
    }


    @FXML
    protected void userInputKeyPressed(){
        beepFactory.getBeep3();
        drawUserOnset();
        makeTapPadBlack();
        if(Level.isUserInputCaptureEnabled()) {
            RhythmListener.userInput.add((int) ((System.nanoTime() / 1_000_000) - RhythmListener.startTime));
        }

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