package com.example.skiplegday.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SchedaPersonaleController {
    @FXML
    Label labelSchedaPersonalizzata;
    @FXML
    ImageView imageSchedaPersonalizzata;
    public void accediSchedaPersonalizzata(MouseEvent mouseEvent) {
        labelSchedaPersonalizzata.getText();
        //e co sto testo devo trova l'allenamento nel database dal model
    }
}
