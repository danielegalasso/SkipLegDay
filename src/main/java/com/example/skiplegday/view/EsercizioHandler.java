package com.example.skiplegday.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;

import java.util.Random;

public class EsercizioHandler {
    private static EsercizioHandler instance = new EsercizioHandler();
    private EsercizioHandler() {}
    public static EsercizioHandler getInstance() {return instance;}
    private ImageView imageEsercizio;
    private TextFlow esercizioTextFlow;
    private Button removeEsercizio;
    public void collegaControllerHandlerEsercizio(ImageView imageEsercizio, TextFlow esercizioTextFlow,Button removeEsercizio){
        this.imageEsercizio = imageEsercizio;
        this.esercizioTextFlow = esercizioTextFlow;
        this.removeEsercizio = removeEsercizio;
    }
    public void setEsercizio(Image esercizioImage, Esercizio nameEsercizio, boolean isPredefinito){
        imageEsercizio.setImage(esercizioImage);
        //nameEsercizio.setFont(javafx.scene.text.Font.font("System", 30));  //sicuro vengono sovrascritte del css
        esercizioTextFlow.getChildren().add(nameEsercizio);
        if (isPredefinito){
            removeEsercizio.setVisible(false);
        }
        else{
            removeEsercizio.setVisible(false);   // da mettere true, o in caso cacciarlo se non voglio far piu vedere il
            //remove esercizio
        }
    }
    public void setDescrizioneEsercizio(Image esercizioImage, DescrizioneEsercizio nameEsercizio){
        imageEsercizio.setImage(esercizioImage);
        esercizioTextFlow.getChildren().add(nameEsercizio);
    }
    public void setOnMouseClickedEvent(EventHandler<ActionEvent> eventHandler, String nameButton) {
        removeEsercizio.setOnAction(eventHandler);
        removeEsercizio.setText(nameButton);
    }
}
