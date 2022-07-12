package com.javafx.gui;
import com.core.logic.Level.LevelDriver;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import javax.swing.text.DefaultCaret;
import java.awt.*;

public class TimeController {
    @FXML
    private Label welcomeText;
    @FXML
    private Rectangle tapPad;
    @FXML
    private Rectangle beat1;
    @FXML
    private Rectangle beat2;
    @FXML
    private Rectangle beat3;
    @FXML
    private Rectangle beat4;

    @FXML
    private TextArea textArea;
    @FXML
    private Line sampleLine;
    @FXML
    private Line userLine;
    @FXML
    private TextArea score;



    @FXML
    protected void onPlayButtonClick() {
        // start application driver
        Thread t1 = new Thread(LevelDriver::playLevel);
        t1.start();
    }

    @FXML
    public void showScore(double score){
        textArea.setText("Score: "+score*100 +"%");
    }





}