package com.example.skiplegday.controller;

import com.example.skiplegday.view.DescrizioneEsercizio;
import com.example.skiplegday.view.EsercizioHandler;
import com.example.skiplegday.view.SceneHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class AllenamentoPersonaleController {
    @FXML
    private AnchorPane paneAllenamento;
    @FXML
    private Button modificaAllenamentoAction;
    @FXML
    private AnchorPane paneListaEs;
    public void modificaAllenamentoAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().setLastScene();
        SceneSecondaryHandler.getInstance().createCreateAllenamentoScene();  //con questa funzione creo e modifico
    }
    public void setPaneAllenamento(Node node){
        paneAllenamento.getChildren().add(node);
    }
    /*
    public void initialize(){
        SceneSecondaryHandler.getInstance().setAnchorPaneAllenamento(paneAllenamento);
    }*/
    public void addEserSchedaAction(ActionEvent actionEvent) throws IOException {
        /*
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();
        //ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();  RIMETTERE DECOMMENTANDOLO!!!!!!!!!!!
        ArrayList<String> strings = new ArrayList<>(); //mi serve come prova, poi lo elimino e tengo lo string di sopra
        strings.add("panca piana manubri");strings.add("croci cavi");strings.add("squat");

        Node n= (Node) paneAllenamento.getChildren().get(0);
        VBox v= (VBox) n.lookup("vBoxListaEsercizi");
        for (String s: strings) {
            Node node1 = (Node) loadRootFromFXML("esercizio.fxml");
            EsercizioHandler.getInstance().setDescrizioneEsercizio(loadImage(s), new DescrizioneEsercizio(s));
            EsercizioHandler.getInstance().setOnMouseClickedEvent(event -> {
                v.getChildren().add(node1);
            },"Aggiungi");
            vBox.getChildren().add(node1);
        }
        scrollPane.setContent(vBox);
        paneListaEs.getChildren().add(scrollPane);
        scrollPane.setTranslateX(200);
        TranslateTransition transition = new TranslateTransition(Duration.millis(200),paneListaEs);
        transition.setByX(-400);
        transition.play();
         */
    }

    public void indietroAction(ActionEvent actionEvent) {
        SceneSecondaryHandler.getInstance().CreateLastScene();
    }
    /*
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    private Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(getClass().getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }*/
}
