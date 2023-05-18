package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class CreateAllenamentoController {
    @FXML
    TextField fieldCreateNameAllenamento;
    @FXML
    VBox vBoxTuoAllenamento;
    @FXML
    ScrollPane scrollPaneEsercizi;
    public void saveAllenamentoAction(ActionEvent actionEvent) {
        //aggiungo nel databasecreao fxml con label e immagine avente come nome questo textField
    }
    public void initialize(){
        SceneSecondaryHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        SceneSecondaryHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
    }
}
