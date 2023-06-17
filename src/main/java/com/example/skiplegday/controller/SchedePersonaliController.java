package com.example.skiplegday.controller;

import com.example.skiplegday.view.GridPaneAllenamentiHandler;
import com.example.skiplegday.view.InfoTooltip;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class SchedePersonaliController {
    @FXML
    GridPane gridPane;
    @FXML
    Button aggiungiEsercizioButton;
    @FXML
    AnchorPane infoPane;
    public void aggiungiEsercizioAction(ActionEvent actionEvent) throws IOException {
        //prima la creo, e quando premo salva la aggiungo
        SceneSecondaryHandler.getInstance().setLastScene();
        SceneSecondaryHandler.getInstance().createCreateSchedaAllenamentoScene("");
    }
    public void initialize(){
        GridPaneAllenamentiHandler.getInstance().setGridPaneSchede(gridPane);
        InfoTooltip.agganciaTooltip(infoPane,"ShortCut:","CTRL + N per aggiungere una scheda");
    }
}
