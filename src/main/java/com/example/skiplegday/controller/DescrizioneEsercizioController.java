package com.example.skiplegday.controller;

import com.example.skiplegday.view.DescrizioneEsercizioHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DescrizioneEsercizioController {
    @FXML
    Text nomeEsercizioSpiegazione;
    @FXML
    ImageView immagineEsercizioSpiegazione;
    @FXML
    ScrollPane scrollPaneEsercizioSpiegazione;
    public void initialize(){
        DescrizioneEsercizioHandler.getInstance().setDescrizioneEsercizio(nomeEsercizioSpiegazione,immagineEsercizioSpiegazione,scrollPaneEsercizioSpiegazione);
    }
}
