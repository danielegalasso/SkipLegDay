package com.example.skiplegday.controller;

import com.example.skiplegday.Utils;
import com.example.skiplegday.model.RegisterUserService;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegisterController {
    @FXML
    private TextField NomeUtenteRegister;
    @FXML
    private PasswordField PasswordRegister;
    @FXML
    private PasswordField confermaPasswordRegister;
    //defga
    public void initialize() {
        if (PasswordRegister==null) return;
        Text text = new Text("La password deve contenere almeno 8 caratteri, una lettera maiuscola, una minuscola e un numero");
        text.setWrappingWidth(300);
        text.getStyleClass().add("testo-color");
        Tooltip passwordTooltip = new Tooltip();
        passwordTooltip.setGraphic(text);        passwordTooltip.setShowDelay(Duration.millis(10));
        PasswordRegister.setTooltip(passwordTooltip);
        PasswordRegister.focusedProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                // Quando il TextField ottiene il focus, mostra il Tooltip
                passwordTooltip.show(PasswordRegister,
                        PasswordRegister.getScene().getWindow().getX() + PasswordRegister.getLayoutX(),
                        PasswordRegister.getScene().getWindow().getY() + PasswordRegister.getLayoutY() + PasswordRegister.getHeight());
            } else {
                // Quando il TextField perde il focus, nascondi il Tooltip
                passwordTooltip.hide();
            }
        });
    }
    public void avantiAction (ActionEvent actionEvent) throws IOException {
        if (usernameAndPasswordIsValid()) {
            Parent collegamento= SceneHandler.getInstance().createRegister2PhaseScene();
            System.out.println("coll"+collegamento);
            Register2PhaseController controller = (Register2PhaseController) collegamento.getProperties().get("foo");
            controller.setDati(NomeUtenteRegister.getText(),PasswordRegister.getText());
        }
    }
    public void tornaALoginAction(MouseEvent mouseEvent) {
        SceneHandler.getInstance().createLoginScene();
    }
    private boolean usernameAndPasswordIsValid() {
        String username = NomeUtenteRegister.getText();
        String password = PasswordRegister.getText();
        String confermaPassword = confermaPasswordRegister.getText();

        if (username.isEmpty() || password.isEmpty()) {
            ErrorMessage.getInstance().showErrorMessage("Inserisci sia il nome utente che la password.");
            return false;
        }

        if (!password.equals(confermaPassword)) {
            ErrorMessage.getInstance().showErrorMessage("La password e la conferma password non corrispondono.");
            return false;
        }

        if (!isValidatePassword(password)) {
            ErrorMessage.getInstance().showErrorMessage("La password non è valida.");
            return false;
        }
        return true;
    }
    private boolean isValidatePassword (String str){
        return str.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
    }

    public void exitAction(MouseEvent mouseEvent) {
        Utils.chiudiApp(NomeUtenteRegister); //mi basta passare un qualsiasi elemento della scena per risalire alla scena
        mouseEvent.consume();
    }
}
