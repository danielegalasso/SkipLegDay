package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class vBoxEserciziController {
    @FXML
    private VBox vBox;
    public void initialize() {
        SceneSecondaryHandler.getInstance().setSchedeDefaultSceneRoot(vBox);
    }
}
