package com.example.skiplegday.controller;

import com.example.skiplegday.model.FileReader;
import com.example.skiplegday.view.SceneHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    public void eserciziAction(ActionEvent actionEvent) {
        sceneRoot.getChildren().clear();
        sceneRoot.getChildren().add(FileReader.getInstance().leggiFile("files/esercizi.txt"));
    }
    public void statisticheAction(ActionEvent actionEvent) {
    }

    public void schedePredefiniteAction(ActionEvent actionEvent) throws IOException {
        Node node=SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();
        sceneRoot.setTopAnchor(node,10.0);
        sceneRoot.setBottomAnchor(node, 10.0);
        sceneRoot.setLeftAnchor(node, 10.0);
        sceneRoot.setRightAnchor(node, 10.0);
        sceneRoot.getChildren().clear();
        sceneRoot.getChildren().add(node);
    }
    public void schedaPersonaleAction(ActionEvent actionEvent) {
    }
}
