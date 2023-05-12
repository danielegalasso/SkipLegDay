package com.example.skiplegday.view;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class PopupHandler {
    private static final PopupHandler instance = new PopupHandler();
    private PopupHandler() {}
    public static PopupHandler getInstance() {return instance;}
    private VBox vBoxDatiEsercizi;
    private Text nomeEsercizio;
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public void addDatiEsercizio() throws IOException {
        vBoxDatiEsercizi.getChildren().add(loadRootFromFXML("kgRepRec.fxml"));
    }
    public void setNomeEsercizio(String nomeEsercizio) {
        this.nomeEsercizio.setText(nomeEsercizio);
    }
    public void setvBoxDatiEsercizi(VBox vBoxDatiEsercizi) { //mi carico il vBox dal controller col metodo initialize, in modo che posso gestirmi tutto da questa classe
        this.vBoxDatiEsercizi = vBoxDatiEsercizi;
    }
    public void setNomeEsercizio(Text nomeEsercizio) {
        this.nomeEsercizio = nomeEsercizio;
    }
}
