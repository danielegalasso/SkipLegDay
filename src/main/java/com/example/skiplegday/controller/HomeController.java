package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private AnchorPane sceneRoot;
    public void logoutAction(ActionEvent actionEvent) {
        //model.logout();  daniele galasso   devo salvare qualcosa nell'account utente??
        SceneHandler.getInstance().createLoginScene();
    }
    public void profiloAction(ActionEvent actionEvent) {
        //galasso
    }
    public void eserciziAction(ActionEvent actionEvent) {  //creare gli esercizi dovrebbe essere della view???
        SceneSecondaryHandler.getInstance().createEserciziScene();
    }
    public void statisticheAction(ActionEvent actionEvent) {
    }
    public void schedePredefiniteAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();
    }
    public void schedaPersonaleAction(ActionEvent actionEvent) {
    }
    public void initialize() {
        // Imposta l'AnchorPane come campo privato della classe SceneSecondaryHandler
        SceneSecondaryHandler.getInstance().setHomeSceneRoot(sceneRoot);
    }
}
