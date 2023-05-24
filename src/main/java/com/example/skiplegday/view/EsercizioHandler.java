package com.example.skiplegday.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextFlow;

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
        esercizioTextFlow.getChildren().add(nameEsercizio);
        if (isPredefinito){
            removeEsercizio.setVisible(false);
        }
        else{
            removeEsercizio.setVisible(true);
        }
    }
    public void setDescrizioneEsercizio(Image esercizioImage, DescrizioneEsercizio nameEsercizio){
        imageEsercizio.setImage(esercizioImage);
        esercizioTextFlow.getChildren().add(nameEsercizio);
    }
    public void setButtonAggiungiInCreaAllenamentoScene() {
        // Rimuovo l'azione di default del button esistente
        removeEsercizio.setOnAction(null);

        // Aggiungo l'azione desiderata al nuovo button
        removeEsercizio.setOnAction(event -> { // Azione personalizzata del nuovo button
            System.out.println("Nuovo Button cliccato!");
        });

        removeEsercizio.setText("Aggiungi");
    }
    public void setOnMouseClickedEvent(EventHandler<MouseEvent> eventHandler,String nameButton) {
        removeEsercizio.setOnMouseClicked(eventHandler);
        removeEsercizio.setText(nameButton);
    }

}
