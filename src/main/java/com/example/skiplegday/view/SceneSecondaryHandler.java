package com.example.skiplegday.view;

import com.example.skiplegday.controller.*;
import com.example.skiplegday.model.*;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
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
                        ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento delle schede personali");
                    }
                }
            });
            //----------------------------------
        }
        addAndCenter(root);
        ShortCut.addNewSchedeShortCut(root);
        sceneRoot.requestFocus();
        setLastScene();
        simulateTabPress();
    }
    public void createSchedePredefiniteScene() throws IOException {
        //Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/schedeDefault1.fxml"));
        Node node = loader.load();
        SchedeDefault1Controller controller = loader.getController();
        ArrayList<String> nomiSchedeDef = new ArrayList<>();
        nomiSchedeDef.add("PRINCIPIANTE");nomiSchedeDef.add("INTERMEDIO");nomiSchedeDef.add("AVANZATO");nomiSchedeDef.add("TOTAL BODY");nomiSchedeDef.add("CORPO LIBERO");nomiSchedeDef.add("RESISTENZA");
        for(int i=0;i<2;++i){
            for(int j=0;j<3;++j){
                int linearizzato= (i)*3+j;
                controller.addSchedaDefault(caricaSchedeDefault(nomiSchedeDef.get(linearizzato),linearizzato),i,j);
            }
        }
        addAndCenter(node);
        sceneRoot.requestFocus();
        //setLastScene(); + pulsante indietroo
        simulateTabPress();
    }
    private Node caricaSchedeDefault(String nomeScheda,int n) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/schedaPersonale.fxml"));
        Node node = loader.load();
        SchedaPersonaleController controller = loader.getController();
        controller.setLabelSchedaPersonalizzata(nomeScheda);
        controller.setSchedaDefault(); //cosi non mi fa modificare la scheda (rimuoverla)
        controller.setImageViewSchedaPersonalizzata(new Image(getClass().getResource("/com/example/skiplegday/imageSchede/def"+n+".jpg").toExternalForm())); //!!!!!!!!!!!! qua c'era random
        return node;
    }

    public void createStatisticheScene() throws IOException {
        Node node = (Node) loadRootFromFXML("statistiche.fxml");  //cosi carico ogni volta un nuovo nodo, inefficiente
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
            root = loadRootFromFXML("manualeEsercizi.fxml");
            sceneMap.put("manualeEsercizi", root);
        }//altrimenti pensa caricare ogni volta tutti gli esercizi, cosi lo faccio solo una volta
        aggiungiManualeEsercizi(root);
        addAndCenter(root);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    //FARE OBIETTIVI
    public void createDatiPersonaliScene() throws IOException {
        Node node = (Node) loadRootFromFXML("datiPersonali.fxml");
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    public void createValutaciScene() throws IOException {
        Node node = (Node) loadRootFromFXML("recensione.fxml");
        addAndCenter(node);
        sceneRoot.requestFocus();
        simulateTabPress();
    }
    //SCHEDE DEFAULT---------------------------------------------
    public void createSchedaDefaultNameScene(String text)  throws IOException{
        Node node = (Node) loadRootFromFXML("vBoxEsercizi.fxml");
        aggiungiSchedaPredefinita(text+".txt");
        //vBox.getChildren().add(setControllerAndLoadFromFXML("allenamento.fxml","principiante.txt"));
        addAndCenter(node);
    }
    private void aggiungiSchedaPredefinita(String schedaName) throws IOException {
        List<List<String>> l=LettoreFile.getInstance().leggiSchedaDefault("files/"+schedaName);
        for(int i=0;i<l.size();i++) {
            Node allenamento= (Node) loadRootFromFXML("allenamento.fxml");
            //allenamento.setPrefHeight(600);
            AllenamentoHandler.getInstance().setAllenamentoPredef(l.get(i));
            //allenamento.setEffect(new DropShadow( 15, Color.DARKSLATEGREY));
            ScrollPane scrollPane= new ScrollPane();
            scrollPane.setEffect(new DropShadow( 15, Color.DARKSLATEGREY));
            scrollPane.setMinHeight(300);
            scrollPane.setMaxHeight(300);
            scrollPane.setContent(allenamento);
            // Applica uno stile personalizzato alla barra di scorrimento
            vBox.getChildren().add(scrollPane);
        }
        sceneRoot.requestFocus();
        simulateTabPress();
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

        sceneRoot.requestFocus();
        simulateTabPress();
    }

    //CREA SCHEDA PERSONALIZZATA ------- (ALLENAMENTI PERSONALIZZATI)---
    public void createCreateAllenamentoScene(String s) throws IOException {
        Node node = (Node) loadRootFromFXMLandSetController("createAllenamento.fxml");
        CreateAllenamentoHandler.getInstance().caricaManualeEsercizi();
        if (!s.equals("")){ //se non è vuoto vuol dire che è stato chiamato dalla modifica
            CreateAllenamentoHandler.getInstance().caricaNomeAllenamento(s);
            CreateAllenamentoHandler.getInstance().caricaScegliNomeText("Modifica nome allenamento (opzionale) ");
            CreateAllenamentoHandler.getInstance().setNomeButtonModifica();
        }
        CreateAllenamentoHandler.getInstance().caricaEserciziVbox("tanto non serve questo parametro");
        addAndCenter(node);
        ShortCut.addSaveShortCut(node);
        sceneRoot.requestFocus();
        simulateTabPress();
        simulateTabPress();
    }
    //per accedere all'allenamento pers quando sono sulla label
    public void accediSchedaPersonalizzataScene(String schedaNome) throws IOException {  //meglio chiamarlo accedi allenamento
        //setLastScene();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/allenamentoPersonale.fxml"));
        Node node=loader.load();
        //Node node = (Node) loadRootFromFXML("allenamentoPersonale.fxml");
        AnchorPane allenamento = (AnchorPane) loadRootFromFXML("allenamento.fxml");
        allenamento.setPrefWidth(500);
        allenamento.setPrefHeight(480);

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

        GridPane pane = new GridPane();
        pane.setPrefWidth(700);
        pane.setPrefHeight(460);
        ScrollPane scrollPane = new ScrollPane(pane);
        scrollPane.setPrefWidth(800);
        scrollPane.setPrefHeight(550);
        pane.setHgap(10);
        pane.setVgap(10);

        int colonna = 0;
        int riga = 0;

        for (int i = 0; i < keysNomiEs.size(); i++) {
            String nomeEs = keysNomiEs.get(i);
            ArrayList<Serie> serie = eserciziSerie.get(nomeEs);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/allenamentoCalendario.fxml"));
            Node allenamento = loader.load();
            AllenamentoCalendarioController c = loader.getController();
            c.setNomeEsercizioText(nomeEs);
            c.setImageEsercizio(loadImage("croci cavi")); //mettere nomeEs !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            for (Serie s : serie) {
                Text t = new Text(s.toString());
                c.addSerie(t);
            }
            pane.add(allenamento, colonna, riga);

            colonna++;
            if (colonna >= 2) {
                colonna = 0;
                riga++;
            }
        }
        addAndCenter(scrollPane);
    }
    // METODI UTILI ----------------------------------------------------
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
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
    public void setHBoxHome(HBox hBoxHome) {this.hBoxHome = hBoxHome;} //è inutile?? mai implementata(?)
    public void setSchedeDefaultSceneRoot(VBox vBox) { this.vBox = vBox;}
    public void setScrollPane(ScrollPane scrollPane) {this.scrollPane = scrollPane;}
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
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    private Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(getClass().getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }
}