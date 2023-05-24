package com.example.skiplegday.controller;

import com.example.skiplegday.view.EsercizioHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class EsercizioController {
    @FXML
    private ImageView imageEsercizio;
    @FXML
    private TextFlow esercizioTextFlow;
    @FXML
    private Button removeEsercizio;
    public void removeEsercizioAction(ActionEvent actionEvent) {
        ((VBox)((Pane) removeEsercizio.getParent()).getParent()).getChildren().remove(removeEsercizio.getParent());
        //DEVO RIMUOVERE DA DATABASEEEEEE
    }
    public void initialize(){
        EsercizioHandler.getInstance().collegaControllerHandlerEsercizio(imageEsercizio,esercizioTextFlow,removeEsercizio);
    }
}
