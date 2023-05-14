package com.example.skiplegday.controller;

import com.example.skiplegday.view.AllenamentoHandler;
import com.example.skiplegday.view.Esercizio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class AllenamentoController {
    @FXML
    Text idGruppoMuscolare;
    @FXML
    VBox vBoxListaEsercizi;
    @FXML
    Button importaButton;
    @FXML
    ImageView imageGruppoMuscolare;
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public Esercizio converterTextEsercizio(String nomeEsercizio){
        return new Esercizio(nomeEsercizio);
    }
    public void importaSchedaAction(ActionEvent actionEvent) {
        //da fare con daniele gay
    }
    public void initialize(){
        AllenamentoHandler.getInstance().setAllenamento(idGruppoMuscolare,vBoxListaEsercizi);
    }
}
