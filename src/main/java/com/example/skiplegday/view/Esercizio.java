package com.example.skiplegday.view;

import com.example.skiplegday.Utils;
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
    //in modo da non doverle ricaricare ogni volta che clicco su un esercizio, potendomi inoltre memorizzare lo stato passato, ripartendo sempre da quello
    private Scene scene;
    public Esercizio(String text){
        setText(text);
        getStyleClass().add("esercizio");
        setOnMouseClicked(event -> {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); //prendo il mainStage per poterlo passare alla classe popup
            try {
                String nomeEsercizio = getText();
                Parent root = popupMap.get(nomeEsercizio);  //accedo alla map col nome di questo esercizio per vedere se è presente un Parent gia caricato in precedenza
                if (root == null) {
                    root = Utils.loadRootFromFXML("popupEsercizio.fxml");
                    popupMap.put(nomeEsercizio, root);  //se non è presente carico un nuovo fxml.load() e lo metto nella map
                }
                if (scene==null){
                    scene = new Scene(root);
                }
                else {
                    PopupHandler.getInstance().setvBoxDatiEsercizi((VBox) root.lookup("#vBoxDatiEsercizi")); //se non lo setto qui perdo il riferimento al vBox,
                    //in quanto non caricando il fxml (se è già presente nella map non si carica, ma lo prendo e basta) -> non si crea il controller
                    // associato che setta il Vbox, quindi se prima clicco su un altro esercizio
                    //e poi su questo, il vBox non è settato e non posso aggiungere i dati dell'esercizio.
                    scene.setRoot(root);
                }
                PopupHandler.getInstance().setNomeEsercizio(this.getText()); //passo il nome dell'esercizio alla classe singleton che gestisce le finestre popup
                Popup popup= new Popup(mainStage, scene);
                SceneHandler.getInstance().setCSSForScene(scene);
            } catch (IOException e) {
                ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento dell'esercizio");
            }
        });
    }
   /*quando prendo un fxmlloader e faccio il .load(), esso restituisce un oggetto di tipo Parent, che è una superclasse astratta
    per tutti i nodi contenitori nell'albero della scena di JavaFX.  Parent è un'interfaccia per tutti i nodi che possono
    contenere altri nodi, come ad esempio HBox, VBox, AnchorPane. il .load() da un riferimento generico al nodo radice*/
}
