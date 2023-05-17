package com.example.skiplegday.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.text.NumberFormat;

public class RecensioneController {
    @FXML
    private Label label;
    @FXML
    private Slider sliderId;

    @FXML
    public void initialize(){
        label.textProperty().bindBidirectional(sliderId.valueProperty(), NumberFormat.getNumberInstance());
        //sliderValue.bindBidirectional(sliderId.valueProperty());
        //label.textProperty().bind(sliderValue.asString("%.2f"));
    }
}
