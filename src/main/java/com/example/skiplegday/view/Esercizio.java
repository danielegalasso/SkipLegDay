package com.example.skiplegday.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Esercizio extends Text{  //la classe esercizio non è altro che un Text con associato un actionEvent internamente che apre una finestra popup
    private Map<String, Parent> popupMap = new HashMap<String,Parent>();  //con questa struttura riesco a memorizzare le finestre popup già aperte
    //in modo da non doverle ricaricare ogni volta che clicco su un esercizio, potendomi memorizzare lo stato passato, ripartendo sempre da quello
    private Scene scene;
    public Esercizio(String text){
        setText(text);
        setOnMouseClicked(event -> {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                String nomeEsercizio = getText();
                Parent root = popupMap.get(nomeEsercizio);  //accedo alla map col nome di questo esercizio per vedere se è presente un Parent gia caricato in precedenza
                if (root == null) {
                    root = loadRootFromFXML("popupEsercizio.fxml");
                    popupMap.put(nomeEsercizio, root);  //se non è presente carico un nuovo fxml.load() e lo metto nella map
                }
                if (scene==null){
                    scene = new Scene(root);
                }
                else {
                    PopupHandler.getInstance().setvBoxDatiEsercizi((VBox) root.lookup("#vBoxDatiEsercizi")); //se non lo setto qui perdo il riferimento al vBox,
                    //in qunato non caricando il fxml non si crea il controller associato che setta il Vbox, quindi se prima clicco su un altro esercizio
                    //e poi su questo, il vBox non è settato e non posso aggiungere i dati dell'esercizio.
                    scene.setRoot(root);
                }
                PopupHandler.getInstance().setNomeEsercizio(this.getText()); //passo il nome dell'esercizio alla classe singleton che gestisce le finestre popup
                Popup popup= new Popup(mainStage, scene);
            } catch (IOException e) {
                ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento dell'esercizio");
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(Color.BLUE);
                setCursor(Cursor.HAND);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(Color.BLACK);
                setCursor(Cursor.DEFAULT);
            }
        });
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }  /*quando prendo un fxmlloader e faccio il .load(), esso restituisce un oggetto di tipo Parent, che è una superclasse astratta
    per tutti i nodi contenitori nell'albero della scena di JavaFX.  Parent è un'interfaccia per tutti i nodi che possono
    contenere altri nodi, come ad esempio HBox, VBox, AnchorPane. il .load() da un riferimento generico al nodo radice*/
}
