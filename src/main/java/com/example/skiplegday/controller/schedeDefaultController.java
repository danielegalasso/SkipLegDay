package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class schedeDefaultController {
    public void principianteSceneAction(MouseEvent mouseEvent) throws IOException {
        System.out.println("ok");
        SceneSecondaryHandler.getInstance().createPrincipianteScene();// non ha senso ritorno un fxml
    }
    public void avanzatoSceneAction(MouseEvent mouseEvent) {
    }
    public void corpoLiberoSceneAction(MouseEvent mouseEvent) {
    }
    public void totalBodySceneAction(MouseEvent mouseEvent) {
    }
    public void resistenzaSceneAction(MouseEvent mouseEvent) {
    }
    public void intermedioSceneAction(MouseEvent mouseEvent) {
    }
}