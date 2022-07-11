module time {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.testng;


    opens com.javafx.gui to javafx.fxml;
    exports com.javafx.gui;
    exports com.core.logic.Rhythm;
}



