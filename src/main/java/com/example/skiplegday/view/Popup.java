package com.example.skiplegday.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup extends Stage {  //è un normale stage su cui poi ci caricherò un fxml che sarà la finestra popup
    private Scene scene;
    public Popup(Stage ownerStage, Node node) {
        if(scene == null) {
            scene = new Scene((Parent) node);
        }
        else   //se non c'è la creo altrimenti la modifico
            scene.setRoot((Parent) node);
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
