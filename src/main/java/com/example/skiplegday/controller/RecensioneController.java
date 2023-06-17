package com.example.skiplegday.controller;

import com.example.skiplegday.model.DataUser;
import com.example.skiplegday.model.GetAllDataFromUserService;
import com.example.skiplegday.model.ModRecensioneService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.util.StringConverter;

import java.io.IOException;
import java.text.NumberFormat;

public class RecensioneController {
    @FXML
    private Label labelSliderRecensione;
    @FXML
    private Slider sliderRecensione;
    @FXML
    private TextArea textAreaRecensione;
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
        GetAllDataFromUserService getAllDataFromUserService = new GetAllDataFromUserService();
        getAllDataFromUserService.setDati(UtenteAttuale.getInstance().getUsername());
        getAllDataFromUserService.restart();
        getAllDataFromUserService.setOnSucceeded(event -> {
            DataUser dataUser = getAllDataFromUserService.getValue();
            System.out.println(dataUser.valutazione());
            sliderRecensione.setValue(dataUser.valutazione());
            textAreaRecensione.setText(dataUser.recensione());
            System.out.println(dataUser.recensione());
        });

    }
    public void recensioneSalvaAction(){
        ModRecensioneService mrs = new ModRecensioneService();
        double valutazione = sliderRecensione.getValue();
        System.out.println(valutazione);
        mrs.setDati(UtenteAttuale.getInstance().getUsername(),valutazione ,textAreaRecensione.getText());
        mrs.restart();
        mrs.setOnSucceeded(event -> {
            if (mrs.getValue()){
                try {
                    SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();
                } catch (IOException e) {
                    ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della pagina");
                }
            }
            else{
                ErrorMessage.getInstance().showErrorMessage("Errore durante l'inserimento della recensione");
            }
        });
    }

    public void recensioneAnnullaAction() throws IOException {
        SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();
    }
}
