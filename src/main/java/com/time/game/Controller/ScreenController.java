package com.time.game.Controller;

import com.time.game.Model.Level.LevelDriver;
import com.time.game.TimeApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class ScreenController {

    public static void switchToGamePlayScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource("/com/time/game/game-play-view.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = TimeApplication.primaryStage;
        stage.setScene(scene);
        stage.setMaxHeight(868);
        stage.setMinHeight(868);
        stage.setMaxWidth(1300);
        stage.setMinWidth(1300);
        stage.show();

        // allows keyboard input to be monitored
        scene.getRoot().requestFocus();

        // start playing level
        Thread t1 = new Thread(() -> LevelDriver.playLevel(LevelDriver.getLevelNumber()));
        t1.start();
    }
}
