package com.time.game.Controller;
import com.time.game.TimeApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ScreenController {

    public static Scene currentScene;

    public static void changeScreen(String fxmlFile) throws IOException {
        Stage stage = TimeApplication.primaryStage;
        FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource("/com/time/game/"+fxmlFile+".fxml"));
        Scene scene = new Scene(loader.load());
        currentScene = scene;
        scene.getStylesheets().add("TimeLessStyle.css");
        stage.setScene(scene);
        // allows keyboard input to be monitored
        scene.getRoot().requestFocus();
        stage.show();



    }

}
