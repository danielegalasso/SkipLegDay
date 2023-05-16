package com.example.skiplegday.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup extends Stage {  //è un normale stage su cui poi ci caricherò un fxml che sarà la finestra popup
    //non la creo singleton in quanto al costruttore devo passargli lo stage, e se è gia presente non lo posso risettare,
    //se la mettessi singleton, ogni volta che la richiamo dovrei fare setStage, ma se ne ha gia uno mi causa eccezione
    public Popup(Stage ownerStage, Scene scene) {
        this.setScene(scene);
        this.setHeight(450);
        this.setWidth(300);
        this.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra secondaria come modale
        this.initOwner(ownerStage); // Imposta la finestra principale come proprietario della finestra secondaria
        this.showAndWait();
        ownerStage.toFront();
        ownerStage.requestFocus();
    }
}
