package com.example.skiplegday.controller;

import com.example.skiplegday.Utils;
import com.example.skiplegday.model.*;
import com.example.skiplegday.view.ConfirmationAlert;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        //System.out.println(user+" "+pass);
        LoginService loginService = new LoginService();
        loginService.setDati(user,pass);
        loginService.restart();
        loginService.setOnSucceeded( event -> {
            if(loginService.getValue()) {
                try {
                    UtenteAttuale.getInstance().setUsername(user);
                    SceneHandler.getInstance().createHomeScene();
                    GetCssService getCssService = new GetCssService();
                    getCssService.setDati(user);
                    getCssService.restart();
                    getCssService.setOnSucceeded( event1 -> {
                        String css = getCssService.getValue();
                        SceneHandler.getInstance().setTheme(css);
                    });
                    //------------------------------------------------------------------
                    GetAllDataFromUserService getAllDataFromUserService = new GetAllDataFromUserService();
                    getAllDataFromUserService.setDati(UtenteAttuale.getInstance().getUsername());
                    getAllDataFromUserService.restart();
                    getAllDataFromUserService.setOnSucceeded(event2 -> {
                        DataUser dataUser = getAllDataFromUserService.getValue();
                        UtenteAttuale.getInstance().setDataUser(dataUser);
                    });
                } catch (Exception e) {
                    ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della pagina");
                }
            }
            else{
                NomeUtenteLogin.setText("");
                PasswordLogin.setText("");
                erroreLoginText.setVisible(true);
            }
        });
    }
    public void initialize() throws SQLException {  //appena starto l'applicazione mi carico tutti i dati e gli esercizi
        InformazioniEsercizi.getInstance().caricaEsercizi();
    }
    public void exitAction(MouseEvent mouseEvent) {
        Utils.chiudiApp(erroreLoginText);
        mouseEvent.consume();
    }
}
