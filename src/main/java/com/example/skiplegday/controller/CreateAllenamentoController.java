package com.example.skiplegday.controller;

import com.example.skiplegday.model.AddSchedaService;
import com.example.skiplegday.model.CheckSchedaInDbService;
import com.example.skiplegday.model.RemoveSchedaService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.CreateAllenamentoHandler;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.GridPaneAllenamentiHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML
    Button saveAllenamentoButton;
    private static final String PROMPT_TEXT = "inserisci nome";
    public void saveAllenamentoAction(ActionEvent actionEvent) throws IOException {
        //aggiungo nel database creo fxml con label e immagine avente come nome questo textField  !!!!!!!!!!!!!!!!!!
        if (fieldCreateNameAllenamento.getPromptText().equals(PROMPT_TEXT)) { //se è così non ho acceduto dal pulsante modifica ma da aggiungiAllenamento
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
                                //SceneSecondaryHandler.getInstance().aggiungiSchedaPersonaleScene(fieldCreateNameAllenamento.getText());
                                GridPaneAllenamentiHandler.getInstance().aggiungiSchedaPersonaleScene(fieldCreateNameAllenamento.getText());
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
        else{    //se sono qui vuol dire che il promptText non era vuoto -> quindi sono arrivato da button Modifica
            if (vBoxTuoAllenamento.getChildren().size() == 0) {
                return;
            }
            RemoveSchedaService removeSchedaService = new RemoveSchedaService();
            removeSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),fieldCreateNameAllenamento.getPromptText());
            removeSchedaService.restart();
            removeSchedaService.setOnSucceeded(event ->{
                if (removeSchedaService.getValue()) {
                    String nomeAllenamento;
                    if(!fieldCreateNameAllenamento.getText().equals("")) {
                        nomeAllenamento= fieldCreateNameAllenamento.getText();
                        //se ha cambiato nome allenamento devo cambiarlo dalla grafica, in quanto gli allenamenti vengono caricati
                        //dal database solo al login dell'utente per efficienza. altrimenti ad ogni cambiamento o piccola modifica
                        //dovrei fare ogni volta una query al database per aggiungere poi gli allenamenti al gridPane.
                        //SceneSecondaryHandler.getInstance().setModificaNomeAllenamento(nomeAllenamento,fieldCreateNameAllenamento.getPromptText());
                        GridPaneAllenamentiHandler.getInstance().setModificaNomeAllenamento(nomeAllenamento,fieldCreateNameAllenamento.getPromptText());
                        //sul promptText ho il nome vecchio, sul text ho il nome nuovo
                    }
                    else{nomeAllenamento= fieldCreateNameAllenamento.getPromptText();}
                    AddSchedaService addSchedaService = new AddSchedaService();
                    addSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),nomeAllenamento, CreateAllenamentoHandler.getInstance().getEserciziAggiuntiScheda());
                    addSchedaService.restart();
                    addSchedaService.setOnSucceeded(event1 ->{
                        if (addSchedaService.getValue()) {
                            try {
                                //SceneSecondaryHandler.getInstance().aggiungiSchedaPersonaleScene(fieldCreateNameAllenamento.getText());
                                SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
                            } catch (IOException ignoredEvent) {}
                        } else {
                            System.out.println("Scheda non aggiunta");
                        }
                    });
                }
                else{
                    System.out.println("Scheda non rimossa");
                }
            });
        }
    }
    public void initialize() throws IOException {
        CreateAllenamentoHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        CreateAllenamentoHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
        CreateAllenamentoHandler.getInstance().setFieldNameAllenamento(fieldCreateNameAllenamento);
        CreateAllenamentoHandler.getInstance().caricaEserciziVbox(fieldCreateNameAllenamento.getPromptText());
        CreateAllenamentoHandler.getInstance().setScegliNomeText(scegliNomeText);
        CreateAllenamentoHandler.getInstance().setSaveAllenamentoButton(saveAllenamentoButton);
    }
    public void indietroAction(ActionEvent actionEvent) {SceneSecondaryHandler.getInstance().CreateLastScene();}
}
