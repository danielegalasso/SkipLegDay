package com.example.skiplegday.view;
import javafx.scene.Scene;
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
        Scene scene = dialogPane.getScene();
        SceneHandler.getInstance().setCSSForScene(scene);
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
