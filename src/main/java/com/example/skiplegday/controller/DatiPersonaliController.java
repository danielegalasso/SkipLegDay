package com.example.skiplegday.controller;

import com.example.skiplegday.model.RemoveUserService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.ConfirmationAlert;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DatiPersonaliController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private TextField altezzaField;
    @FXML
    private TextField pesoField;
    @FXML
    private DatePicker dataNascitaField;
    @FXML
    private ChoiceBox<String> genereField;

    ObservableList<String> genereChoiceList = FXCollections.observableArrayList("UOMO","DONNA","ALTRO");
    public void initialize() {
        genereField.setItems(genereChoiceList);
    }
    public void profiloSalvaAction(){
        //salva i dati nel DB
        int res=ConfirmationAlert.showConfirmationAlert("Sicuro di voler salvare i dati? I dati verranno salvati nel database","Annulla","Salva");
            if (res==0){
            }
    }
    public void profiloAnnullaAction(){
        SceneHandler.getInstance().createHomeScene();
        //torna alla schermata precedente
    }
    public void profiloEliminaAccountAction(){
        int res=ConfirmationAlert.showConfirmationAlert("Sicuro di voler eliminare l'account? l'account verrà perso definitivamente","Annulla","Elimina");
        if (res==0){
            System.out.println("Elimina account");
            /*
            RemoveUserService rem = new RemoveUserService();
            rem.setDati(UtenteAttuale.getInstance().getUsername(),); //????
            rem.restart();
            rem.setOnSucceeded(event -> {
                if (rem.getValue()) {
                    SceneHandler.getInstance().createLoginScene();
                    //SceneHandler.getInstance().resetRegisterPhase2();
                } else {
                    ErrorMessage.getInstance().showErrorMessage("Errore durante l'eliminazione dell'account");
                }
            });*/
        }
    }
}
