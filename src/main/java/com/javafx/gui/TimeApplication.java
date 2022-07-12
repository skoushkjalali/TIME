package com.javafx.gui;
import com.core.logic.Rhythm.BeepFactory;
import com.core.logic.Rhythm.RhythmListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;

public class TimeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("time-view.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // when a keyboard key is pressed, play a beep sound, and place a time stamp in milliseconds into
        // RhythmListener.userTaps.
        scene.setOnKeyPressed(keyEvent -> {
            BeepFactory.getBeep();
            RhythmListener.userInput.add((int) ((System.nanoTime() / 1_000_000) - RhythmListener.startTime));
        });

        // focus is needed for keyboard taps to be registered.
        scene.getRoot().requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}