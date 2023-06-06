package com.example.skiplegday.controller;

import com.example.skiplegday.view.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class AllenamentoPersonaleController {
    private String nomeAllenamento;
    @FXML
    private BorderPane paneAllenamento;  //prima era AnchorPane
    @FXML
    private AnchorPane paneListaEs;
    @FXML
    private TextArea textAreaTutorial;
    public void modificaAllenamentoAction(ActionEvent actionEvent) throws IOException {
        //System.out.println(nomeScheda);
        SceneSecondaryHandler.getInstance().setLastScene();
        SceneSecondaryHandler.getInstance().createCreateAllenamentoScene(nomeScheda);  //con questa funzione creo e modifico
        //dentr createAlleamentoScene ho anche la funzione createManuale
        /*
        SceneSecondaryHandler.getInstance().caricaEserciziVbox(nomeScheda);
        SceneSecondaryHandler.getInstance().caricaNomeAllenamento(nomeScheda);*/
        //CreateAllenamentoHandler.getInstance().caricaEserciziVbox(nomeScheda);
        //CreateAllenamentoHandler.getInstance().caricaNomeAllenamento(nomeScheda);

    }
    public void setPaneAllenamento(Node node){
        paneAllenamento.setCenter(node);
    }
    public void indietroAction(ActionEvent actionEvent) {
        SceneSecondaryHandler.getInstance().CreateLastScene();
    }
    private String nomeScheda;  //setto nomeScheda quando accedo alla scheda personalizzata
    public void setNomeAllenamento(String schedaNome) {
        this.nomeScheda = schedaNome;
    }

    public void openTutorial(MouseEvent mouseEvent) {
        double start = textAreaTutorial.getTranslateX() == 0 ? 0 : 157+6;
        double end = textAreaTutorial.getTranslateX() == 0 ? 157+6 : 0;

        TranslateTransition transition = new TranslateTransition(Duration.millis(300), textAreaTutorial);
        transition.setFromX(start);
        transition.setToX(end);
        transition.play();
    }
    public void initialize(){   //in modo che appena carico la schermata non si vede il tutorial
        double start = textAreaTutorial.getTranslateX() == 0 ? 0 : 157+6;
        double end = textAreaTutorial.getTranslateX() == 0 ? 157+6 : 0;
        TranslateTransition transition = new TranslateTransition(Duration.millis(1), textAreaTutorial);
        transition.setFromX(start);
        transition.setToX(end);
        transition.play();
    }
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
    /*
    public void initialize(){
        SceneSecondaryHandler.getInstance().setAnchorPaneAllenamento(paneAllenamento);
    }
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

    }*/
