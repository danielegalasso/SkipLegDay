package com.example.skiplegday.view;

import com.example.skiplegday.model.InformazioniEsercizi;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

public class DescrizioneEsercizioHandler {
    private static DescrizioneEsercizioHandler instance= new DescrizioneEsercizioHandler();
    private DescrizioneEsercizioHandler(){}
    public static DescrizioneEsercizioHandler getInstance(){return instance;}
    private Text nomeEsercizioSpiegazione;
    private ImageView immagineEsercizioSpiegazione;
    private ScrollPane scrollPaneEsercizioSpiegazione;
    private String textPulsantePremuto;  //mi serve a prendere il testo del pulsante premuto nel Manuale esercizi per capire come settarlo
    public void setDescrizioneEsercizio(Text nomeEsercizioSpiegazione, ImageView immagineEsercizioSpiegazione, ScrollPane scrollPaneEsercizioSpiegazione){
        this.nomeEsercizioSpiegazione=nomeEsercizioSpiegazione;
        this.immagineEsercizioSpiegazione=immagineEsercizioSpiegazione;
        this.scrollPaneEsercizioSpiegazione=scrollPaneEsercizioSpiegazione;
        this.nomeEsercizioSpiegazione.setText(textPulsantePremuto);
        this.scrollPaneEsercizioSpiegazione.setContent(new Text(InformazioniEsercizi.getInstance().getDescrizioni().get(textPulsantePremuto)));
        this.immagineEsercizioSpiegazione.setImage(new Image(InformazioniEsercizi.getInstance().getImageEsercizio(textPulsantePremuto+"Descrizione")));
    }
    public void setTextPulsantePremuto(String textPulsantePremuto){
        this.textPulsantePremuto=textPulsantePremuto;
    }
}
