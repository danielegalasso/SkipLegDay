package com.example.skiplegday.view;

import com.example.skiplegday.controller.DescrizioneEsercizioController;
import com.example.skiplegday.model.InformazioniEsercizi;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class ManualeEserciziHandler {
    private static final ManualeEserciziHandler instance = new ManualeEserciziHandler();
    private ManualeEserciziHandler() {
    }
    public static ManualeEserciziHandler getInstance() {
        return instance;
    }
    private ScrollPane scrollPane;
    private AnchorPane paneDescrizioneRoot;
    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }
    public void aggiungiManualeEsercizi() {
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();
        scrollPane.setContent(retTextFlow(strings));
    }
    public void setPaneDescrizioneRoot(AnchorPane paneDescrizioneRoot) {
        this.paneDescrizioneRoot = paneDescrizioneRoot;
    }
    public void aggiungiDescrizioneEsercizio(String descrizEs) throws IOException {  //questa funzione viene chiamata quando clicco il DescrizioneEsercizio
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/descrizioneEsercizio.fxml"));
        Node desc = fxmlLoader.load();
        DescrizioneEsercizioController c= fxmlLoader.getController();
        c.loadDati(descrizEs);
        paneDescrizioneRoot.getChildren().setAll(desc);
        desc.setTranslateX(400);
        TranslateTransition transition = new TranslateTransition(Duration.millis(400),desc);
        transition.setByX(-400);
        transition.play();
    }
    public void resetDescrizioneEsercizio() {
        paneDescrizioneRoot.getChildren().clear();
    }
    public void removeDescrizione(){
        Node desc = paneDescrizioneRoot.getChildren().get(0);
        desc.setTranslateX(0);
        TranslateTransition transition = new TranslateTransition(Duration.millis(400),desc);
        transition.setByX(400);
        transition.play();
        transition.setOnFinished(event -> {
            paneDescrizioneRoot.getChildren().clear();
        });
    }
    private TextFlow retTextFlow(ArrayList<String> es) {
        TextFlow t = new TextFlow();
        for (String s: es) {
            t.getChildren().add(new DescrizioneEsercizio(s,false)); //false e true per capire se deve aprire popup o meno
            t.getChildren().add(new Text("\n"));
        }
        return t;
    }
    public void filter(String gruppoMuscolare) {
        ArrayList<String> es=InformazioniEsercizi.getInstance().getEserciziGruppoMuscolare(gruppoMuscolare);
        scrollPane.setContent(retTextFlow(es));
    }
}
