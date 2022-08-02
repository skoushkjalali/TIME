package com.time.game.Controller.Login;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Profile.UserProfile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField existingUsername;

    @FXML
    private  TextField existingPassword;

    @FXML
    private TextField newUsername;

    @FXML
    private TextField newPassword;

    @FXML
    private Button existingUserLoginButton;

    @FXML
    private Button newUserLoginButton;

    @FXML
    private Button exitButton;

    @FXML
    protected void onExistingUserLoginButtonClick() throws IOException {

        UserProfile.setUsername(existingUsername.getText());
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void onNewUserLoginButtonClick() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void onExitButtonClick(){
        Platform.exit();
    }


}
