package com.example.skiplegday.controller;

import com.example.skiplegday.model.*;
import com.example.skiplegday.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    @FXML
    AnchorPane infoPane;
    @FXML
    ImageView infoIcon;
    @FXML
    Text errorText;
    private static final String PROMPT_TEXT = "inserisci nome";
    public void saveAllenamentoAction(ActionEvent actionEvent) throws IOException {
        //aggiungo nel database creo fxml con label e immagine avente come nome questo textField  !!!!!!!!!!!!!!!!!!
        if (fieldCreateNameAllenamento.getPromptText().equals(PROMPT_TEXT)) { //se è così non ho acceduto dal pulsante modifica ma da aggiungiAllenamento
            if(fieldCreateNameAllenamento.getText().equals("") || vBoxTuoAllenamento.getChildren().size() == 0){
                errorText.setText("Inserisci nome e almeno un esercizio");
                errorText.setVisible(true);
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
                            } catch (IOException e) {
                                ErrorMessage.getInstance().showErrorMessage("Errore durante l'aggiunta della scheda");
                            }
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
                            } catch (IOException e) {
                                ErrorMessage.getInstance().showErrorMessage("Errore durante l'aggiunta della scheda");
                            }
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

        InfoTooltip.agganciaTooltip(infoPane,"ShortCut:", "Ctrl + S per salvare l'allenamento");
    }
    public void indietroAction(ActionEvent actionEvent) {SceneSecondaryHandler.getInstance().CreateLastScene();}

    public void filterAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() instanceof MenuItem menuItem){
            setMenuButtonName(menuItem);   //serve per cambiare il MenuButton col nome del filtro che ho scelto
            CreateAllenamentoHandler.getInstance().filter(menuItem.getText());
        }
        else{
            ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento esercizi");
        }
    }
    public void filterAllAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() instanceof MenuItem menuItem){
            setMenuButtonName(menuItem);
            CreateAllenamentoHandler.getInstance().caricaManualeEsercizi();
        }
        else{
            ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento esercizi");
        }
    }
    private void setMenuButtonName(MenuItem menuItem){
        ContextMenu contextMenu = menuItem.getParentPopup();
        if (contextMenu != null && contextMenu.getOwnerNode() instanceof MenuButton menuButton) {
            menuButton.setText(menuItem.getText());
        }
    }
}
