package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
import com.time.game.Database.DatabaseUtils;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.Model.Rhythm.RhythmFactory;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private ChoiceBox<Integer> levelSelector;

    @FXML
    private Button playButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button resetMetricsButton;

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

    @FXML
    private BarChart<String, Number> levelScoresBarChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private Text highScoreText;

    @FXML
    private Button viewLevelStatsButton;

    @FXML
    private Text lastLevelText;

    @FXML
    private Circle userCircle;

    @FXML
    private Rectangle statsSurfaceRectangle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // populate page with current user data
        showCurrentUserProfileData();

        // populate and setup bindings for level selector and BPM dropdown menus
        setupLevelAndBpmDropdownSelectors();

        // populate bar chart with high score values for each level
        setupBarChart();

        // if the red cross is used to exit the game, then user data is still stored
        setSaveUserDataIfWindowClosed();

    }

    protected void setSaveUserDataIfWindowClosed(){
        TimeApplication.primaryStage.setOnCloseRequest(e -> {
            try {
                DatabaseUtils.updateAllUserData(userProfile);
            } catch (SQLException ignored) {
            }
        });
    }

    @FXML
    protected void onViewLevelStatsButtonClicked() throws IOException {
        int statLevelRequest = 1;
        try{
            statLevelRequest = levelSelector.getValue();
        }catch (Exception ignored){
        }
        finally{
            userProfile.setLevelStatRequestNumber(statLevelRequest);
        }
        ScreenController.changeScreen("level-stats-view");
    }

    protected void setupBarChart(){

        xAxis.tickLabelFontProperty().set(Font.font(20));
        xAxis.setStyle("-fx-font-size: 30");
        xAxis.setStyle("-fx-text-fill: #ffff; -fx-tick-label-fill: white");

        highScoreText.setRotate(-90);
        yAxis.tickLabelFontProperty().set(Font.font(20));
        yAxis.setStyle("-fx-font-size: 30");
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(100);
        yAxis.setLowerBound(0);
        yAxis.setStyle("-fx-text-fill: #ffff; -fx-tick-label-fill: white");


        for(int i = 1; i<= RhythmFactory.getLastPossibleRhythmNumber(); i++) {
            XYChart.Series<String, Number> scoreData = new XYChart.Series<>();
            if(userProfile.getLevelScoreAttempts(i) != null) {
                XYChart.Data<String, Number> levelScore =  new XYChart.Data<>(""+i, userProfile.getHighestLevelScore(i));
                scoreData.getData().add(levelScore);
                levelScoresBarChart.getData().add(scoreData);


            }
            else{
                XYChart.Data<String, Number> levelScore =  new XYChart.Data<>(""+i, 0);
                scoreData.getData().add(levelScore);
                levelScoresBarChart.getData().add(scoreData);
            }
        }


        levelScoresBarChart.setCategoryGap(10);
        levelScoresBarChart.setBarGap(-20);
        levelScoresBarChart.setLegendVisible(false);
        levelScoresBarChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        levelScoresBarChart.setHorizontalGridLinesVisible(false);
        levelScoresBarChart.setVerticalGridLinesVisible(false);

    }

    protected void setupLevelAndBpmDropdownSelectors(){
        for(int i = 1; i<= RhythmFactory.getLastPossibleRhythmNumber(); i++) {
            levelSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        levelSelector.setOnAction(this::onLevelSelection);
        levelSelector.setValue(1);

        // populate dropdown menu for bpmSelector
        for(int i = 10; i<= 200; i+=10) {
            bpmSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        bpmSelector.setOnAction(this::onBpmSelection);
        bpmSelector.setValue(Level.getBpm());
    }

    protected void showCurrentUserProfileData(){
        userProfile = TimeApplication.userProfile;
        username.setText(userProfile.getUsername());
        levelsCompletedText.setText(userProfile.getNumLevelsCompleted()+"/"+ RhythmFactory.getLastPossibleRhythmNumber());
        lastLevelScoreText.setText(userProfile.getScoreOnLastLevel());

        if (userProfile.getNumLevelsCompleted()!=0) {
            averageHighestScoreText.setText(userProfile.getAverageHighestScore() + "%");
            if(Level.getLevelNumber() < 10){
                lastLevelText.setText("Last Level Played:  L"+Level.getLevelNumber());
            }
            else{
                lastLevelText.setText("Last Level Played: L"+Level.getLevelNumber());
            }
        }
    }

    @FXML
    protected void onExitButtonClicked() throws SQLException {
        DatabaseUtils.updateAllUserData(userProfile);
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

    @FXML
    protected void onResetMetricsButtonClick() throws IOException {
        userProfile.resetMetrics();
        ScreenController.changeScreen("profile-view");



    }


}
