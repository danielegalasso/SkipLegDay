package com.example.skiplegday.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;
public class kgRepRecController {
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
}
