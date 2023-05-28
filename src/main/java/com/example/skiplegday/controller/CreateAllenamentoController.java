package com.example.skiplegday.controller;

import com.example.skiplegday.model.AddSchedaService;
import com.example.skiplegday.model.CheckSchedaInDbService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.CreateAllenamentoHandler;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class CreateAllenamentoController {
    @FXML
    Text scegliNomeText;
    @FXML
    TextField fieldCreateNameAllenamento;
    @FXML
    VBox vBoxTuoAllenamento;
    @FXML
    ScrollPane scrollPaneEsercizi;
    public void saveAllenamentoAction(ActionEvent actionEvent) throws IOException {
        //aggiungo nel database creo fxml con label e immagine avente come nome questo textField  !!!!!!!!!!!!!!!!!!
        if(fieldCreateNameAllenamento.getText().equals("") || vBoxTuoAllenamento.getChildren().size() == 0){
            return;
        }
        CheckSchedaInDbService checkSchedaInDbService = new CheckSchedaInDbService();
        checkSchedaInDbService.setDati(UtenteAttuale.getInstance().getUsername(),fieldCreateNameAllenamento.getText());
        checkSchedaInDbService.restart();
        checkSchedaInDbService.setOnSucceeded(event ->{
            if (!checkSchedaInDbService.getValue()) { //se non esiste la scheda la aggiungo
                AddSchedaService addSchedaService = new AddSchedaService();
                //QUANDO INVECE MODIFICO DEVO CAPIRE DA DOVE PRENDERE IL NOME DELLA SCHEDA ALLENAMENTO VVVVV
                addSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),fieldCreateNameAllenamento.getText(), CreateAllenamentoHandler.getInstance().getEserciziAggiuntiScheda());
                addSchedaService.restart();
                addSchedaService.setOnSucceeded(event1 ->{
                    if (addSchedaService.getValue()) {
                        try {
                            SceneSecondaryHandler.getInstance().aggiungiSchedaPersonaleScene(fieldCreateNameAllenamento.getText());
                            SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
                        } catch (IOException ignoredEvent) {}
                    } else {
                        System.out.println("Scheda non aggiunta");
                    }
                });
            }
            else{
                //scegliNomeText.setText("Nome già presente");  //non devo farlo su questo text ma ne creo uno fatto apposta per questo errore
                //!!!!!!!!!!!!!!!!!!!!!!!! INVECE DEL TEXT CREO UN POPUP DI ERRORE PERCHE POSSO AGGIUNGERE
                //ANCHE DA SCHEDE DI DEFAULT CON IL PULSANTE IMPORTA
                ErrorMessage.getInstance().showErrorMessage("Scheda già presente");
            }
        });
    }
    public void initialize() throws IOException {
        /*
        SceneSecondaryHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        SceneSecondaryHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
        SceneSecondaryHandler.getInstance().setFieldNameAllenamento(fieldCreateNameAllenamento);
        //SceneSecondaryHandler.getInstance().caricaEserciziVbox();  decommentare !!!!!!!!*/
        CreateAllenamentoHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        CreateAllenamentoHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
        CreateAllenamentoHandler.getInstance().setFieldNameAllenamento(fieldCreateNameAllenamento);
        CreateAllenamentoHandler.getInstance().caricaEserciziVbox(fieldCreateNameAllenamento.getPromptText());
    }
    public void indietroAction(ActionEvent actionEvent) {
        SceneSecondaryHandler.getInstance().CreateLastScene();
    }

}
