module time {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
//    requires org.testng;
    requires java.sql;

    opens com.time.game.Controller.GamePlay to javafx.fxml;
    opens com.time.game.Controller.Profile to javafx.fxml;
    opens com.time.game.Controller to javafx.fxml;
    opens com.time.game.Controller.EndOfLevel to javafx.fxml;
    opens com.time.game.Controller.Login to javafx.fxml;

    exports com.time.game.Controller to javafx.fxml;
    exports com.time.game.Controller.Login to javafx.fxml;
    exports com.time.game;
    exports com.time.game.Model.Rhythm;
    exports com.time.game.Controller.GamePlay;
    exports com.time.game.Controller.Profile;
    exports com.time.game.Model.Level;
    exports com.time.game.GameLogic.Scorer;
    exports com.time.game.Controller.EndOfLevel;
    exports com.time.game.Model.Profile;
    exports com.time.game.GameLogic.Rhythm;

}



