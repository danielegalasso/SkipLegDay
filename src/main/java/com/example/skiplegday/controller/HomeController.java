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
    @FXML
    private HBox hBoxHome;
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
        SceneSecondaryHandler.getInstance().createEserciziScene();  //devo tornare al principale, forse questa è una schifezza
    }
    public void profiloAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createDatiPersonaliScene();
        //apri datiPersonali.fxml
    }
    public void settingsButtonAction(ActionEvent actionEvent) {
        settingsMenu.show(settingsButton,settingsButton.getLayoutX(),settingsButton.getLayoutY() + settingsMenu.getHeight());
    }
    public void cambiaTemaAction(ActionEvent actionEvent) {
    }
    public void accessibilitaAction(ActionEvent actionEvent) {
    }
    public void valutaciAction(ActionEvent actionEvent) throws IOException {
        SceneSecondaryHandler.getInstance().createValutaciScene();
    }
    public void obiettiviAction(ActionEvent actionEvent) {
    }
    public void initialize() {
        // Imposta l'AnchorPane come campo privato della classe SceneSecondaryHandler
        SceneSecondaryHandler.getInstance().setHomeSceneRoot(sceneRoot);
        HBox.setHgrow(hBoxHome, javafx.scene.layout.Priority.ALWAYS);
        SceneSecondaryHandler.getInstance().setHBoxHome(hBoxHome);
    }
}
