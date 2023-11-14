package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
import com.time.game.TimeApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InstructionsController implements Initializable{
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
    private Line userOnsetPointer;
    @FXML
    private Line sampleLinePointer;
    @FXML
    private Line sampleOnsetPointer;
    @FXML
    private Line metronomePointer;
    @FXML
    private Line tapPadPointer;
    @FXML
    private Line userLinePointer;
    @FXML
    private Text oneText;
    @FXML
    private Text twoText;
    @FXML
    private Text threeText;
    @FXML
    private Text fourText;
    @FXML
    private Line beat1Marker;
    @FXML
    private Line beat2Marker;
    @FXML
    private Line beat3Marker;
    @FXML
    private Line beat4Marker;
    @FXML
    private Line endOfBarMarker;
    private final Color primaryYellow = Color.web("#d5a72a"); // primary yellow
    private final Color lightYellow = Color.web("#ffd85d"); // secondary yellow, light variant
    private final Color primaryOrange = Color.web("#d5522a"); // primary orange
    private final Color lightOrange = Color.web("#ff8356"); // primary orange, light variant
    private final Color backgroundOffBlack = Color.web("#121212");
    private  final Color highlight = lightYellow;
    private ArrayList<Node> metronomeNodes;
    private ArrayList<Node> sampleRhythmOnsetNodes;
    private ArrayList<Node> userOnsetNodes;
    private ArrayList<Node> tapPadNodes;
    private ArrayList<Node> userLineNodes;
    private ArrayList<Node> sampleLineNodes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        metronomeNodes = new ArrayList<>(List.of(beat1, oneText, beat2, twoText, beat3,
                threeText, beat4, fourText, metronomePointer, metronomeText));

        sampleRhythmOnsetNodes = new ArrayList<>(List.of(sampleOnset1, sampleOnset2, sampleOnset3,
                sampleOnset4, sampleOnsetPointer, computerOnsetLineText));

        userOnsetNodes = new ArrayList<>(List.of(userOnset1, userOnset2, userOnset3,
                userOnset4, userOnsetPointer, userOnsetLineText));

        tapPadNodes = new ArrayList<>(List.of(tapPad, tapPadPointer, tapPadText));

        sampleLineNodes = new ArrayList<>(List.of(sampleLine, sampleLinePointer, sampleLineText));

        userLineNodes = new ArrayList<>(List.of(userLine, userLinePointer, userLineText));
    }

    protected void setOpacityToLowForAllNonDesiredNodes(ArrayList<Node> desiredNodes){
        Stage stage = TimeApplication.primaryStage;
        for(var node : stage.getScene().getRoot().getChildrenUnmodifiable()){
            if(!desiredNodes.contains(node)){
                node.setStyle("-fx-opacity: 0.3");
            }
        }
    }

    protected void resetOpacityForAllNodes(){
        Stage stage = TimeApplication.primaryStage;
        for(var node : stage.getScene().getRoot().getChildrenUnmodifiable()){
            node.setStyle("-fx-opacity: 1");
        }

        // reset beat markers back to low opacity

        beat1Marker.setStyle("-fx-opacity: 0.3");
        beat2Marker.setStyle("-fx-opacity: 0.3");
        beat3Marker.setStyle("-fx-opacity: 0.3");
        beat4Marker.setStyle("-fx-opacity: 0.3");
        endOfBarMarker.setStyle("-fx-opacity: 0.3");
    }

    @FXML
    protected void onProfileButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void highlightMetronomeElements(){
        setOpacityToLowForAllNonDesiredNodes(metronomeNodes);

        beat1.setFill(highlight);
        beat1.setStroke(highlight);
        beat2.setFill(highlight);
        beat2.setStroke(highlight);
        beat3.setFill(highlight);
        beat3.setStroke(highlight);
        beat4.setFill(highlight);
        beat4.setStroke(highlight);
        metronomePointer.setStroke(highlight);
        metronomeText.setFill(highlight);
        oneText.setFill(highlight);
        twoText.setFill(highlight);
        threeText.setFill(highlight);
        fourText.setFill(highlight);

    }

    @FXML
    protected void unHighlightMetronomeElements(){
        resetOpacityForAllNodes();

        beat1.setFill(backgroundOffBlack);
        beat1.setStroke(lightYellow);
        beat2.setFill(backgroundOffBlack);
        beat2.setStroke(lightYellow);
        beat3.setFill(backgroundOffBlack);
        beat3.setStroke(lightYellow);
        beat4.setFill(backgroundOffBlack);
        beat4.setStroke(lightYellow);
        metronomePointer.setStroke(Color.WHITE);
        metronomeText.setFill(Color.WHITE);
        oneText.setFill(Color.WHITE);
        twoText.setFill(Color.WHITE);
        threeText.setFill(Color.WHITE);
        fourText.setFill(Color.WHITE);
    }

    @FXML
    protected void highlightSampleRhythmOnsetElements(){
        setOpacityToLowForAllNonDesiredNodes(sampleRhythmOnsetNodes);
        Color highlight = lightYellow;

        sampleOnset1.setStroke(highlight);
        sampleOnset2.setStroke(highlight);
        sampleOnset3.setStroke(highlight);
        sampleOnset4.setStroke(highlight);
        computerOnsetLineText.setFill(highlight);
        sampleOnsetPointer.setStroke(highlight);
    }

    @FXML
    protected void unHighlightSampleRhythmOnsetElements(){
        resetOpacityForAllNodes();
        sampleOnset1.setStroke(Color.WHITE);
        sampleOnset2.setStroke(Color.WHITE);
        sampleOnset3.setStroke(Color.WHITE);
        sampleOnset4.setStroke(Color.WHITE);
        computerOnsetLineText.setFill(Color.WHITE);
        sampleOnsetPointer.setStroke(Color.WHITE);
    }


    @FXML
    protected void highlightUserOnsetElements(){
        setOpacityToLowForAllNonDesiredNodes(userOnsetNodes);

        userOnset1.setStroke(highlight);
        userOnset2.setStroke(highlight);
        userOnset3.setStroke(highlight);
        userOnset4.setStroke(highlight);
        userOnsetLineText.setFill(highlight);
        userOnsetPointer.setStroke(highlight);
    }

    @FXML
    protected void unHighlightUserOnsetElements(){
        resetOpacityForAllNodes();
        userOnset1.setStroke(primaryOrange);
        userOnset2.setStroke(primaryOrange);
        userOnset3.setStroke(primaryOrange);
        userOnset4.setStroke(primaryOrange);
        userOnsetLineText.setFill(Color.WHITE);
        userOnsetPointer.setStroke(Color.WHITE);
    }

    @FXML
    protected void highlightTapPadElements(){
        setOpacityToLowForAllNonDesiredNodes(tapPadNodes);
        tapPad.setFill(highlight);
        tapPad.setStroke(highlight);
        tapPadText.setFill(highlight);
        tapPadPointer.setStroke(highlight);
    }

    @FXML
    protected void unHighlightTapPadElements(){
        resetOpacityForAllNodes();
        tapPad.setFill(backgroundOffBlack);
        tapPad.setStroke(primaryOrange);
        tapPadText.setFill(Color.WHITE);
        tapPadPointer.setStroke(Color.WHITE);
    }

    @FXML
    protected void highlightUserInputLineElements(){
        setOpacityToLowForAllNonDesiredNodes(userLineNodes);
        userLine.setStroke(highlight);
        userLineText.setFill(highlight);
        userLinePointer.setStroke(highlight);
    }
    @FXML
    protected void unHighlightUserInputLineElements(){
        resetOpacityForAllNodes();
        userLine.setStroke(Color.WHITE);
        userLineText.setFill(Color.WHITE);
        userLinePointer.setStroke(Color.WHITE);
    }

    @FXML
    protected void highlightSampleRhythmLineElements(){
        setOpacityToLowForAllNonDesiredNodes(sampleLineNodes);
        sampleLine.setStroke(highlight);
        sampleLineText.setFill(highlight);
        sampleLinePointer.setStroke(highlight);
    }
    @FXML
    protected void unHighlightSampleRhythmLineElements(){
        resetOpacityForAllNodes();
        sampleLine.setStroke(Color.WHITE);
        sampleLineText.setFill(Color.WHITE);
        sampleLinePointer.setStroke(Color.WHITE);
    }

}
