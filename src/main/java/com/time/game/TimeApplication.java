package com.time.game;

import com.time.game.Controller.ScreenController;
import com.time.game.Model.Profile.UserProfile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

public class TimeApplication extends Application {
    public static Stage primaryStage; // Holds Stage object for duration application is running
    public static UserProfile userProfile; // Holds userProfile for duration of game. Can be updated.

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        stage.setMaxHeight(868);
        stage.setMinHeight(868);
        stage.setMaxWidth(1300);
        stage.setMinWidth(1300);
        stage.setTitle("TIME");

        FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource("/com/time/game/login-view.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add("TimeLessStyle.css");
        scene.setFill(Color.web("#121212")); // background off-black
        stage.setScene(scene);
        scene.getRoot().requestFocus(); // allows keyboard input to be monitored
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}