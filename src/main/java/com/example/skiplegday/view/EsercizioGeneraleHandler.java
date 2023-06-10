package com.example.skiplegday.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.TextFlow;

public class EsercizioGeneraleHandler {
    private static EsercizioGeneraleHandler instance = new EsercizioGeneraleHandler();
    private EsercizioGeneraleHandler() {}
    public static EsercizioGeneraleHandler getInstance() {return instance;}
    private ImageView imageEsercizio;
    private TextFlow esercizioTextFlow;
    private Button removeEsercizio;
    public void collegaControllerHandlerEsercizio(ImageView imageEsercizio, TextFlow esercizioTextFlow,Button removeEsercizio){
        this.imageEsercizio = imageEsercizio;
        this.esercizioTextFlow = esercizioTextFlow;
        this.removeEsercizio = removeEsercizio;
    }
    public void setEsercizioSchedeDefault(Image esercizioImage, DescrizioneEsercizio nameEsercizio){
        imageEsercizio.setImage(esercizioImage);
        esercizioTextFlow.getChildren().add(nameEsercizio);
        removeEsercizio.setVisible(false);  //se viene caricato per le schede predef, non devo dare la possibilità di
        //rimuoverlo, mentre se la DescrizioneEsercizio viene caricata per le schede personalizzate, allora devo dare
        //la possibilità di rimuoverlo
    }
    public void setEsercizio(Image esercizioImage, Esercizio nameEsercizio, boolean isPredefinito){
        imageEsercizio.setImage(esercizioImage);
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
        //il button me lo gestisco con il metodo sotto
    }
    public void setOnMouseClickedEvent(EventHandler<ActionEvent> eventHandler, String nameButton) {
        removeEsercizio.setOnAction(eventHandler);
        removeEsercizio.setText(nameButton);
        //facendo cosi ho una cosa generale, usando sempre lo stesso button per rimuovere e aggiungere l'esercizio
        //quando sono in creaAllenamentoScene.
    }
}
