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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.Effect;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Button viewNextLevelButton;

    @FXML
    private Button viewPreviousLevelButton;

    @FXML
    private Button playLevelButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button exitButton;

    @FXML
    private Text statsPageTitle;

    @FXML
    private Text scoreLabelText;

    @FXML
    private ChoiceBox<Integer> bpmSelector;

    private UserProfile userProfile;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userProfile = TimeApplication.userProfile;
        statsPageTitle.setText("Level " + userProfile.getLevelStatRequestNumber() + " Progress Report");
        setupLineGraph();
        setupBPMSelector();

    }

    protected void setupBPMSelector(){
        // populate dropdown menu for bpmSelector
        for(int i = 10; i<= 200; i+=10) {
            bpmSelector.getItems().add(i);
        }
        // link onLevelSelection method with levelSelector ChoiceBox
        bpmSelector.setOnAction(this::onBpmSelection);
        bpmSelector.setValue(Level.getBpm());
    }


    @FXML
    protected void onBpmSelection(ActionEvent event) {
        int bpmSelection = bpmSelector.getValue();
        Level.setBpm(bpmSelection);
    }


        protected void setupLineGraph(){



        xAxis.tickLabelFontProperty().set(Font.font(20));
        xAxis.setStyle("-fx-font-size: 30; -fx-text-fill: #ffff; -fx-tick-label-fill: white ");

        scoreLabelText.setRotate(-90);
        yAxis.tickLabelFontProperty().set(Font.font(20));
        yAxis.setStyle("-fx-font-size: 30; -fx-text-fill: #ffff; -fx-tick-label-fill: white ");
        yAxis.setAutoRanging(false);
        yAxis.setUpperBound(100);
        yAxis.setLowerBound(0);



        ArrayList<Integer> levelScores = userProfile.getLevelScoreAttempts(userProfile.getLevelStatRequestNumber());
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        if(levelScores != null) {
            for (int i = 0; i < levelScores.size(); i++) {
                XYChart.Data<String, Number> levelScore = new XYChart.Data<>(""+(i+1), levelScores.get(i));
                dataSeries.getData().add(levelScore);
            }
        }
        levelStatsGraph.getData().add(dataSeries);
        levelStatsGraph.setLegendVisible(false);
        levelStatsGraph.setVerticalGridLinesVisible(false);
        levelStatsGraph.setHorizontalGridLinesVisible(false);
        levelStatsGraph.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

    }

    @FXML
    protected void onExitButtonClicked() throws SQLException {
        DatabaseUtils.updateAllUserData(userProfile);
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

    @FXML
    protected void onViewPreviousLevelButtonClicked() throws IOException {
        int level = userProfile.getLevelStatRequestNumber() - 1;
        if(level < 1){level = 1;}
        userProfile.setLevelStatRequestNumber(level);
        Level.setLevelNumber(level);
        ScreenController.changeScreen("level-stats-view");
    }

    @FXML
    protected void onViewNextLevelButtonClicked() throws IOException {
        int level = userProfile.getLevelStatRequestNumber() + 1;
        if (level > RhythmFactory.getLastPossibleRhythmNumber()) {
            level = RhythmFactory.getLastPossibleRhythmNumber();
        }
        userProfile.setLevelStatRequestNumber(level);
        Level.setLevelNumber(level);
        ScreenController.changeScreen("level-stats-view");
    }


}
