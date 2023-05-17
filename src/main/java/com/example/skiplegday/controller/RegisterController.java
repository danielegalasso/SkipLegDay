package com.example.skiplegday.controller;

import com.example.skiplegday.model.UsersReader;
import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class RegisterController {
    @FXML
    private PasswordField PasswordRegister;
    @FXML
    private TextField NomeUtenteRegister;
    @FXML
    private Text erroreRegisterText;
    public void creaAccountAction(ActionEvent actionEvent) {
        if (checkIsValid()){
            if (!UsersReader.getInstance().userEsistente(NomeUtenteRegister.getText())){
                UsersReader.getInstance().createUser(NomeUtenteRegister.getText(),PasswordRegister.getText());
                SceneHandler.getInstance().createHomeScene();
            }
            else{
                erroreRegisterText.setVisible(true);
            }
        }
    }
    private boolean checkIsValid(){
        return !NomeUtenteRegister.getText().equals("") && !PasswordRegister.getText().equals("");
    }
    public void tornaALoginAction(MouseEvent mouseEvent) {
        SceneHandler.getInstance().createLoginScene();
    }
}
