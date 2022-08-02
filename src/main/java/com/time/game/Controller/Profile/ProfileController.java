package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
//import com.time.game.GameLogic.Level.LevelDriver;
import com.time.game.Model.Level.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ChoiceBox<Integer> levelSelector;

    @FXML
    private Button playButton;

    @FXML
    private ChoiceBox<Integer> bpmSelector;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // populate dropdown menu for levelSelector
        for(int i = 1; i<= 25; i++) {
            levelSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        levelSelector.setOnAction(this::onLevelSelection);

        // populate dropdown menu for bpmSelector
        for(int i = 10; i<= 200; i+=10) {
            bpmSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        bpmSelector.setOnAction(this::onBpmSelection);

    }

    @FXML
    protected void onLevelSelection(ActionEvent event ){
        int levelSelection = levelSelector.getValue();
        Level.setLevelNumber(levelSelection);
    }

    @FXML
    protected void onPlayButtonClick() throws IOException {
        ScreenController.changeScreen("game-play-view");
    }

    @FXML
    protected void onBpmSelection(ActionEvent event){
        int bpmSelection = bpmSelector.getValue();
        Level.setBpm(bpmSelection);
    }



}
