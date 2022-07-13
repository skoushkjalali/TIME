package com.javafx.gui;
import com.core.logic.Rhythm.BeepFactory;
import com.core.logic.Rhythm.RhythmListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class TimeApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("time-view.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaxHeight(868);
        stage.setMinHeight(868);
        stage.setMaxWidth(1300);
        stage.setMinWidth(1300);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}