package com.example.skiplegday.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Esercizio extends Text{
    public Esercizio(String text){
        setText(text);
        setOnMouseClicked(event -> {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                Popup popup = new Popup(mainStage,loadRootFromFXML("popupEsercizio.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
}
