package com.example.skiplegday.controller;

import com.example.skiplegday.view.ManualeEserciziHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

public class ManualeEserciziController {
    @FXML
    private Button indexesButton;
    @FXML
    private ScrollPane scrollPaneManualeEs;
    @FXML
    private AnchorPane paneDescrizioneRoot;
    public void scrollToIndexesAction(ActionEvent actionEvent) {
        scrollPaneManualeEs.setVvalue(0.0);
    }
    public void initialize(){
        //SceneSecondaryHandler.getInstance().setScrollPane(scrollPaneManualeEs);
        ManualeEserciziHandler.getInstance().setScrollPane(scrollPaneManualeEs);
        ManualeEserciziHandler.getInstance().setPaneDescrizioneRoot(paneDescrizioneRoot);
    }
}
