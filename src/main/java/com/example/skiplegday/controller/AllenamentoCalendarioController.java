package com.example.skiplegday.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AllenamentoCalendarioController {
    @FXML
    Text nomeEsercizioText;
    @FXML
    ImageView imageEsercizio;
    @FXML
    VBox vBox;
    public void setNomeEsercizioText(String nomeEsercizio) {
        nomeEsercizioText.setText(nomeEsercizio);
    }
    public void setImageEsercizio(Image imageEsercizio) {
        this.imageEsercizio.setImage(imageEsercizio);
    }
    public void addSerie(Text serie) {
        this.vBox.getChildren().add(serie);
    }
}
