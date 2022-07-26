package com.time.game.Controller.EndOfLevel;

import com.time.game.Controller.ScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.IOException;

public class EndOfLevel {

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

    @FXML
    protected void repeatLevel() throws IOException {
        ScreenController.switchToGamePlayScreen();
    }

}
