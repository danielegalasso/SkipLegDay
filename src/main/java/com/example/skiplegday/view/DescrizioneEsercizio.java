package com.example.skiplegday.view;

import com.example.skiplegday.controller.DescrizioneEsercizioController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DescrizioneEsercizio extends Text {
    public DescrizioneEsercizio(String text,boolean popup) {  //la descrizione esercizio si puo aprire sia sul popup, che in menuEsercizi
        setText(text);
        getStyleClass().add("esercizio");
        setOnMouseClicked(event -> {
            try {
                if (!popup) {
                    ManualeEserciziHandler.getInstance().aggiungiDescrizioneEsercizio(this.getText());
                } else {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/descrizioneEsercizio.fxml"));
                    Parent desc = fxmlLoader.load();
                    Scene scene = new Scene(desc);
                    DescrizioneEsercizioController c = fxmlLoader.getController();
                    c.loadDati(this.getText());
                    c.buttonInvisibile();
                    Stage popupStage = new Stage();
                    SceneHandler.getInstance().setCSSForScene(scene);
                    popupStage.initStyle(StageStyle.UNDECORATED);
                    popupStage.setScene(scene);
                    popupStage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento della descrizione dell'esercizio");
            }
        });
    }
}
