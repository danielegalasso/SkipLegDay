package com.example.skiplegday.view;

import com.example.skiplegday.controller.SchedaPersonaleController;
import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.model.LettoreFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SceneSecondaryHandler {
    private AnchorPane sceneRoot;
    private VBox vBox;  //vbox nelle schede di default
    private ScrollPane scrollPane;
    private GridPane gridPane; //per le schedePersonali
    private Node lastScene;
    private Map<String,Parent> sceneMap= new HashMap<>();
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    //SCENE PRINICIPALI---------------------------------------------------
    public void createSchedePersonaliScene() throws IOException {
        Parent root = sceneMap.get("schedePersonali");
        if(root == null) {
            root = loadRootFromFXML("schedePersonali.fxml");
            sceneMap.put("schedePersonali", root);
        }
        //Node node= (Node) loadRootFromFXML("schedePersonali.fxml");
        addAndCenter(root);
    }
    public void createSchedePredefiniteScene() throws IOException {
        Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        addAndCenter(node);
    }
    //DA FARE STATISTICHE
    public void createEserciziScene() throws IOException {
        Node node= (Node) loadRootFromFXML("manualeEsercizi.fxml"); //non voglio far caricare sempre un nuovo nodo
        //LO CAMBIERO
        aggiungiManualeEsercizi(node);
        addAndCenter(node);   //MOMENTANEO, POI MI SETTO LE COSE DA DANIELE, E LE CARICO
    }
    //FARE OBIETTIVI
    public void createDatiPersonaliScene() throws IOException {
        Node node = (Node) loadRootFromFXML("datiPersonali.fxml");
        addAndCenter(node);
    }
    public void createValutaciScene() throws IOException {
        Node node = (Node) loadRootFromFXML("recensione.fxml");
        addAndCenter(node);
    }
    //SCHEDE DEFAULT---------------------------------------------
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
    private void aggiungiSchedaPredefinita(String schedaName) throws IOException {
        List<List<String>> l=LettoreFile.getInstance().leggiSchedaDefault("files/"+schedaName);
        for(int i=0;i<l.size();i++) {
            Node allenamento= (Node) loadRootFromFXML("allenamento.fxml");
            AllenamentoHandler.getInstance().setAllenamentoPredef(l.get(i));
            vBox.getChildren().add(allenamento);
        }
    }
    //MANUALE ESERCIZI--------------------------------------------(anche per aggiungere allenamenti)
    private void aggiungiManualeEsercizi(Node node) {
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();
        TextFlow t = new TextFlow();
        for (String s: strings) {
            t.getChildren().add(new DescrizioneEsercizio(s));  //qua ci va DESCRIZIONEESERCIZIO !!!!
            t.getChildren().add(new Text("\n"));
        }
        scrollPane.setContent(t);
    }
    public void createDescrizioneEsercizioScene() throws IOException {
        Node node = (Node) loadRootFromFXML("descrizioneEsercizio.fxml");  //quando aggiungo l'fxml si attiva in automatico il controller associato che fa tutto
        //funzione che mi prende da database tutti gli allenamenti e me li popola
        addAndCenter(node);
    }
    //METODI UTILI -----------------------------------------------------
    private void addAndCenter(Node node){
        sceneRoot.getChildren().setAll(node);
        AnchorPane.setTopAnchor(node,10.0);
        AnchorPane.setBottomAnchor(node, 10.0);
        AnchorPane.setLeftAnchor(node, 10.0);
        AnchorPane.setRightAnchor(node, 10.0);
    }
    public void CreateLastScene() {
        sceneRoot.getChildren().setAll(lastScene);
    }
    public void setLastScene() {
        // Ottieni il nodo FXML attualmente presente nel sceneRoot
        Node currentFXMLNode = sceneRoot.getChildren().get(0);
        lastScene = currentFXMLNode;
    }
    public void setHomeSceneRoot(AnchorPane sceneRoot) {
        this.sceneRoot = sceneRoot;
    }
    public void setSchedeDefaultSceneRoot(VBox vBox) { this.vBox = vBox;}
    public void setScrollPane(ScrollPane scrollPane) {this.scrollPane = scrollPane;}
    public void clearAll() {
        sceneMap.clear();
    }
    //-
  //SCHEDA PERSONALE (allenamenti personali) --------------------------------------
    private List<String> mieiAllenamenti = new ArrayList<>();  //ste due funzioni ignoratele, devo provare una cosa
    private String nameAllenamento(String nameAllenamento) {
        return nameAllenamento;
    }
    private int columnCount = 2;//gridPane.getColumnCount();
    private int rowIndex = 0;  //quando ricclicco su scheda personale le resetto, finche non prendo le cose da daniele
    private int columnIndex = 0;
    public void aggiungiSchedaPersonaleScene(String nameExercise) throws IOException {
        //Node node = (Node) loadRootFromFXML("schedaPersonale.fxml"); non posso farla con questa funzione perche mi serve
        //controller associato
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/schedaPersonale.fxml"));
        Node node = loader.load();
        SchedaPersonaleController controller = loader.getController();
        controller.setLabelSchedaPersonalizzata(nameExercise);
        addSchedaNextPositionGridPane(node);
    }
    private void addSchedaNextPositionGridPane(Node node) {
        gridPane.add(node, columnIndex, rowIndex);
        columnIndex++;
        if (columnIndex >= columnCount) {
            columnIndex = 0;
            rowIndex++;
        }
    }
    public void repositionSchede(List<Node> children){
        columnCount = gridPane.getColumnCount();
        rowIndex = 0;
        columnIndex = 0;
        for (Node node : children) {
            // Riposiziona il nodo nel GridPane
            addSchedaNextPositionGridPane(node);
        }
    }
    public void setGridPaneSchede(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    //--------------------  CREA SCHEDA PERSONALIZZATA   (ALLENAMENTI PERSONALIZZATI)-----
    private ScrollPane scrollPaneEsercizi;
    private VBox vBoxTuoAllenamento;
    public void createCreateAllenamentoScene() throws IOException {
        Node node = (Node) loadRootFromFXML("createAllenamento.fxml");
        //Node manuale = (Node) loadRootFromFXML("manualeEsercizi.fxml");
        //paneEserciziAdd.getChildren().setAll(manuale);
        //NELLO SCROLLPANE NON DEVO CARICARE IL MANUALE ESERCIZI, CARICARE GLI ESERCIZI OTTENUTI CON LA FUNZIONE DI DANIELE
        //E PRIMA DI CARICARLI CREO UN HBOX CON SPIEGAZIONE ESERCIZIO E A DESTRA BUTTON PER AGGIUNGERE
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();
        TextFlow t = new TextFlow();
        for (String s: strings) {
            HBox hBox = new HBox();
            Button button = new Button("Aggiungi");
            DescrizioneEsercizio desc= new DescrizioneEsercizio(s);
            button.setOnMouseClicked(event -> {
                //addvBoxIfUnique(desc.getText());
                addvBoxIfUnique(desc.getText());
                    });
            hBox.getChildren().addAll(desc,button);
            //t.getChildren().add(new DescrizioneEsercizio(s));  //qua ci va DESCRIZIONEESERCIZIO !!!!
            t.getChildren().add(hBox);
            t.getChildren().add(new Text("\n"));
        }
        scrollPaneEsercizi.setContent(t);
        addAndCenter(node);
    }
    private void addvBoxIfUnique(String text) {
        boolean isUnique = true;
        for (Node hboxs : vBoxTuoAllenamento.getChildren()) {
            HBox hbox = (HBox) hboxs;
            String exercise = ((Text)hbox.getChildren().get(0)).getText();
            if (exercise.equals(text)) {
                isUnique = false;
                break;
            }
        }
        if (isUnique) {  //SE NON è PRESENTE CREO L'HOB CON ESERCIZIO E BUTTON RIMUOVI
            HBox hbox = new HBox();
            Button b= new Button("Rimuovi");
            b.setOnMouseClicked(event -> {
                vBoxTuoAllenamento.getChildren().remove(hbox);
            });
            hbox.getChildren().addAll(new DescrizioneEsercizio(text),b);
            vBoxTuoAllenamento.getChildren().add(hbox);
        }
    }
    public void setScrollPaneEserciziAdd(ScrollPane scrollPaneEsercizi) {
        this.scrollPaneEsercizi=scrollPaneEsercizi;
    }
    public void setVBoxTuoAllenamento(VBox vBoxTuoAllenamento) {
        this.vBoxTuoAllenamento = vBoxTuoAllenamento;
    }
    //per accedere all'allenamento pers quando sono sulla label
    public void accediSchedaPersonalizzataScene(String text) throws IOException {  //meglio chiamarlo accedi allenamento
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        Node allenamento= (Node) loadRootFromFXML("allenamento.fxml");
        //tramite il NomeAllenamento faccio una query al database che mi restituisce la lista degli esercizi da cui è composto e le rispettive immagini
        List<String> esercizi = new ArrayList<>();
        esercizi.add(text); esercizi.add("esercizio2"); esercizi.add("esercizio3");
        AllenamentoHandler.getInstance().setAllenamentoPers(esercizi);
        vBox.getChildren().add(allenamento);
        addAndCenter(node);
    }

    //----------------------------------------------------
}