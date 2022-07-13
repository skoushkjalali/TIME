package com.time.game.Controller;

import com.time.game.GameLogic.Level.LevelDriver;
import com.time.game.Model.Level.Level;
import com.time.game.Model.Rhythm.RhythmFactory;
import com.time.game.TimeApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


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

        // create Level object that has the corresponding Rhythm
        Level level = new Level(RhythmFactory.getRhythm(Level.getLevelNumber()));

        // start playing level that has been selected by the user
        Thread t1 = new Thread(() -> LevelDriver.playLevel(level));
        t1.start();
    }
}
