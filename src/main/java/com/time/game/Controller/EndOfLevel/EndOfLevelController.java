package com.time.game.Controller.EndOfLevel;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Level.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndOfLevelController implements Initializable {

    @FXML
    private Text levelCompletionText;
    @FXML
    private Text levelAccuracyText;
    @FXML
    private Text aggregateAccuracyText;
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
