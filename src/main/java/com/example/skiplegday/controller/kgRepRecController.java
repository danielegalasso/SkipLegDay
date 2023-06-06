package com.example.skiplegday.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
public class kgRepRecController {
    @FXML
    Button removeButton;
    @FXML
    TextField kgTextField,repTextField,recTextField;
    public void initialize(){
        // Aggiungi un filtro per consentire solo i caratteri numerici
        kgTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                kgTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        repTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                repTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        recTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                recTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
    public void removeAction(ActionEvent actionEvent) {
        ((VBox)((Pane) removeButton.getParent()).getParent()).getChildren().remove(removeButton.getParent());
        //con il getParent del bottone ottengo il pane che contiene il bottone, con il getParent di questo ottengo il VBox che contiene il pane
    }
}
