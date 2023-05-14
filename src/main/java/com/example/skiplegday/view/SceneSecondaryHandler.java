package com.example.skiplegday.view;

import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.model.LettoreFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();
        TextFlow t = new TextFlow();
        for (String s: strings) {
            t.getChildren().add(new DescrizioneEsercizio(s));
            t.getChildren().add(new Text("\n"));
        }
        scrollPane.setContent(t);
    }
    public void createSchedePredefiniteScene() throws IOException {
        Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        addAndCenter(node);
    }
    public void createPrincipianteScene() throws IOException{
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita("schedaPrincipiante.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","schedaPrincipiante.txt"));
        addAndCenter(node);
    }
    public void createIntermedioScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita("schedaIntermedio.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","schedaIntermedio.txt"));
        addAndCenter(node);
    }
    public void createAvanzatoScene(Stage mainStage) throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita("schedaAvanzato.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","schedaAvanzato.txt"));
        addAndCenter(node);
    }
    public void createCorpoLiberoScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita("schedaCorpoLibero.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","schedaCorpoLibero.txt"));
        addAndCenter(node);
    }
    public void createTotalBodyScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita("schedaTotalBody.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","schedaTotalBody.txt"));
        addAndCenter(node);
    }
    public void createResistenzaScene() throws IOException {
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita("schedaResistenza.txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","schedaResistenza.txt"));
        addAndCenter(node);
    }
    public void createDescrizioneEsercizioScene() throws IOException {
        Node node = (Node) loadRootFromFXML("descrizioneEsercizio.fxml");
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
    private void aggiungiSchedaPredefinita(String schedaName) throws IOException {
        List<List<String>> l=LettoreFile.getInstance().leggiSchedaDefault("files/"+schedaName);
        for(int i=0;i<l.size();i++) {
            Node allenamento= (Node) loadRootFromFXML("allenamento.fxml");
            AllenamentoHandler.getInstance().setAllenamentoPredef(l.get(i));
            vBox.getChildren().add(allenamento);
        }
    }
}