package com.example.skiplegday.controller;

import com.example.skiplegday.view.AllenamentoHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class CreateAllenamentoController {
    @FXML
    Text scegliNomeText;
    @FXML
    TextField fieldCreateNameAllenamento;
    @FXML
    VBox vBoxTuoAllenamento;
    @FXML
    ScrollPane scrollPaneEsercizi;
    public void saveAllenamentoAction(ActionEvent actionEvent) throws IOException {
        //aggiungo nel database creo fxml con label e immagine avente come nome questo textField  !!!!!!!!!!!!!!!!!!
        if(fieldCreateNameAllenamento.getText().equals("") || vBoxTuoAllenamento.getChildren().size() == 0){
            return;
        }
        SceneSecondaryHandler.getInstance().aggiungiSchedaPersonaleScene(fieldCreateNameAllenamento.getText());
        SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
    }
    public void initialize(){
        SceneSecondaryHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        SceneSecondaryHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
    }
    public void indietroAction(ActionEvent actionEvent) {
        SceneSecondaryHandler.getInstance().CreateLastScene();
    }
}
