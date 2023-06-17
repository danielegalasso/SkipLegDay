package com.example.skiplegday.controller;

import com.example.skiplegday.model.*;
import com.example.skiplegday.view.HoveredThresholdNode;
import com.example.skiplegday.view.PopupHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PopupEsercizioController {
    @FXML
    Text nomeEsercizio;
    @FXML
    VBox vBoxDatiEsercizi;
    @FXML
    Button addDatiEsButton;
    @FXML
    Text textError;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    NumberAxis AX;
    @FXML
    NumberAxis AY;
    public void initialize() throws IOException {
        PopupHandler.getInstance().setvBoxDatiEsercizi(vBoxDatiEsercizi);
        PopupHandler.getInstance().setText(nomeEsercizio);
        PopupHandler.getInstance().setErrorText(textError);
        PopupHandler.getInstance().setGraph(lineChart, AX, AY);
    }
    public void addDatiEsAction(ActionEvent actionEvent) throws IOException {
        PopupHandler.getInstance().setSaved(false);   //ogni volta che aggiungo un esercizio devo salvare prima di uscire
        PopupHandler.getInstance().addDatiEsercizio();
    }
    public void salvaProgressiAction(ActionEvent actionEvent) {
        if (PopupHandler.getInstance().chekValueNull()){
            PopupHandler.getInstance().showErrorText("Inserire tutti i campi");
            return;
        }
        PopupHandler.getInstance().hideErrorText();  //se sono qua è andato tutti a buon fine, tolgo l'errorText
        //AllenamentoSaver.getInstance().resetdati();  //resetto i dati per evitare di avere dati doppi, ad esempio se salvo piu volte di fila
        //oppure se elimino unKgRepRec, ne aggiungo un altro, ecc.
        //ho implementato la funzione resetdati direttamente quando li aggiungo
        PopupHandler.getInstance().setSaved(true);
        System.out.println("salva progressi");
        ObservableList<Node> items = vBoxDatiEsercizi.getChildren();
        ArrayList<Serie> serieList= new ArrayList<>();
        for (Node item : items) {
            if (item instanceof Parent) {
                Parent parent = (Parent) item;
                // Verifica se il nodo contiene i tre TextField    if (parent.getChildrenUnmodifiable().size() == 4 && parent.getChildrenUnmodifiable().get(0) instanceof TextField)
                if (parent.getChildrenUnmodifiable().get(0) instanceof TextField && parent.getChildrenUnmodifiable().get(1) instanceof TextField && parent.getChildrenUnmodifiable().get(2) instanceof TextField) {
                    TextField textField1 = (TextField) parent.getChildrenUnmodifiable().get(0);
                    TextField textField2 = (TextField) parent.getChildrenUnmodifiable().get(1);
                    TextField textField3 = (TextField) parent.getChildrenUnmodifiable().get(2);
                    Integer kg = Integer.valueOf(textField1.getText());
                    Integer rep = Integer.valueOf(textField2.getText());
                    Integer rec = Integer.valueOf(textField3.getText());

                    Serie serie = new Serie(kg, rep, rec);
                    serieList.add(serie);
                }
            }
        }
        AllenamentoSaver.getInstance().addSerie(nomeEsercizio.getText(),serieList);
        AllenamentoSaver.getInstance().stampa();
    }
}
