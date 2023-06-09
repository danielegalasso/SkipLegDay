package com.example.skiplegday.controller;

import com.example.skiplegday.model.RegisterUserService;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Register2PhaseController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private TextField altezzaField;
    @FXML
    private TextField pesoField;
    @FXML
    private ChoiceBox<String> genereChoice;
    @FXML
    private DatePicker dataNascita;
    private String username;
    private String password;

    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Maschio", "Femmina");
        genereChoice.setItems(list);
    }
    public void confermaRegisterAction(ActionEvent actionEvent) {
        //METTO NEL DB E TORNO ALLA SCHERMATA DI LOGIN
        if (datiPersonaliIsValid()){
            LocalDate selectedDate = dataNascita.getValue();
            String dateString = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //posso mettere il cazzo che voglio per daniele
            //DEVO METTERE LA DATA NEL DATABASE!!!!!!!!!!!!
            //il peso si carica da profilo, inoltre tutti i valori me li salvo in UtenteAttuale cosi deFga se li puo prendere easy
            //in realtà me li devo caricare su UtenteAttuale da Login e non ora, perche un utente si potrebbe registrare ma collegarsi
            //uno diverso. Login-----> query db---> mi prendo tutti i dati li button in UtenteAttuale e li usa deFga

            RegisterUserService reg = new RegisterUserService();
            reg.setDati(username,password,nomeField.getText(),cognomeField.getText(),genereChoice.getValue() ,pesoField.getText(),dateString ,altezzaField.getText(), "", "", "");
            reg.restart();
            reg.setOnSucceeded(event -> {
                if (reg.getValue()) {
                    SceneHandler.getInstance().createLoginScene();
                    SceneHandler.getInstance().resetRegisterPhase2();
                } else {
                    ErrorMessage.getInstance().showErrorMessage("Errore durante la registrazione.");
                }
            });
        }
    }
    public void indietroRegisterAction(ActionEvent actionEvent) {
        SceneHandler.getInstance().tornaRegisterScene();
        //devo tornare alla schermata di prima
    }
    public void tornaALoginAction(MouseEvent mouseEvent) {
        SceneHandler.getInstance().createLoginScene();
    }
    private boolean datiPersonaliIsValid() {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String altezza = altezzaField.getText();
        String peso = pesoField.getText();
        String genere = genereChoice.getValue();
        LocalDate data = dataNascita.getValue();

        if (nome.isEmpty() || cognome.isEmpty() || altezza.isEmpty() || peso.isEmpty() || genere.isEmpty() || data == null) {
            ErrorMessage.getInstance().showErrorMessage("Compila tutti i campi dei dati personali.");
            return false;
        }

        if (!isAlphabetic(nome)) {
            ErrorMessage.getInstance().showErrorMessage("Il nome deve contenere solo lettere.");
            return false;
        }

        if (!isAlphabetic(cognome)) {
            ErrorMessage.getInstance().showErrorMessage("Il cognome deve contenere solo lettere.");
            return false;
        }

        if (!isNumeric(altezza)) {
            ErrorMessage.getInstance().showErrorMessage("L'altezza non è valida.");
            return false;
        }

        if (!isNumeric(peso)) {
            ErrorMessage.getInstance().showErrorMessage("Il peso non è valido.");
            return false;
        }
        return true;
    }
    private boolean isNumeric(String str) {
        return str.matches("\\d*([.,]\\d+)?");
    }
    private boolean isAlphabetic(String str) {
        return str.matches("[a-zA-Z]+");
    }

    public void setDati(String text, String text1) {
        this.username = text;
        this.password = text1;
    }
}
