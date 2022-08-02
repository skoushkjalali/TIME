package com.time.game.Controller;
import com.time.game.TimeApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ScreenController {

    public static void changeScreen(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(ScreenController.class.getResource("/com/time/game/"+fxmlFile+".fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = TimeApplication.primaryStage;
        stage.setScene(scene);
        // allows keyboard input to be monitored
        scene.getRoot().requestFocus();
    }

}
