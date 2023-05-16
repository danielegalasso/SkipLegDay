package com.example.skiplegday.view;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class DescrizioneEsercizio extends Text {
    public DescrizioneEsercizio(String text) {
        setText(text);
        setOnMouseClicked(event -> {
            try {
                DescrizioneEsercizioHandler.getInstance().setTextPulsantePremuto(this.getText());
                SceneSecondaryHandler.getInstance().createDescrizioneEsercizioScene();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(Color.BLUE);
                setCursor(Cursor.HAND);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setFill(Color.BLACK);
                setCursor(Cursor.DEFAULT);
            }
        });
    }
}
