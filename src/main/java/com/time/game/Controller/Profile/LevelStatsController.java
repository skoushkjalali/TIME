package com.time.game.Controller.Profile;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.Model.Rhythm.RhythmFactory;
import com.time.game.TimeApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LevelStatsController implements Initializable {

    @FXML
    private LineChart<String, Number> levelStatsGraph;

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button playLevelButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button exitButton;

    @FXML
    private Text statsPageTitle;

    private UserProfile userProfile;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userProfile = TimeApplication.userProfile;
        statsPageTitle.setText("Level " + userProfile.getLevelStatRequestNumber() + " Progress Report");
        setupLineGraph();

    }

    protected void setupLineGraph(){

        xAxis.setLabel("Attempt");
        xAxis.tickLabelFontProperty().set(Font.font(20));
        xAxis.setStyle("-fx-font-size: 30");

        yAxis.setLabel("Score");
        yAxis.tickLabelFontProperty().set(Font.font(20));
        yAxis.setStyle("-fx-font-size: 30");
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(100);
        yAxis.setLowerBound(0);




        ArrayList<Integer> levelScores = userProfile.getLevelScoreAttempts(userProfile.getLevelStatRequestNumber());
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        if(levelScores != null) {
            for (int i = 0; i < levelScores.size(); i++) {
                XYChart.Data<String, Number> levelScore = new XYChart.Data<>(""+(i+1), levelScores.get(i));
                dataSeries.getData().add(levelScore);
                System.out.println(levelScore);
            }
        }
        levelStatsGraph.getData().add(dataSeries);
        levelStatsGraph.setLegendVisible(false);
    }

    @FXML
    protected void onExitButtonClicked(){
        Platform.exit();
    }

    @FXML
    protected void onProfileButtonClicked() throws IOException {
        ScreenController.changeScreen("profile-view");
    }

    @FXML
    protected void onPlayLevelButtonClicked() throws IOException {
        Level.setLevelNumber(userProfile.getLevelStatRequestNumber());
        ScreenController.changeScreen("game-play-view");
    }


}
