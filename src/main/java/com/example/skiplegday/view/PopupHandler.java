package com.example.skiplegday.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PopupHandler {
    private static final PopupHandler instance = new PopupHandler();
    private PopupHandler() {}
    public static PopupHandler getInstance() {return instance;}
    private VBox vBoxDatiEsercizi;
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public void addDatiEsercizio() throws IOException {
        vBoxDatiEsercizi.getChildren().add(loadRootFromFXML("kgRepRec.fxml"));
    }
    public void setvBoxDatiEsercizi(VBox vBoxDatiEsercizi) {
        this.vBoxDatiEsercizi = vBoxDatiEsercizi;
    }
}
