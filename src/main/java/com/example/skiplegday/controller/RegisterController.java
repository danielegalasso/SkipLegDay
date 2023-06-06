package com.example.skiplegday.controller;

import com.example.skiplegday.model.Database;
import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterController {
    @FXML
    private PasswordField PasswordRegister;
    @FXML
    private TextField NomeUtenteRegister;
    @FXML
    private Text erroreRegisterText;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField altezzaField;
    @FXML
    private DatePicker dataNascita;
    public void creaAccountAction(ActionEvent actionEvent) throws SQLException {
        if (checkIsValid()){
            LocalDate selectedDate = dataNascita.getValue();
            String dateString = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); //posso mettere il cazzo che voglio per daniele
            //DEVO METTERE LA DATA NEL DATABASE!!!!!!!!!!!!
            //il peso si carica da profilo, inoltre tutti i valori me li salvo in UtenteAttuale cosi deFga se li puo prendere easy
            //in realtà me li devo caricare su UtenteAttuale da Login e non ora, perche un utente si potrebbe registrare ma collegarsi
            //uno diverso. Login-----> query db---> mi prendo tutti i dati li button in UtenteAttuale e li usa deFga
            /*   DECOMMENTO PER EVITARE DI BUTTARE MERDA NEL DB
            if (Database.getInstance().registerUser(NomeUtenteRegister.getText(),PasswordRegister.getText(),nomeField.getText(),cognomeField.getText(),emailField.getText(),dateString,altezzaField.getText() )){
                SceneHandler.getInstance().createHomeScene();
            }
            else{
                erroreRegisterText.setVisible(true);
            }*/
        }
    }
    private boolean checkIsValid(){
        return !NomeUtenteRegister.getText().equals("") && !PasswordRegister.getText().equals("") && !nomeField.getText().equals("")  && !cognomeField.getText().equals("")  && !altezzaField.getText().equals("");
    }
    public void tornaALoginAction(MouseEvent mouseEvent) {
        SceneHandler.getInstance().createLoginScene();
    }
}
