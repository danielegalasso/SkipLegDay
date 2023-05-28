package com.example.skiplegday.controller;

import com.example.skiplegday.model.AddSchedaService;
import com.example.skiplegday.model.CheckSchedaGiaPresenteService;
import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.AllenamentoHandler;
import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

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
        CheckSchedaGiaPresenteService checkSchedaGiaPresenteService = new CheckSchedaGiaPresenteService();
        checkSchedaGiaPresenteService.setDati(UtenteAttuale.getInstance().getUsername(),fieldCreateNameAllenamento.getText());
        checkSchedaGiaPresenteService.restart();
        checkSchedaGiaPresenteService.setOnSucceeded(event ->{
            if (checkSchedaGiaPresenteService.getValue()) {
                AddSchedaService addSchedaService = new AddSchedaService();
                //QUANDO INVECE MODIFICO DEVO CAPIRE DA DOVE PRENDERE IL NOME DELLA SCHEDA ALLENAMENTO VVVVV
                addSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),fieldCreateNameAllenamento.getText(), SceneSecondaryHandler.getInstance().getEserciziAggiuntiScheda());
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
    public void initialize(){
        SceneSecondaryHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        SceneSecondaryHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
        SceneSecondaryHandler.getInstance().setFieldNameAllenamento(fieldCreateNameAllenamento);
        //SceneSecondaryHandler.getInstance().caricaEserciziVbox();  decommentare !!!!!!!!
    }
    public void indietroAction(ActionEvent actionEvent) {
        SceneSecondaryHandler.getInstance().CreateLastScene();
    }

}
