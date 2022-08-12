package com.time.game.Controller.EndOfLevel;

import com.time.game.Controller.ScreenController;
import com.time.game.Database.DatabaseUtils;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.Model.Rhythm.RhythmFactory;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class GameCompletedController implements Initializable {

    @FXML
    private Button profileButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button repeatLevelButton;

    @FXML
    private Text scoreText;

    @FXML
    private Text levelHighScoreText;

    private UserProfile userProfile;

    @FXML
    private Button viewLevelStatsButton;

    @FXML
    private ChoiceBox<Integer> bpmSelector;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        userProfile = TimeApplication.userProfile;
        scoreText.setText(TimeApplication.userProfile.getScoreOnLastLevel());
        levelHighScoreText.setText(TimeApplication.userProfile.getHighestLevelScore(
                RhythmFactory.getLastPossibleRhythmNumber()) + "%");

        setupBPMSelector();
    }

    protected void setupBPMSelector(){
        // populate dropdown menu for bpmSelector
        for(int i = 10; i<= 200; i+=10) {
            bpmSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        bpmSelector.setOnAction(this::onBpmSelection);
        bpmSelector.setValue(Level.getBpm());

    }


    @FXML
    protected void onBpmSelection(ActionEvent event) {
        int bpmSelection = bpmSelector.getValue();
        Level.setBpm(bpmSelection);
    }

    @FXML
    protected void onProfileButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void onExitButtonClick() throws SQLException {
        DatabaseUtils.updateAllUserData(userProfile);
        Platform.exit();
    }

    @FXML
    protected void onRepeatLevelButtonClicked() throws IOException {
        ScreenController.changeScreen("game-play-view");
    }
    @FXML
    protected void onViewLevelStatsButtonClicked() throws IOException {
        userProfile.setLevelStatRequestNumber(Level.getLevelNumber());
        ScreenController.changeScreen("level-stats-view");
    }
}
