package com.example.skiplegday.view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Esercizio extends Text{  //la classe esercizio non è altro che un Text con associato un actionEvent internamente che apre una finestra popup
    public Esercizio(String text){
        setText(text);
        setOnMouseClicked(event -> {
            Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                Node node=loadRootFromFXML("popupEsercizio.fxml");
                PopupHandler.getInstance().setNomeEsercizio(this.getText());
                Popup popup= new Popup(mainStage,node);
            } catch (IOException e) {
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
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
}
