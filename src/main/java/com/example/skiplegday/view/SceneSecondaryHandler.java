package com.example.skiplegday.view;

import com.example.skiplegday.Utils;
import com.example.skiplegday.controller.*;
import com.example.skiplegday.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.*;

public class SceneSecondaryHandler {
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private AnchorPane sceneRoot;
    private VBox vBox;  //vbox nelle schede di default
    private LinkedList<Node> lastScene= new LinkedList<>();
    private Map<String,Parent> sceneMap= new HashMap<>();

    //SCENE PRINCIPALI---------------------------------------------------
    public void createDatiPersonaliScene() throws IOException {
        Node node = Utils.loadRootFromFXML("datiPersonali.fxml");
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createGuidaScene() throws IOException {
        Node node = Utils.loadRootFromFXML("guida.fxml");
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createValutaciScene() throws IOException {
        Node node = Utils.loadRootFromFXML("recensione.fxml");
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createSchedePersonaliScene() throws IOException {
        Parent root = sceneMap.get("schedePersonali");
        //il node lo carico già in SceneHandler quando avvio la homeScene, altrimenti se apro prima esercizi, e faccio importa, il gridPane è null
        //in quanto non l'ho ancora caricato e mi da nullPointerException
        if(root == null) {
            root = Utils.loadRootFromFXML("schedePersonali.fxml");
            sceneMap.put("schedePersonali", root);
            //query al db per ottenere le schede personali dell'utente
            GetSchedeService getSchedeService = new GetSchedeService();
            getSchedeService.setDati(UtenteAttuale.getInstance().getUsername());
            getSchedeService.restart();
            getSchedeService.setOnSucceeded(event -> {  //va fatto solo all'inizio, e non ogni volta che apro schedePersonali altrimenti sarebbe inefficiente
                ArrayList<String> schede = getSchedeService.getValue();
                for (int i = 0; i < schede.size(); i++) {
                    try {
                        GridPaneAllenamentiHandler.getInstance().aggiungiSchedaPersonaleScene(schede.get(i));
                    } catch (IOException e) {
                        e.printStackTrace();
                        ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento delle schede personali");
                    }
                }
            });
        }
        addAndCenter(root);
        ShortCut.addNewSchedeShortCut(root);
        sceneRoot.requestFocus();
        setLastScene();
        simulateTabPress();
    }
    public void createSchedePredefiniteScene() throws IOException {  //CAMBIARE NOME SCHEDEDEFAULT
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/schedeDefault.fxml"));
        //sarebbe un gridPane, prendo il controller associato e ci aggiungo le schedeDefault col metodo addSchedaDefault
        Node node = loader.load();
        SchedeDefaultController controller = loader.getController();
        ArrayList<String> nomiSchedeDef = nomiSchedePredefinite();
        for(int i=0;i<2;++i){
            for(int j=0;j<3;++j){
                int linearizzato= (i)*3+j;  //devo linearizzare la matrice 2x3 in un array di 6 elementi
                controller.addSchedaDefault(caricaSchedeDefault(nomiSchedeDef.get(linearizzato),linearizzato),i,j);
            }
        }
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    private ArrayList<String> nomiSchedePredefinite(){
        ArrayList<String> nomiSchedeDef = new ArrayList<>();
        nomiSchedeDef.add("PRINCIPIANTE");nomiSchedeDef.add("INTERMEDIO");nomiSchedeDef.add("AVANZATO");nomiSchedeDef.add("TOTAL BODY");nomiSchedeDef.add("POWERLIFTING");nomiSchedeDef.add("FORZA");
        return nomiSchedeDef;
    }
    private Node caricaSchedeDefault(String nomeScheda,int n) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/cardScheda.fxml"));
        Node node = loader.load();
        CardSchedaController controller = loader.getController(); //prendo il controller per settare i componenti
        controller.setLabelCardScheda(nomeScheda);
        controller.setSchedaDefault(); //cosi non mi fa modificare la scheda (rimuoverla)
        controller.setImageViewSchedaPersonalizzata(new Image(getClass().getResource("/com/example/skiplegday/imageSchede/def"+n+".jpg").toExternalForm())); //!!!!!!!!!!!! qua c'era random
        //le schede di default hanno delle immagini di default a differenza di quelle personalizzate
        return node;
    }

    public void createStatisticheScene() throws IOException {
        Node node = (Node) Utils.loadRootFromFXML("statistiche.fxml");  //cosi carico ogni volta un nuovo nodo, inefficiente
        StatisticheHandler.getInstance().loadCalendar();
        StatisticheHandler.getInstance().loadGrafoRadar();
        StatisticheHandler.getInstance().loadGrafo();
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createEserciziScene() throws IOException {
        Parent root = sceneMap.get("manualeEsercizi");
        if(root == null) {
            root = Utils.loadRootFromFXML("manualeEsercizi.fxml");
            sceneMap.put("manualeEsercizi", root);
        }//altrimenti pensa caricare ogni volta tutti gli esercizi, cosi lo faccio solo una volta
        ManualeEserciziHandler.getInstance().aggiungiManualeEsercizi();
        ManualeEserciziHandler.getInstance().resetDescrizioneEsercizio();
        addAndCenter(root);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createObiettiviScene() throws IOException {
        //Node node = loadRootFromFXML("obiettivi.fxml");
        Node node= loadRootFromFXMLandSetController("obiettivi.fxml");
        ProgressiObiettivi.getInstance().loadObiettivi();
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createSchedaDefaultNameScene(String text)  throws IOException{
        Node node = Utils.loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(text+".txt");
        addAndCenter(node);
    }
    private void aggiungiSchedaPredefinita(String schedaName) throws IOException {
        List<List<String>> l=LettoreFile.getInstance().leggiSchedaDefault("files/"+schedaName);
        for(int i=0;i<l.size();i++) {
            Node allenamento= Utils.loadRootFromFXML("schedaAllenamento.fxml");
            SchedaAllenamentoHandler.getInstance().setAllenamentoPredef(l.get(i));
            vBox.getChildren().add(allenamento);
        }
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    //CREA SCHEDA PERSONALIZZATA ------- (ALLENAMENTI PERSONALIZZATI)---
    public void createCreateSchedaAllenamentoScene(String s) throws IOException {
        Node node = loadRootFromFXMLandSetController("createSchedaAllenamento.fxml");
        CreateSchedaAllenamentoHandler.getInstance().caricaManualeEsercizi();
        if (!s.equals("")){ //se non è vuoto vuol dire che è stato chiamato dalla modifica
            CreateSchedaAllenamentoHandler.getInstance().caricaNomeAllenamento(s);
            CreateSchedaAllenamentoHandler.getInstance().caricaScegliNomeText("Modifica nome allenamento (opzionale) ");
            CreateSchedaAllenamentoHandler.getInstance().setNomeButtonModifica();
        }
        CreateSchedaAllenamentoHandler.getInstance().caricaEserciziVbox();
        addAndCenter(node);
        ShortCut.addSaveShortCut(node);
        sceneRoot.requestFocus();
        simulateTabPress();
        simulateTabPress();
    }
    //per accedere all'allenamento pers quando sono sulla label
    public void accediSchedaPersonalizzataScene(String schedaNome) throws IOException {  //meglio chiamarlo accedi allenamento
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/accediAllenamento.fxml"));
        Node node=loader.load();
        AnchorPane allenamento = (AnchorPane) Utils.loadRootFromFXML("schedaAllenamento.fxml");
        allenamento.setPrefWidth(500);
        allenamento.setPrefHeight(480);

        //tramite il NomeAllenamento faccio una query al database che mi restituisce la lista degli esercizi da cui è composto e le rispettive immagini
        ArrayList<String> esercizi = new ArrayList<>();
        GetEserciziSchedaService getEserciziSchedaService = new GetEserciziSchedaService();
        getEserciziSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),schedaNome);
        getEserciziSchedaService.restart();
        getEserciziSchedaService.setOnSucceeded(event -> {
            esercizi.addAll(getEserciziSchedaService.getValue());
            esercizi.add(0, schedaNome);  //<-----------------------------------------NOME ALLENAMENTO
            try {
                SchedaAllenamentoHandler.getInstance().setAllenamentoPers(esercizi);
                AccediAllenamentoController accediAllenamentoController = loader.getController();
                accediAllenamentoController.setPaneAllenamento(allenamento);
                accediAllenamentoController.setNomeAllenamento(schedaNome);
                addAndCenter(node);
            } catch (IOException e) {
                ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento dell'allenamento");
            }
        });
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createAllenamentoCalendarioScene(ArrayList<Object> esercizi) throws IOException {
        HashMap<String, ArrayList<Serie>> eserciziSerie = (HashMap<String, ArrayList<Serie>>) esercizi.get(1); //in 0 c'è nomeScheda
        ArrayList<String> keysNomiEs = new ArrayList<>(eserciziSerie.keySet());
        //System.out.println("chiavi " + keysNomiEs);
        Node node = Utils.loadRootFromFXML("vBoxEsercizi.fxml");
        ScrollPane scrollPaneRoot= (ScrollPane) node.lookup("#scrollPaneRoot");
        GridPane pane = new GridPane();
        pane.getStyleClass().add("gridpane-allenamento");
        pane.setPrefWidth(830);
        pane.setPrefHeight(575);
        scrollPaneRoot.setContent(pane);
        int colonna = 0;
        int riga = 0;

        for (int i = 0; i < keysNomiEs.size(); i++) {
            String nomeEs = keysNomiEs.get(i);
            ArrayList<Serie> serie = eserciziSerie.get(nomeEs);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/allenamentoCalendario.fxml"));
            Node allenamento = loader.load();
            AllenamentoCalendarioController c = loader.getController();
            c.setNomeEsercizioText(nomeEs);
            c.setImageEsercizio(Utils.loadImage(nomeEs));
            for (Serie s : serie) {
                Text t = new Text("Peso: "+ s.getPeso() + "kg    Ripetizioni: "+s.getRipetizioni()+"    Recupero: "+s.getRecuperoSecondi()+"sec");
                //System.out.println();
                c.addSerie(t);
            }
            pane.add(allenamento, colonna, riga);

            colonna++;
            if (colonna >= 2) {
                colonna = 0;
                riga++;
            }
        }
        addAndCenter(node);
    }
    // METODI UTILI ----------------------------------------------------
    private Node loadRootFromFXMLandSetController(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        Node n= fxmlLoader.load();
        n.getProperties().put("foo", fxmlLoader.getController());
        return n;
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

        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void setLastScene() {   // Ottieni il nodo FXML attualmente presente nel sceneRoot
        Node currentFXMLNode = sceneRoot.getChildren().get(0);
        lastScene.add(currentFXMLNode);
    }
    public void setHomeSceneRoot(AnchorPane sceneRoot) {
        this.sceneRoot = sceneRoot;
    }
    public void setSchedeDefaultSceneRoot(VBox vBox) { this.vBox = vBox;}
    public void clearAll() {
        sceneMap.clear();
        GridPaneAllenamentiHandler.getInstance().setPosInitialGridPane();
    }
    private void simulateTabPress(){
        Robot robot = new Robot();
        // Simula la pressione del tasto Tab
        robot.keyPress(KeyCode.TAB);
        robot.keyRelease(KeyCode.TAB);
    }
}