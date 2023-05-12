package com.example.skiplegday.controller;

import com.example.skiplegday.view.PopupHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class PopupEsercizioController {
    @FXML
    Text nomeEsercizio;
    @FXML
    VBox vBoxDatiEsercizi;
    @FXML
    Button addDatiEsButton;
    public void initialize(){
        PopupHandler.getInstance().setvBoxDatiEsercizi(vBoxDatiEsercizi);
        PopupHandler.getInstance().setNomeEsercizio(nomeEsercizio);
    }
    public void addDatiEsAction(ActionEvent actionEvent) throws IOException {
        PopupHandler.getInstance().addDatiEsercizio();
    }
    public void salvaProgressiAction(ActionEvent actionEvent) {
        //galasso bastardo
    }
}
