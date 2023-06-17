package com.example.skiplegday.view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

import java.util.Optional;

public class ConfirmationAlert {
    public static int showConfirmationAlert(String contentText,String buttonName,String buttonName1) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Conferma");
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        // Creazione dei pulsanti personalizzati
        ButtonType buttonClose = new ButtonType(buttonName1, ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType(buttonName, ButtonBar.ButtonData.YES);

        // Personalizzazione del DialogPane per aggiungere un'icona
        //dialogPane.setGraphic(new ImageView(new Image(getClass().getResource("/com/examl.png"))));

        // Aggiunta dei pulsanti al DialogPane
        DialogPane dialogPane = alert.getDialogPane();
        Scene scene = dialogPane.getScene();
        SceneHandler.getInstance().setCSSForScene(scene);
        dialogPane.getButtonTypes().setAll(buttonClose, buttonYes);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == buttonClose) {
                return 0;
            } else if (result.get() == buttonYes) {
                return 1;
            }
        }
        return -1;
    }
}
