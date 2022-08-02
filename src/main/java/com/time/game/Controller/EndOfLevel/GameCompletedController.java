package com.time.game.Controller.EndOfLevel;

import com.time.game.Controller.ScreenController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.imageio.IIOException;
import java.io.IOException;

public class GameCompletedController {

    @FXML
    private Button profileButton;


    @FXML
    private Button exitButton;

    @FXML
    protected void onProfileButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }
}
