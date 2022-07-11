package com.javafx.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import com.core.logic.Main.Main;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(){
        welcomeText.setText("You know how to push my buttons");
        String[] args = {};
        Main.main(args);

    }
}