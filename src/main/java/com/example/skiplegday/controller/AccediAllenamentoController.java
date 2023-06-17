package com.example.skiplegday.controller;

import com.example.skiplegday.view.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.io.IOException;

public class AccediAllenamentoController {
    @FXML
    private BorderPane paneAllenamento;  //prima era AnchorPane
    @FXML
    private TextArea textAreaTutorial;
    public void modificaAllenamentoAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().setLastScene();
        SceneSecondaryHandler.getInstance().createCreateSchedaAllenamentoScene(nomeScheda);  //con questa funzione creo e modifico
    }
    public void setPaneAllenamento(Node node){
        paneAllenamento.setLeft(node);
    }
    public void indietroAction(ActionEvent actionEvent) {
        SceneSecondaryHandler.getInstance().CreateLastScene();
    }
    private String nomeScheda;  //setto nomeScheda quando accedo alla scheda personalizzata
    public void setNomeAllenamento(String schedaNome) {
        this.nomeScheda = schedaNome;
    }
    private double shift = 30;
    public void openTutorial(MouseEvent mouseEvent) {
        double width = textAreaTutorial.getPrefWidth();
        double start = textAreaTutorial.getTranslateX() == 0 ? 0 : width+shift;
        double end = textAreaTutorial.getTranslateX() == 0 ? width+shift : 0;

        TranslateTransition transition = new TranslateTransition(Duration.millis(300), textAreaTutorial);
        transition.setFromX(start);
        transition.setToX(end);
        transition.play();
    }
    public void initialize(){   //in modo che appena carico la schermata non si vede il tutorial
        double width = textAreaTutorial.getPrefWidth();
        double start = textAreaTutorial.getTranslateX() == 0 ? 0 : width+shift;
        double end = textAreaTutorial.getTranslateX() == 0 ? width+shift : 0;
        TranslateTransition transition = new TranslateTransition(Duration.millis(1), textAreaTutorial);
        transition.setFromX(start);
        transition.setToX(end);
        transition.play();
    }
}
