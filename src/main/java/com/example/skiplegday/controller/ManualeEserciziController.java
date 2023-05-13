package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

public class ManualeEserciziController {
    @FXML
    Button indexesButton;
    @FXML
    ScrollPane scrollPaneManualeEs;
    public void scrollToIndexesAction(ActionEvent actionEvent) {
    }
    public void initialize(){
        SceneSecondaryHandler.getInstance().setScrollPane(scrollPaneManualeEs);
    }
}
