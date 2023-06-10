package com.example.skiplegday.view;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

import java.util.Objects;

public class ErrorMessage {
    private static final ErrorMessage instance = new ErrorMessage();
    private final Alert alert;
    private ErrorMessage() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UNDECORATED);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(ErrorMessage.class.getResource(CSS_PATH+"dialog.css").toExternalForm());
        //for (String style : Settings.styles)    DI FRA LAVURAA
        //    alert.getDialogPane().getStylesheets().add(Objects.requireNonNull(ErrorMessage.class.getResource(style)).toExternalForm());
    }
    public static ErrorMessage getInstance() {
        return instance;
    }
    public void showErrorMessage(String text) {
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
    private static final String CSS_PATH = "/com/example/skiplegday/css/";
}
