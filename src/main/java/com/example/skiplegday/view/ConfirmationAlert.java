package com.example.skiplegday.view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ConfirmationAlert {
    public static int showConfirmationAlert(String contentText,String buttonName,boolean buttonEvidenziato){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma");
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        String buttonYES,buttonCLOSE;
        if (buttonEvidenziato) {
            buttonYES = buttonName;
            buttonCLOSE = "Esci";
        }
        else{
            buttonYES = "Esci";
            buttonCLOSE = buttonName;
        }
        // Creazione dei pulsanti personalizzati
        ButtonType esciButton = new ButtonType(buttonCLOSE, ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType salvaButton = new ButtonType(buttonYES, ButtonBar.ButtonData.YES);

        // Personalizzazione del DialogPane per aggiungere un'icona
        //dialogPane.setGraphic(new ImageView(new Image(getClass().getResource("/com/examl.png"))));

        // Aggiunta dei pulsanti al DialogPane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().setAll(esciButton, salvaButton);

        Optional<ButtonType> result = alert.showAndWait();

        /*ad esempio nel caso rimuoviScheda non posso mettere il button per rimuovere la scheda in evidenza, quindi imposto
        il button per rimuovere la scheda come buttonClose, non evidenziato, e non di default da premere,però in questo caso
        la scheda verrà eliminata se premo il buttonClose e non il buttonYEs, quindi entrambi i valori restituiti dai
        button dipendono dal parametro booleano */
        if (result.isPresent()) {  //doppio if in quando il button che inserisco può andare sia in buttonYES che CLOSe
            if (buttonEvidenziato){
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
        return -1;
    }
}
