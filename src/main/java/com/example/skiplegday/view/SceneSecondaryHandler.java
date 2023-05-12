package com.example.skiplegday.view;

import com.example.skiplegday.controller.GruppoMuscolareController;
import com.example.skiplegday.model.LettoreFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SceneSecondaryHandler {
    private AnchorPane sceneRoot;
    private VBox vBox;
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    private<T> T setControllerAndLoadFromFXML(String resourceName, String schedeName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        T group=fxmlLoader.load();
        GruppoMuscolareController g=fxmlLoader.getController();
        g.setGruppoMuscolare(LettoreFile.getInstance().leggiSchedaDefault("files/"+schedeName));
        return group;
    }
    public void createEserciziScene(){
        Node node = (Node) LettoreFile.getInstance().leggiFileCaratteri("files/esercizi.txt");
        addAndCenter(node);
    }
    public void createSchedePredefiniteScene() throws IOException {
        Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        addAndCenter(node);
    }
    public void createPrincipianteScene() throws IOException{
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaPrincipiante.txt"));
        addAndCenter(node);
    }
    public void createIntermedioScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaIntermedio.txt"));
        addAndCenter(node);
    }
    public void createAvanzatoScene(Stage mainStage) throws IOException {
        Popup popup = new Popup(mainStage,loadRootFromFXML("popupEsercizio.fxml"));
        PopupHandler.getInstance().addDatiEsercizio();
    }
    public void createCorpoLiberoScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaCorpoLibero.txt"));
        addAndCenter(node);
    }
    public void createTotalBodyScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaTotalBody.txt"));
        addAndCenter(node);
    }
    public void createResistenzaScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        vBox.getChildren().add(setControllerAndLoadFromFXML("gruppoMuscolare.fxml","schedaResistenza.txt"));
        addAndCenter(node);
    }
    private void addAndCenter(Node node){
        sceneRoot.getChildren().setAll(node);
        AnchorPane.setTopAnchor(node,10.0);
        AnchorPane.setBottomAnchor(node, 10.0);
        AnchorPane.setLeftAnchor(node, 10.0);
        AnchorPane.setRightAnchor(node, 10.0);
    }
    /*   HA SENSO?
    public Popup creaPopup(Stage mainStage) throws IOException {  //VEDERE DOVE METTERE QUESTA FUNZIONE, SE VA BENE QUI(?)
        return new Popup(mainStage,loadRootFromFXML("popupEsercizio.fxml"));
    }*/
    public void setHomeSceneRoot(AnchorPane sceneRoot) {
        this.sceneRoot = sceneRoot;
    }
    public void setSchedeDefaultSceneRoot(VBox vBox) { this.vBox = vBox;}
}
