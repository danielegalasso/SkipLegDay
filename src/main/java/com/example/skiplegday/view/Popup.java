package com.example.skiplegday.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Popup extends Stage {  //è un normale stage su cui poi ci caricherò un fxml che sarà la finestra popup
    //non la creo singleton in quanto al costruttore devo passargli lo stage, e se è gia presente non lo posso risettare,
    //se la mettessi singleton, ogni volta che la richiamo dovrei fare setStage, ma se ne ha gia uno mi causa eccezione
    public Popup(Stage ownerStage, Scene scene) {
        this.setScene(scene);
        this.setHeight(510);
        this.setWidth(300);
        this.setResizable(false);
        SceneHandler.getInstance().loadFonts();            //facendo cosi aggiungo automaticamente stessi font e css delle altre scene
        //SceneHandler.getInstance().setCSSForScene(scene);
        PopupHandler.getInstance().setSaved(true); //appena la avvio posso chiuderla senza problemi
        this.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra secondaria come modale
        this.initOwner(ownerStage); // Imposta la finestra principale come proprietario della finestra secondaria
        this.show();
        ownerStage.toFront();
        ownerStage.requestFocus();
        this.setOnCloseRequest(e -> {    //lambda expression
            System.out.println("Popup closed");
            if(PopupHandler.getInstance().chekNotSave()){
                e.consume();  //consumo l'evento, in modo tale che la finestra non viene chiusa
                PopupHandler.getInstance().showErrorText("modifiche non salvate");
            }
        });
    }
}
