package com.time.game.Controller.EndOfLevel;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Level.Level;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContinuationController implements Initializable {

    @FXML
    private Text levelCompletionText;
    @FXML
    private Text levelAccuracyText;
    @FXML
    private Text levelHighScoreText;
    @FXML
    private Button repeatLevelButton;
    @FXML
    private Button playNextLevelButton;
    @FXML
    private Button goToProfileButton;
    @FXML
    private Button exitButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        levelCompletionText.setText("Level "+Level.getLevelNumber()+" Complete");
        levelAccuracyText.setText("Accuracy score "+Level.getLastScore() + "%");
        int highScore = TimeApplication.userProfile.getHighestLevelScore(Level.getLevelNumber());
        levelHighScoreText.setText("Level High Score "+highScore+"%");


    }



    @FXML
    protected void onRepeatLevelButtonClick() throws IOException {
        ScreenController.changeScreen("game-play-view");
    }

    @FXML
    protected void onPlayNextLevelButtonClick() throws IOException {
        Level.setLevelNumber(Level.getLevelNumber()+1);
        ScreenController.changeScreen("game-play-view");
    }

    @FXML
    protected void onExitButtonClick(){
        Platform.exit();
    }
    @FXML
    protected void onProfileButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }




}
