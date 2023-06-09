package com.example.skiplegday.controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.util.StringConverter;

import java.text.NumberFormat;

public class RecensioneController {
    @FXML
    private Label labelSliderRecensione;
    @FXML
    private Slider sliderRecensione;

    private static class DecimalStringConverter extends StringConverter<Number> {
        @Override
        public String toString(Number number) {
            return String.format("%.1f", number.doubleValue());
        }

        @Override
        public Number fromString(String string) {
            try {
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }

    @FXML
    public void initialize(){
        labelSliderRecensione.textProperty().bindBidirectional(sliderRecensione.valueProperty(), new DecimalStringConverter());

    }

    public void recensioneSalvaAction(){
        //collega la recensione all'utente nel DB
    }

    public void recensioneAnnullaAction(){
        //torna alla schermata precedente
    }
}
