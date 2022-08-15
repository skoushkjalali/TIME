package com.time.game.Controller;
import com.time.game.TimeApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ScreenController {

    static Stage stage = TimeApplication.primaryStage;


    public static void changeScreen(String fxmlFile) throws IOException {

        FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource("/com/time/game/"+fxmlFile+".fxml"));
        stage.getScene().setRoot(loader.load());
        stage.getScene().getRoot().requestFocus(); // allows keyboard input to be monitored
    }

}
