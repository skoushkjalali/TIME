package com.time.game.Controller.Login;

import com.time.game.Controller.ScreenController;
import com.time.game.Database.DatabaseUtils;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

    private int numInvalidExistingLoginAttempts;
    private int numInvalidNewUserAttempts;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set up enter key as login and signup button press (if password field is selected)
        setupEnterKeyBinding();
    }


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
                invalidLoginText.setFill(Color.web("#B00020")); // toggle red/blue for multiple wrong attempts
                                                    // otherwise text popup appears static on repeat tries
            }
            else{
                invalidLoginText.setFill(Color.web("#e94948"));
            }

            invalidLoginText.setText("invalid username and/or password");
            numInvalidExistingLoginAttempts +=1;
        }
    }

    @FXML
    protected void onNewUserLoginButtonClick() throws IOException, SQLException {

        String username = newUsername.getText();
        String password = newPassword.getText();

        // set colour of error messages;
        if(numInvalidNewUserAttempts % 2 == 0) {
            invalidNewUserTextLine1.setFill(Color.web("#B00020")); // toggle red/blue for multiple wrong attempts
            invalidNewUserTextLine2.setFill(Color.web("#B00020")); // otherwise text popup appears static on repeat tries
        }
        else{
            invalidNewUserTextLine1.setFill(Color.web("#e94948"));
            invalidNewUserTextLine2.setFill(Color.web("#e94948"));
        }

        // check username and password will fit in varchar(24) in database columns and are not 0 length
        if(username.length() > 24 || password.length() > 24 || username.length() < 1 || password.length() < 1){
            invalidNewUserTextLine1.setText("invalid username and/or password lengths");
            invalidNewUserTextLine2.setText("username and password must each be between 1 and 24 characters");
            numInvalidNewUserAttempts +=1;
            return;
        }

        // check username is available, if so create new user in database
        if(DatabaseUtils.checkNewUsernameIsAvailable(username)) {

            DatabaseUtils.createNewUserProfile(username, password);
            DatabaseUtils.addNewUserToScoresTable(username);
            TimeApplication.userProfile = new UserProfile(username);
            ScreenController.changeScreen("profile-view");
        } else{
            invalidNewUserTextLine1.setText("username is already in use");
            invalidNewUserTextLine2.setText("");
            numInvalidNewUserAttempts +=1;
        }
    }

    /*
        access user data and populate local user scores
     */
    protected void loadUserProfileDataFromDatabase(UserProfile userProfile) throws SQLException {
       DatabaseUtils.loadUserDataToLocalProfile(userProfile);
    }

    public void setupEnterKeyBinding(){

        existingPassword.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.ENTER){
                Platform.runLater(() -> existingUserLoginButton.setStyle("-fx-background-color: #a07800; " +
                        "-fx-effect: innershadow(gaussian,black,2,2,0.5,0.5)"));
            }
        });
        existingPassword.setOnKeyReleased(e-> {
            if(e.getCode() == KeyCode.ENTER){
                Platform.runLater(() -> {
                    existingUserLoginButton.setStyle("-fx-background-color: #d5a72a");

                    existingUserLoginButton.setOnMouseEntered(event ->
                            existingUserLoginButton.setStyle("-fx-background-color: #a07800; " +
                                    "-fx-effect: innershadow(gaussian,black,2,2,0.5,0.5)"));
                    existingUserLoginButton.setOnMouseExited(event ->
                            existingUserLoginButton.setStyle("-fx-background-color: #d5a72a"));

                });
                try {
                    onExistingUserLoginButtonClick();
                } catch (IOException | SQLException ignored) {
                }
            }
        });

        newPassword.setOnKeyPressed(e-> {
            if(e.getCode() == KeyCode.ENTER){
                Platform.runLater(() -> newUserLoginButton.setStyle("-fx-background-color: #a07800; " +
                        "-fx-effect: innershadow(gaussian,black,2,2,0.5,0.5)"));
            }
        });
        newPassword.setOnKeyReleased(e-> {
            if(e.getCode() == KeyCode.ENTER){
                Platform.runLater(() -> {
                    newUserLoginButton.setStyle("-fx-background-color: #d5a72a");

                    newUserLoginButton.setOnMouseEntered(event ->
                            newUserLoginButton.setStyle("-fx-background-color: #a07800; " +
                                    "-fx-effect: innershadow(gaussian,black,2,2,0.5,0.5)"));
                    newUserLoginButton.setOnMouseExited(event ->
                            newUserLoginButton.setStyle("-fx-background-color: #d5a72a"));

                });
                try {
                    onNewUserLoginButtonClick();
                } catch (IOException | SQLException ignored) {
                }
            }
        });
    }


}
