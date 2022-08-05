package com.time.game.Controller.EndOfLevel;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Rhythm.RhythmFactory;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        scoreText.setText("Score " + TimeApplication.userProfile.getScoreOnLastLevel());
        levelHighScoreText.setText("Level High Score "+TimeApplication.userProfile.getHighestLevelScore(
                RhythmFactory.getLastPossibleRhythmNumber()) + "%");
    }

    @FXML
    protected void onProfileButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    @FXML
    protected void onRepeatLevelButtonClicked() throws IOException {
        ScreenController.changeScreen("game-play-view");
    }
}
