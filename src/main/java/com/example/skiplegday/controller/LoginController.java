package com.example.skiplegday.controller;

import com.example.skiplegday.model.UsersReader;
import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private TextField NomeUtenteLogin;
    @FXML
    private PasswordField PasswordLogin;
    @FXML
    private Text textMessage;
    public void RegistraAction(MouseEvent mouseEvent) {
        //model.createUser();  daniele galasso
        SceneHandler.getInstance().createRegistraScene();
    }
    public void loginAction(ActionEvent actionEvent) {
        String user= NomeUtenteLogin.getText();
        String pass=PasswordLogin.getText();
        if(UsersReader.getInstance().checkAccess(user,pass)) {
            //vanno caricati gli esercizi e le schede (?)
            try {
                SceneHandler.getInstance().createHomeScene();
            } catch (Exception e) {
                //SceneHandler.getInstance().showError(Message.LOAD_USER_ERROR);
            }
        }
        else{
            textMessage.setVisible(true);
            NomeUtenteLogin.setText("");
            PasswordLogin.setText("");
            textMessage.setText("Nome utente o password errati");
        }
    }
}
