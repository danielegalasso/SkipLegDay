package com.example.skiplegday.view;

import com.example.skiplegday.controller.AllenamentoPersonaleController;
import com.example.skiplegday.controller.SchedaPersonaleController;
import com.example.skiplegday.model.*;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class SceneSecondaryHandler {
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private AnchorPane sceneRoot;

    private VBox vBox;  //vbox nelle schede di default
    private ScrollPane scrollPane;
    private HBox hBoxHome; //prova mai implementato(?)
    private LinkedList<Node> lastScene= new LinkedList<>();
    private Map<String,Parent> sceneMap= new HashMap<>();

    //SCENE PRINICIPALI---------------------------------------------------
    public void createSchedePersonaliScene() throws IOException {
        Parent root = sceneMap.get("schedePersonali");
        //il node lo carico già in SceneHandler quando avvio la homeScene, altrimenti se apro prima esercizi, e faccio importa, il gridPane è null
        //in quanto non l'ho ancora caricato e mi da nullPointerException
        if(root == null) {
            root = loadRootFromFXML("schedePersonali.fxml");
            sceneMap.put("schedePersonali", root);
            //--------------------------------
            GetSchedeService getSchedeService = new GetSchedeService();  //devo salvarmi i dati in utente attuale!!!!!!!!!!!!!!!
            getSchedeService.setDati(UtenteAttuale.getInstance().getUsername());
            getSchedeService.restart();
            getSchedeService.setOnSucceeded(event -> {  //va fatto solo all'inizio?????? altrimenti sarebbe inefficiente
                ArrayList<String> schede = getSchedeService.getValue();
                for (int i = 0; i < schede.size(); i++) {
                    try {
                        GridPaneAllenamentiHandler.getInstance().aggiungiSchedaPersonaleScene(schede.get(i));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            //----------------------------------
        }
        addAndCenter(root);
        setLastScene();
    }
    public void createSchedePredefiniteScene() throws IOException {
        Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        addAndCenter(node);
    }
    public void createStatisticheScene() throws IOException {
        Node node = (Node) loadRootFromFXML("statistiche.fxml");  //cosi carico ogni volta un nuovo nodo, inefficiente
        StatisticheHandler.getInstance().loadCalendar();
        addAndCenter(node);
    }
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

    //CREA SCHEDA PERSONALIZZATA ------- (ALLENAMENTI PERSONALIZZATI)---
    public void createCreateAllenamentoScene(String s) throws IOException {
        Node node = (Node) loadRootFromFXML("createAllenamento.fxml");
        CreateAllenamentoHandler.getInstance().caricaManualeEsercizi();
        if (!s.equals("")){ //se non è vuoto vuol dire che è stato chiamato dalla modifica
            CreateAllenamentoHandler.getInstance().caricaNomeAllenamento(s);
            CreateAllenamentoHandler.getInstance().caricaScegliNomeText("Modifica nome allenamento (opzionale) ");
            CreateAllenamentoHandler.getInstance().setNomeButtonModifica();
        }
        CreateAllenamentoHandler.getInstance().caricaEserciziVbox("tanto non serve questo parametro");
        addAndCenter(node);
    }
    //per accedere all'allenamento pers quando sono sulla label
    public void accediSchedaPersonalizzataScene(String schedaNome) throws IOException {  //meglio chiamarlo accedi allenamento
        //setLastScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/allenamentoPersonale.fxml"));
        Node node=loader.load();
        //Node node = (Node) loadRootFromFXML("allenamentoPersonale.fxml");
        Node allenamento = (Node) loadRootFromFXML("allenamento.fxml");
        //tramite il NomeAllenamento faccio una query al database che mi restituisce la lista degli esercizi da cui è composto e le rispettive immagini
        ArrayList<String> esercizi = new ArrayList<>();
        GetEserciziSchedaService getEserciziSchedaService = new GetEserciziSchedaService();
        getEserciziSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),schedaNome);
        getEserciziSchedaService.restart();
        getEserciziSchedaService.setOnSucceeded(event -> {
            esercizi.addAll(getEserciziSchedaService.getValue());
            //esercizi.add("panca piana manubri");esercizi.add("squat"); //eliminare questo rigo
            esercizi.add(0, schedaNome);  //<-----------------------------------------NOME ALLENAMENTO
            try {
                AllenamentoHandler.getInstance().setAllenamentoPers(esercizi);
                AllenamentoPersonaleController allenamentoPersonaleController = loader.getController();
                allenamentoPersonaleController.setPaneAllenamento(allenamento);
                allenamentoPersonaleController.setNomeAllenamento(schedaNome);
                addAndCenter(node);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    // METODI UTILI ----------------------------------------------------
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    private void addAndCenter(Node node){
        sceneRoot.getChildren().setAll(node);
        /*
        AnchorPane.setTopAnchor(node,10.0);
        AnchorPane.setBottomAnchor(node, 10.0);
        AnchorPane.setLeftAnchor(node, 10.0);
        AnchorPane.setRightAnchor(node, 10.0);*/
    }
    public void CreateLastScene() {
        sceneRoot.getChildren().setAll(lastScene.getLast());
        lastScene.removeLast();
    }
    public void setLastScene() {   // Ottieni il nodo FXML attualmente presente nel sceneRoot
        Node currentFXMLNode = sceneRoot.getChildren().get(0);
        lastScene.add(currentFXMLNode);
    }
    public void setHomeSceneRoot(AnchorPane sceneRoot) {
        this.sceneRoot = sceneRoot;
    }
    public void setHBoxHome(HBox hBoxHome) {this.hBoxHome = hBoxHome;} //è inutile?? mai implementata(?)
    public void setSchedeDefaultSceneRoot(VBox vBox) { this.vBox = vBox;}
    public void setScrollPane(ScrollPane scrollPane) {this.scrollPane = scrollPane;}
    public void clearAll() {
        sceneMap.clear();
        GridPaneAllenamentiHandler.getInstance().setPosInitialGridPane();
    }
}