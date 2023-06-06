package com.example.skiplegday.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class DatiPersonaliController {
    @FXML
    private ChoiceBox<String> genereChoiceBox;

    ObservableList<String> genereChoiceList = FXCollections.observableArrayList("UOMO","DONNA","ALTRO");
    public void initialize() {
        genereChoiceBox.setItems(genereChoiceList);
    }
}
