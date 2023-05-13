package com.example.skiplegday.controller;

import com.example.skiplegday.view.Esercizio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
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
    public void setGruppoMuscolare(List<String> l) throws IOException {
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size()-1;++i){
            vBoxListaEsercizi.getChildren().add(new Esercizio(l.get(i)));
        }
        imageGruppoMuscolare.setImage(new Image(getClass().getResource(l.get(l.size()-1)).openStream()));
        //se queste robe le metto in un path, non devo scrivere tutto il bordello dei file .txt
    }
    public Esercizio converterTextEsercizio(String nomeEsercizio){
        return new Esercizio(nomeEsercizio);
    }
    public void importaSchedaAction(ActionEvent actionEvent) {
        //da fare con daniele gay
    }
}
