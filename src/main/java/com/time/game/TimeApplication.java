package com.time.game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class TimeApplication extends Application {

    public static Stage primaryStage;


    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profile-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaxHeight(868);
        stage.setMinHeight(868);
        stage.setMaxWidth(1300);
        stage.setMinWidth(1300);
        stage.setTitle("Profile");
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}