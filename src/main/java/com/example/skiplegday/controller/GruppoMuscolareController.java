package com.example.skiplegday.controller;

import com.example.skiplegday.view.Esercizio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;

public class GruppoMuscolareController {
    @FXML
    Text idGruppoMuscolare;
    @FXML
    VBox vBoxListaEsercizi;
    @FXML
    Button importaButton;
    @FXML
    ImageView imageGruppoMuscolare;
    public void setGruppoMuscolare(List<List<String>> l){
        List<String> list = l.get(0);
        idGruppoMuscolare.setText(list.get(0));
        for(int i=1;i<list.size();++i){
            vBoxListaEsercizi.getChildren().add(new Esercizio(list.get(i)));
        }
    }
    public Esercizio converterTextEsercizio(String nomeEsercizio){
        return new Esercizio(nomeEsercizio);
    }
}
