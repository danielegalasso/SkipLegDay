package com.example.skiplegday.view;

import com.example.skiplegday.Utils;
import com.example.skiplegday.controller.DescrizioneEsercizioController;
import com.example.skiplegday.model.InformazioniEsercizi;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
    public void aggiungiManualeEsercizi() throws IOException {
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();
        scrollPane.setContent(retVbox(strings));
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
    private VBox retVbox(ArrayList<String> esercizi) throws IOException {
        VBox vb = new VBox();
        for (String s: esercizi) {
            Node node1 = Utils.loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setDescrizioneEsercizio(Utils.loadImage(s),new DescrizioneEsercizio(s,false));
            EsercizioGeneraleHandler.getInstance().removeButton();
            vb.getChildren().add(node1);
        }
        return vb;
    }
    public void filter(String gruppoMuscolare) throws IOException {
        ArrayList<String> es=InformazioniEsercizi.getInstance().getEserciziGruppoMuscolare(gruppoMuscolare);
        scrollPane.setContent(retVbox(es));
    }

}
