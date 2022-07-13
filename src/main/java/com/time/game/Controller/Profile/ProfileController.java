package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Level.LevelDriver;
import com.time.game.TimeApplication;
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
    protected void onPlayButtonClick() throws IOException {
        ScreenController.switchToGamePlayScreen();
    }



}
