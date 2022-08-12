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
    private Text invalidLoginText;
    @FXML
    private Text invalidNewUserTextLine1;
    @FXML
    private Text invalidNewUserTextLine2;
    @FXML
    private Text gameTitleText;

    private int numInvalidExistingLoginAttempts;
    private int numInvalidNewUserAttempts;


    @FXML
    protected void onExistingUserLoginButtonClick() throws IOException, SQLException {
        String username = existingUsername.getText();
        String password = existingPassword.getText();

        // if username and password match database
        if(DatabaseUtils.validateExistingUser(username, password)){
            // initialize userProfile held as static variable in main TimeApplication class
            TimeApplication.userProfile = new UserProfile(username);
            // Load existing user data from database to local userProfile instance
            loadUserProfileDataFromDatabase(TimeApplication.userProfile);
            // go to profile screen
            ScreenController.changeScreen("profile-view");
        }
        else {
            if(numInvalidExistingLoginAttempts % 2 == 0) {
                invalidLoginText.setFill(Color.RED); // toggle red/blue for multiple wrong attempts
                invalidLoginText.setFill(Color.RED); // otherwise text popup appears static on repeat tries
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

        // check username and password will fil in varchar(24) in database column and is not 0 length
        if(username.length() > 24 || password.length() > 24 || username.length() < 1 || password.length() < 1){
            if(numInvalidNewUserAttempts % 2 ==0) {
                invalidNewUserTextLine1.setFill(Color.RED); // toggle red/blue for multiple wrong attempts
                invalidNewUserTextLine2.setFill(Color.RED); // otherwise text popup appears static on repeat tries
            }
            else{
                invalidNewUserTextLine1.setFill(Color.BLUE);
                invalidNewUserTextLine2.setFill(Color.BLUE);
            }

            invalidNewUserTextLine1.setText("invalid username and/or password lengths");
            invalidNewUserTextLine2.setText("username and password must each be between 1 and 24 characters");
            numInvalidNewUserAttempts +=1;
            return;
        }

        if(DatabaseUtils.checkNewUsernameIsAvailable(username)) {
            // create new user in database
            DatabaseUtils.createNewUserProfile(username, password);
            DatabaseUtils.addNewUserToScoresTable(username);
            TimeApplication.userProfile = new UserProfile(username);
            ScreenController.changeScreen("profile-view");
        }
        else{
            invalidNewUserTextLine1.setText("username is already in use");
            invalidNewUserTextLine2.setText("");
        }
    }

    /*
        access user data and populate local user scores
     */
    protected void loadUserProfileDataFromDatabase(UserProfile userProfile) throws SQLException {
       DatabaseUtils.loadUserDataToLocalProfile(userProfile);

    }

}
