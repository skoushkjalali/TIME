package com.time.game.Controller.Login;

import com.time.game.Controller.ScreenController;
import com.time.game.Database.DatabaseUtils;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.TimeApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

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
    private Text invalidLoginText;

    @FXML
    protected void onExistingUserLoginButtonClick() throws IOException, SQLException {
        String username = existingUsername.getText();
        String password = existingPassword.getText();

        // if username and password match database
        if(DatabaseUtils.validateExistingUser(username, password)){
            // initialize userProfile held as static variable in main TimeApplication class
            TimeApplication.userProfile = new UserProfile(existingUsername.getText());
            ScreenController.changeScreen("profile-view");
        }
        else {
            invalidLoginText.setText("invalid username and/or password");
        }
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
