package com.example.skiplegday.controller;

import com.example.skiplegday.model.Database;
import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.model.LoginService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField NomeUtenteLogin;
    @FXML
    private PasswordField PasswordLogin;
    @FXML
    private Text erroreLoginText;
    public void RegistraAction(MouseEvent mouseEvent) {
        //model.createUser();  daniele galasso
        SceneHandler.getInstance().createRegistraScene();
    }
    public void loginAction(ActionEvent actionEvent) throws SQLException {
        String user= NomeUtenteLogin.getText();
        String pass= PasswordLogin.getText();
        System.out.println(user+" "+pass);
        LoginService loginService = new LoginService();
        loginService.setDati(user,pass);
        loginService.restart();
        loginService.setOnSucceeded( event -> {
            //vanno caricati gli esercizi e le schede (?)   THREAD
            if((boolean) event.getSource().getValue() || true) {
                try {
                    UtenteAttuale.getInstance().setUsername(user);
                    SceneHandler.getInstance().createHomeScene();
                } catch (Exception e) {
                    //SceneHandler.getInstance().showError(Message.LOAD_USER_ERROR);
                }
            }
            else{
                NomeUtenteLogin.setText("");
                PasswordLogin.setText("");
                erroreLoginText.setVisible(true);
            }
        });/*
        if(Database.getInstance().loginIn(user,pass) || true){ //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //vanno caricati gli esercizi e le schede (?)   THREAD
            try {
                UtenteAttuale.getInstance().setUsername(user);
                SceneHandler.getInstance().createHomeScene();
            } catch (Exception e) {
                //SceneHandler.getInstance().showError(Message.LOAD_USER_ERROR);
            }
        }
        else{
            NomeUtenteLogin.setText("");
            PasswordLogin.setText("");
            erroreLoginText.setVisible(true);
        }*/
    }
    public void initialize() throws SQLException {  //appena starto l'applicazione mi carico tutti i dati e gli esercizi
        InformazioniEsercizi.getInstance().caricaEsercizi();
    }
}
