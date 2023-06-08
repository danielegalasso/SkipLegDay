package com.example.skiplegday.controller;

import com.example.skiplegday.view.ConfirmationAlert;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

import java.io.IOException;

public class ShortCut {
    public static void addSaveShortCut(Node scene){
        CreateAllenamentoController myController = (CreateAllenamentoController) scene.getProperties().get("foo");
        /*per fare questo ho dovuto inserire nell'fxml <properties> 

         L'elemento <properties> nel file FXML consente di memorizzare coppie chiave-valore come proprietà associate a
         un nodo o a un'interfaccia utente.ti del tuo codice durante l'esecuzione dell'applicazione.Le proprietà sono
         memorizzate in una mappa associata al nodo o all'interfaccia utente e possono essere utilizzate per archiviare informazioni aggiuntive o personalizzate.
         Nel mio caso utilizzando la chiave "foo" posso accedere al controller dell'AnchorPane da altre parti del mio codice
         */

        KeyCombination saveShortcut = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        // Aggiunta dell'handler dell'evento "Ctrl + S" alla scena
        scene.setOnKeyPressed(event -> {
            if (saveShortcut.match(event)) {
                try {
                    myController.saveAllenamentoAction(null);
                } catch (IOException e) {
                    ErrorMessage.getInstance().showErrorMessage("Errore nel salvataggio dell'allenamento");
                }
                event.consume(); // Consuma l'evento per evitare che venga gestito da altri componenti
            }
        });
    }
    public static void addExitShortCut(Scene scene){
        KeyCombination exitShortcut = new KeyCodeCombination(KeyCode.ESCAPE);
        // Aggiunta dell'handler dell'evento "Esci" alla scena
        scene.setOnKeyPressed(event -> {
            if (exitShortcut.match(event)) {
                int res=ConfirmationAlert.showConfirmationAlert("Sei sicuro di voler uscire?","Torna Indietro","Esci");
                if (res==0){
                    System.exit(0);
                }
            }
        });
    }

    public static void addNewSchedeShortCut(Parent scene){ //qui non mi serve il controller devo solo cambiare scene
        KeyCombination newSchedeShortcut = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);
        // Aggiunta dell'handler dell'evento "Ctrl + N" alla scena
        scene.setOnKeyPressed(event -> {
            if (newSchedeShortcut.match(event)) {
                try {
                    SceneSecondaryHandler.getInstance().setLastScene();
                    SceneSecondaryHandler.getInstance().createCreateAllenamentoScene(""); //non lo chiamo dalla modifica ""
                } catch (IOException e) {
                    ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento della scheda");
                }
                event.consume(); // Consuma l'evento per evitare che venga gestito da altri componenti
            }
        });
    }

}
