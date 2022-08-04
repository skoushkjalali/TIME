package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.Model.Rhythm.RhythmFactory;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ChoiceBox<Integer> levelSelector;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    @FXML
    private ChoiceBox<Integer> bpmSelector;

    @FXML
    private Text username;

    @FXML
    private Text levelsCompletedText;
    @FXML
    private Text lastLevelScoreText;
    @FXML
    private Text averageHighestScoreText;

    private UserProfile userProfile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // populate page with current user data
        showCurrentUserProfileData();

        // populate dropdown menu for levelSelector
        for(int i = 1; i<= 25; i++) {
            levelSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        levelSelector.setOnAction(this::onLevelSelection);

        // populate dropdown menu for bpmSelector
        for(int i = 10; i<= 200; i+=10) {
            bpmSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        bpmSelector.setOnAction(this::onBpmSelection);
    }

    protected void showCurrentUserProfileData(){
        userProfile = TimeApplication.userProfile;
        username.setText(userProfile.getUsername());
        levelsCompletedText.setText(userProfile.getNumLevelsCompleted()+"/"+ RhythmFactory.getLastPossibleRhythmNumber());
        lastLevelScoreText.setText(userProfile.getScoreOnLastLevel());

        if (userProfile.getNumLevelsCompleted()!=0) {
            averageHighestScoreText.setText(userProfile.getAverageHighestScore() + "%");
        }
    }

    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }

    @FXML
    protected void onLevelSelection(ActionEvent event ){
        int levelSelection = levelSelector.getValue();
        Level.setLevelNumber(levelSelection);
    }

    @FXML
    protected void onPlayButtonClick() throws IOException {
        ScreenController.changeScreen("game-play-view");
    }

    @FXML
    protected void onBpmSelection(ActionEvent event){
        int bpmSelection = bpmSelector.getValue();
        Level.setBpm(bpmSelection);
    }







}
