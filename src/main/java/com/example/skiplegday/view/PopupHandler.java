package com.example.skiplegday.view;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private Text errorText;
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
    public void setText(Text nomeEsercizio) { //per ottenere riferimento al nome dell'esercizio del controller
        this.nomeEsercizio = nomeEsercizio;
    }

    public void showErrorText(){
        errorText.setText("Inserire tutti i campi");
        errorText.setVisible(true);
    }
    public boolean chekValueNull() {
        ObservableList<Node> items = vBoxDatiEsercizi.getChildren();
        for (Node item : items) {
            if (item instanceof Parent) {
                Parent parent = (Parent) item;
                TextField textField1 = (TextField) parent.getChildrenUnmodifiable().get(0);
                TextField textField2 = (TextField) parent.getChildrenUnmodifiable().get(1);
                TextField textField3 = (TextField) parent.getChildrenUnmodifiable().get(2);
                if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("")) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setErrorText(Text errorText) {
        this.errorText = errorText;
    }
    public void hideErrorText() {
        errorText.setVisible(false);
    }
    private boolean saved=false;
    public void setSaved(boolean saved) {this.saved = saved;}
    public boolean chekNotSave() {
        return !saved;
    }
}
