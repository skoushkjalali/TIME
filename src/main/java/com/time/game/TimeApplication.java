package com.time.game;
import com.time.game.Controller.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TimeApplication extends Application {

    /*
        Holds Stage object for duration application is running
     */
    public static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        stage.setMaxHeight(868);
        stage.setMinHeight(868);
        stage.setMaxWidth(1300);
        stage.setMinWidth(1300);
        stage.setTitle("TIME APPLICATION");

        FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        Scene profileScene = new Scene(profileLoader.load());
        stage.setScene(profileScene);

        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}