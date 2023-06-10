package com.example.skiplegday.view;

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
        String buttonYES, buttonCLOSE;
        /*
        if (firstParamEvidenziato) {
            buttonYES = buttonName;
            buttonCLOSE = buttonName1;
        }
        else{
            buttonYES = buttonName1;
            buttonCLOSE = buttonName;
        }*/
        // Creazione dei pulsanti personalizzati
        ButtonType buttonClose = new ButtonType(buttonName1, ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType buttonYes = new ButtonType(buttonName, ButtonBar.ButtonData.YES);

        // Personalizzazione del DialogPane per aggiungere un'icona
        //dialogPane.setGraphic(new ImageView(new Image(getClass().getResource("/com/examl.png"))));

        // Aggiunta dei pulsanti al DialogPane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(ConfirmationAlert.class.getResource("/com/example/skiplegday/css/dialog.css").toExternalForm());
        dialogPane.getButtonTypes().setAll(buttonClose, buttonYes);

        Optional<ButtonType> result = alert.showAndWait();

        /*ad esempio nel caso rimuoviScheda non posso mettere il button per rimuovere la scheda in evidenza, quindi imposto
        il button per rimuovere la scheda come buttonClose, non evidenziato, e non di default da premere,però in questo caso
        la scheda verrà eliminata se premo il buttonClose e non il buttonYEs, quindi entrambi i valori restituiti dai
        button dipendono dal parametro booleano
        if (result.isPresent()) {  //doppio if in quando il button che inserisco può andare sia in buttonYES che CLOSe
            if (firstParamEvidenziato){
                if (result.get() == esciButton) {
                    return 0;
                } else if (result.get() == salvaButton) {
                    return 1;
                }
            }
            else{
                if (result.get() == esciButton) {
                    return 1;
                } else if (result.get() == salvaButton) {
                    return 0;
                }
            }
        }
        return -1;*/
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
