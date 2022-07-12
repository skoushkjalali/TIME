package com.javafx.gui;
import com.core.logic.Level.LevelDriver;
import com.core.logic.Rhythm.RhythmFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


public class TimeController implements Initializable {
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
    public void onLevelSelection(ActionEvent event ){
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
    public void showScore(double score){
        textArea.setText("Score: "+score*100 +"%");
    }




}