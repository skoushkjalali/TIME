module time {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires org.testng;


    opens com.time.game to javafx.fxml;
    exports com.time.game;
    exports com.time.game.Model.Rhythm;

    opens com.time.game.Controller.GamePlay to javafx.fxml;
    exports com.time.game.Controller.GamePlay;


    opens com.time.game.Controller.Profile to javafx.fxml;
    exports com.time.game.Controller.Profile;

    opens com.time.game.Controller to javafx.fxml;
    exports com.time.game.Controller to javafx.fxml;
    exports com.time.game.GameLogic.Rhythm;

    exports com.time.game.Model.Level;
    exports com.time.game.GameLogic.Scorer;
    exports com.time.game.Controller.EndOfLevel;
    opens com.time.game.Controller.EndOfLevel to javafx.fxml;

}



