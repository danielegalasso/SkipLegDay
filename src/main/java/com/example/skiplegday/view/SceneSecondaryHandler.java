package com.example.skiplegday.view;

import com.example.skiplegday.controller.GruppoMuscolareController;
import com.example.skiplegday.model.LettoreFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SceneSecondaryHandler {
    private AnchorPane sceneRoot;
    private VBox vBox;  //vbox nelle schede di default
    private ScrollPane scrollPane;
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public void createEserciziScene() throws IOException {
        Node node= (Node) loadRootFromFXML("manualeEsercizi.fxml");
        aggiungiManualeEsercizi(node);
        addAndCenter(node);
    }

    private void aggiungiManualeEsercizi(Node node) {
        TextFlow t = LettoreFile.getInstance().leggiFileCaratteri("files/esercizi.txt");
        scrollPane.setContent(t);
    }

    public void createSchedePredefiniteScene() throws IOException {
        Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        addAndCenter(node);
    }
    public void createPrincipianteScene() throws IOException{
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(node,"schedaPrincipiante.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaPrincipiante.txt"));
        addAndCenter(node);
    }
    public void createIntermedioScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(node,"schedaIntermedio.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaIntermedio.txt"));
        addAndCenter(node);
    }
    public void createAvanzatoScene(Stage mainStage) throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(node,"schedaAvanzato.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaAvanzato.txt"));
        addAndCenter(node);
    }
    public void createCorpoLiberoScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(node,"schedaCorpoLibero.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaCorpoLibero.txt"));
        addAndCenter(node);
    }
    public void createTotalBodyScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(node,"schedaTotalBody.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaTotalBody.txt"));
        addAndCenter(node);
    }
    public void createResistenzaScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(node,"schedaResistenza.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaResistenza.txt"));
        addAndCenter(node);
    }
    private void addAndCenter(Node node){
        sceneRoot.getChildren().setAll(node);
        AnchorPane.setTopAnchor(node,10.0);
        AnchorPane.setBottomAnchor(node, 10.0);
        AnchorPane.setLeftAnchor(node, 10.0);
        AnchorPane.setRightAnchor(node, 10.0);
    }
    public void setHomeSceneRoot(AnchorPane sceneRoot) {
        this.sceneRoot = sceneRoot;
    }
    public void setSchedeDefaultSceneRoot(VBox vBox) { this.vBox = vBox;}
    public void setScrollPane(ScrollPane scrollPane) {this.scrollPane = scrollPane;}
    private<T> T setControllerAndLoadFromFXML(String resourceName, List<String> l) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        T group=fxmlLoader.load();
        GruppoMuscolareController g=fxmlLoader.getController();
        g.setGruppoMuscolare(l);
        return group;
    }
    private void aggiungiSchedaPredefinita(Node node, String schedaName) throws IOException {
        List<List<String>> l=LettoreFile.getInstance().leggiSchedaDefault("files/"+schedaName);
        for(int i=0;i<l.size();i++) {
            vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml",l.get(i)));
        }
    }
}
