package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeController {
    @FXML
    private Button SchedePredefiniteButton;
    @FXML
    private AnchorPane SceneRoot;
    public void logoutAction(ActionEvent actionEvent) {
        //model.logout();  daniele galasso   devo salvare qualcosa nell'account utente??
        SceneHandler.getInstance().createLoginScene();
    }

    public void profiloAction(ActionEvent actionEvent) {
        //galasso
    }

    public void eserciziAction(ActionEvent actionEvent) {
        //galasso
    }

    public void statisticheAction(ActionEvent actionEvent) {
    }

    public void schedePredefiniteAction(ActionEvent actionEvent) throws IOException {
        /*
        ContextMenu contextMenu = new ContextMenu(); //crea il context menu
        MenuItem menuItem1 = new MenuItem("Opzione 1"); //crea il menu item 1
        MenuItem menuItem2 = new MenuItem("Opzione 2"); //crea il menu item 2
        contextMenu.getItems().addAll(menuItem1, menuItem2); //aggiunge i menu items al context menu
        contextMenu.show(SchedePredefiniteButton, Side.RIGHT, 0, 0);
         */
        Node node=SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();
        SceneRoot.setTopAnchor(node,10.0);
        SceneRoot.setBottomAnchor(node, 10.0);
        SceneRoot.setLeftAnchor(node, 10.0);
        SceneRoot.setRightAnchor(node, 10.0);
        SceneRoot.getChildren().add(node);
    }

    public void schedaPersonaleAction(ActionEvent actionEvent) {
    }
}
