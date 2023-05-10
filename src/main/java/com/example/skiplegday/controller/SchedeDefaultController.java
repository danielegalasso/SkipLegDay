package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SchedeDefaultController {
    public void principianteSceneAction(MouseEvent mouseEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createPrincipianteScene();
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