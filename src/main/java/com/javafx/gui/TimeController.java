package com.javafx.gui;
import com.core.logic.Level.LevelDriver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TimeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onPlayButtonClick() {
        // start application driver
        Thread t1 = new Thread(LevelDriver::playLevel);
        t1.start();
    }

}