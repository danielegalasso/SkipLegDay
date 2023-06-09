package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    private AnchorPane sceneRoot;
    @FXML
    private Button settingsButton;
    @FXML
    private ContextMenu settingsMenu;
    public void logoutAction(ActionEvent actionEvent) {
        //model.logout();  daniele galasso   devo salvare qualcosa nell'account utente??
        SceneHandler.getInstance().createLoginScene();
        SceneSecondaryHandler.getInstance().clearAll();

    }
    public void eserciziAction(ActionEvent actionEvent) throws IOException {  //creare gli esercizi dovrebbe essere della view???
        SceneSecondaryHandler.getInstance().createEserciziScene();
    }
    public void statisticheAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createStatisticheScene();
    }
    public void schedePredefiniteAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();
    }
    public void schedaPersonaleAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
    }
    public void homeAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createSchedePredefiniteScene();  //devo tornare al principale, forse questa è una schifezza
    }
    public void profiloAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createDatiPersonaliScene();
    }
    public void settingsButtonAction(ActionEvent actionEvent) {
        double sceneX = settingsButton.localToScene(settingsButton.getBoundsInLocal()).getMinX() - settingsButton.getWidth()/1.5;
        double sceneY = settingsButton.localToScene(settingsButton.getBoundsInLocal()).getMinY() + settingsButton.getHeight()*1.5;
        double screenX = settingsButton.getScene().getWindow().getX() + sceneX;
        double screenY = settingsButton.getScene().getWindow().getY() + sceneY;
        settingsMenu.show(settingsButton, screenX, screenY);
    }
    public void accessibilitaAction(ActionEvent actionEvent) {
    }
    public void valutaciAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createValutaciScene();
    }
    public void obiettiviAction(ActionEvent actionEvent) {
    }
    public void cambiaTemaAction(ActionEvent actionEvent) {
        RadioMenuItem selectedMenuItem = (RadioMenuItem) actionEvent.getSource();       //prendo l'evento del colore cliccato
        String colore = selectedTheme(selectedMenuItem);
        //setLastTheme(colore);
        SceneHandler.getInstance().setTheme(colore);
    }
    public String selectedTheme(RadioMenuItem selectedMenuItem){
        String menuItemId = selectedMenuItem.getId();                                   //prendo l'id del menu item
        String colore = menuItemId.replace("MenuItem","");              //prendo solo il nome del colore
        return colore;
    }
    public void initialize() {
        // Imposta l'AnchorPane come campo privato della classe SceneSecondaryHandler
        SceneSecondaryHandler.getInstance().setHomeSceneRoot(sceneRoot);
    }
}
