package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class SchedePersonaliController {
    @FXML
    GridPane gridPane;
    @FXML
    Button aggiungiEsercizioButton;
    public void aggiungiEsercizioAction(ActionEvent actionEvent) throws IOException {
        //SceneSecondaryHandler.getInstance().aggiungiSchedaPersonaleScene();  non la devo aggiungere
        //prima la creo, e quando premo salva la aggiungo
        SceneSecondaryHandler.getInstance().setLastScene();
        SceneSecondaryHandler.getInstance().createCreateAllenamentoScene();
    }
    public void initialize(){
        SceneSecondaryHandler.getInstance().setGridPaneSchede(gridPane);
    }
}
