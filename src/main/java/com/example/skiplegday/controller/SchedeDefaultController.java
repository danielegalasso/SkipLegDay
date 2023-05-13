package com.example.skiplegday.controller;

import com.example.skiplegday.view.Popup;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SchedeDefaultController {
    public void principianteSceneAction(MouseEvent mouseEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createPrincipianteScene();
    }
    public void intermedioSceneAction(MouseEvent mouseEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createIntermedioScene();
    }
    public void avanzatoSceneAction(MouseEvent mouseEvent) throws IOException {
        //è per una prova
        Stage mainStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow(); // Ottieni il riferimento alla finestra principale
        SceneSecondaryHandler.getInstance().createAvanzatoScene(mainStage);
    }
    public void corpoLiberoSceneAction(MouseEvent mouseEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createCorpoLiberoScene();
    }
    public void totalBodySceneAction(MouseEvent mouseEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createTotalBodyScene();
    }
    public void resistenzaSceneAction(MouseEvent mouseEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createResistenzaScene();
    }
}