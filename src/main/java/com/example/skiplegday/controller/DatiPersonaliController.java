package com.example.skiplegday.controller;

import com.example.skiplegday.model.*;
import com.example.skiplegday.view.ConfirmationAlert;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;

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
        GetAllDataFromUserService getAllDataFromUserService = new GetAllDataFromUserService();
        getAllDataFromUserService.setDati(UtenteAttuale.getInstance().getUsername());
        getAllDataFromUserService.restart();
        getAllDataFromUserService.setOnSucceeded(event -> {
            DataUser dataUser = getAllDataFromUserService.getValue();
            nomeField.setText(dataUser.nome());
            cognomeField.setText(dataUser.cognome());
            genereField.setValue(dataUser.sesso());
            LocalDate data = LocalDate.parse(dataUser.dataNascita());
            dataNascitaField.setValue(data);
            altezzaField.setText(String.valueOf(dataUser.altezza()));
            pesoField.setText(String.valueOf(dataUser.peso()));
        });
    }
    public void profiloSalvaAction(){
        //salva i dati nel DB
        int res=ConfirmationAlert.showConfirmationAlert("Sicuro di voler salvare i dati? I dati verranno salvati nel database","Annulla","Salva");
        if (res==0){
            ModInfoUserService modInfoUserService = new ModInfoUserService();
            modInfoUserService.setDati(UtenteAttuale.getInstance().getUsername(),nomeField.getText(),cognomeField.getText(),genereField.getValue(),pesoField.getText(),dataNascitaField.getValue().toString(),altezzaField.getText());
            modInfoUserService.restart();
            modInfoUserService.setOnSucceeded(event -> {
                if(modInfoUserService.getValue()) {
                    SceneHandler.getInstance().createHomeScene();
                }
                else{
                    ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della pagina");
                }
            });
        }
    }
    public void profiloAnnullaAction(){
        SceneHandler.getInstance().createHomeScene();
        //torna alla schermata precedente
    }
    public void profiloEliminaAccountAction(){
        int res=ConfirmationAlert.showConfirmationAlert("Sicuro di voler eliminare l'account? l'account verrà perso definitivamente","Annulla","Elimina");
        if (res==0){
            RemoveUserCompletelyService removeUserCompletelyService = new RemoveUserCompletelyService();
            removeUserCompletelyService.setDati(UtenteAttuale.getInstance().getUsername());
            removeUserCompletelyService.restart();
            removeUserCompletelyService.setOnSucceeded(event -> {
                if(removeUserCompletelyService.getValue()) {
                    SceneHandler.getInstance().createLoginScene();
                }
                else{
                    ErrorMessage.getInstance().showErrorMessage("L'account non è stato eliminato");
                }
            });
        }
    }
}
