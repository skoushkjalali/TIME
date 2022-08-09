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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private Text invalidNewUserTextLine1;
    @FXML
    private Text invalidNewUserTextLine2;

    private int numInvalidExistingLoginAttempts;
    private int numInvalidNewUserAttempts;


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
            if(numInvalidExistingLoginAttempts % 2 == 0) {
                invalidLoginText.setFill(Color.RED);
                invalidLoginText.setFill(Color.RED);
            }
            else{
                invalidNewUserTextLine1.setFill(Color.BLUE);
                invalidNewUserTextLine2.setFill(Color.BLUE);
            }

            invalidLoginText.setText("invalid username and/or password");
        }
    }

    @FXML
    protected void onNewUserLoginButtonClick() throws IOException, SQLException {
        String username = newUsername.getText();
        String password = newPassword.getText();


        if(username.length() > 24 || password.length() > 24){
            if(numInvalidNewUserAttempts % 2 ==0) {
                invalidNewUserTextLine1.setFill(Color.RED);
                invalidNewUserTextLine2.setFill(Color.RED);
            }
            else{
                invalidNewUserTextLine1.setFill(Color.BLUE);
                invalidNewUserTextLine2.setFill(Color.BLUE);
            }

            invalidNewUserTextLine1.setText("username and/or password too long");
            invalidNewUserTextLine2.setText("usernames and passwords can each only be up to 24 characters");
            numInvalidNewUserAttempts +=1;
            return;
        }

        if(DatabaseUtils.checkNewUsernameIsAvailable(username)) {
            // create new user in database
            DatabaseUtils.createNewUserProfile(username, password);
            TimeApplication.userProfile = new UserProfile(existingUsername.getText());
            ScreenController.changeScreen("profile-view");
        }
        else{
            invalidNewUserTextLine1.setText("username is already in use");
            invalidNewUserTextLine2.setText("");
        }
    }

    @FXML
    protected void onExitButtonClick(){
        Platform.exit();
    }


}
