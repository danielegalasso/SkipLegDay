package com.example.skiplegday.controller;

import com.example.skiplegday.model.*;
import com.example.skiplegday.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class CreateSchedaAllenamentoController {
    @FXML
    Text scegliNomeText;
    @FXML
    TextField fieldCreateNameScheda;
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
    public void saveSchedaAllenamentoAction(ActionEvent actionEvent) throws IOException {
        //aggiungo nel database creo fxml con label e immagine avente come nome questo textField  !!!!!!!!!!!!!!!!!!
        if (fieldCreateNameScheda.getPromptText().equals(PROMPT_TEXT)) { //se è così non ho acceduto dal pulsante modifica ma da aggiungiAllenamento
            if(fieldCreateNameScheda.getText().equals("") || vBoxTuoAllenamento.getChildren().size() == 0){
                errorText.setText("Inserisci nome e almeno un esercizio");
                errorText.setVisible(true);
                return;
            }
            CheckSchedaInDbService checkSchedaInDbService = new CheckSchedaInDbService();
            checkSchedaInDbService.setDati(UtenteAttuale.getInstance().getUsername(), fieldCreateNameScheda.getText());
            checkSchedaInDbService.restart();
            checkSchedaInDbService.setOnSucceeded(event ->{
                if (!checkSchedaInDbService.getValue()) { //se non esiste la scheda la aggiungo
                    AddSchedaService addSchedaService = new AddSchedaService();
                    //QUANDO INVECE MODIFICO DEVO CAPIRE DA DOVE PRENDERE IL NOME DELLA SCHEDA ALLENAMENTO VVVVV
                    addSchedaService.setDati(UtenteAttuale.getInstance().getUsername(), fieldCreateNameScheda.getText(), CreateSchedaAllenamentoHandler.getInstance().getEserciziAggiuntiScheda());
                    addSchedaService.restart();
                    addSchedaService.setOnSucceeded(event1 ->{
                        if (addSchedaService.getValue()) {
                            try {
                                GridPaneAllenamentiHandler.getInstance().aggiungiSchedaPersonaleScene(fieldCreateNameScheda.getText());
                                SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
                            } catch (IOException e) {
                                ErrorMessage.getInstance().showErrorMessage("Errore durante l'aggiunta della scheda");
                            }
                        } else {
                            ErrorMessage.getInstance().showErrorMessage("Scheda non aggiunta");
                        }
                    });
                }
                else{
                    ErrorMessage.getInstance().showErrorMessage("Scheda già presente");
                }
            });
        }
        else{    //se sono qui vuol dire che il promptText non era vuoto -> quindi sono arrivato da button Modifica
            if (vBoxTuoAllenamento.getChildren().size() == 0) {
                return;
            }
            RemoveSchedaService removeSchedaService = new RemoveSchedaService();
            removeSchedaService.setDati(UtenteAttuale.getInstance().getUsername(), fieldCreateNameScheda.getPromptText());
            removeSchedaService.restart();
            removeSchedaService.setOnSucceeded(event ->{
                if (removeSchedaService.getValue()) {
                    String nomeAllenamento;
                    if(!fieldCreateNameScheda.getText().equals("")) {
                        nomeAllenamento= fieldCreateNameScheda.getText();
                        //se ha cambiato nome allenamento devo cambiarlo dalla grafica, in quanto gli allenamenti vengono caricati
                        //dal database solo al login dell'utente per efficienza. altrimenti ad ogni cambiamento o piccola modifica
                        //dovrei fare ogni volta una query al database per aggiungere poi gli allenamenti al gridPane.
                        GridPaneAllenamentiHandler.getInstance().setModificaNomeSchedaAllenamento(nomeAllenamento, fieldCreateNameScheda.getPromptText());
                        //sul promptText ho il nome vecchio, sul text ho il nome nuovo
                    }
                    else{nomeAllenamento= fieldCreateNameScheda.getPromptText();}
                    AddSchedaService addSchedaService = new AddSchedaService();
                    addSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),nomeAllenamento, CreateSchedaAllenamentoHandler.getInstance().getEserciziAggiuntiScheda());
                    addSchedaService.restart();
                    addSchedaService.setOnSucceeded(event1 ->{
                        if (addSchedaService.getValue()) {
                            try {
                                SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
                            } catch (IOException e) {
                                ErrorMessage.getInstance().showErrorMessage("Errore durante l'aggiunta della scheda");
                            }
                        } else {
                            ErrorMessage.getInstance().showErrorMessage("Scheda non aggiunta");
                        }
                    });
                }
                else{
                    ErrorMessage.getInstance().showErrorMessage("Scheda non rimossa");
                }
            });
        }
    }
    public void initialize() throws IOException {
        CreateSchedaAllenamentoHandler.getInstance().setScrollPaneEserciziAdd(scrollPaneEsercizi);
        CreateSchedaAllenamentoHandler.getInstance().setVBoxTuoAllenamento(vBoxTuoAllenamento);
        CreateSchedaAllenamentoHandler.getInstance().setFieldNameAllenamento(fieldCreateNameScheda);
        CreateSchedaAllenamentoHandler.getInstance().caricaEserciziVbox(); //non passo piu il fieldCrateNameScheda perchè non serve più, me lo prendo gia dal metodo di prima
        CreateSchedaAllenamentoHandler.getInstance().setScegliNomeText(scegliNomeText);
        CreateSchedaAllenamentoHandler.getInstance().setSaveAllenamentoButton(saveAllenamentoButton);

        InfoTooltip.agganciaTooltip(infoPane,"ShortCut:", "Ctrl + S per salvare l'allenamento");
    }
    public void indietroAction(ActionEvent actionEvent) {SceneSecondaryHandler.getInstance().CreateLastScene();}

    public void filterAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() instanceof MenuItem menuItem){
            setMenuButtonName(menuItem);   //serve per cambiare il MenuButton col nome del filtro che ho scelto
            CreateSchedaAllenamentoHandler.getInstance().filter(menuItem.getText());
        }
        else{
            ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento esercizi");
        }
    }
    public void filterAllAction(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() instanceof MenuItem menuItem){
            setMenuButtonName(menuItem);
            CreateSchedaAllenamentoHandler.getInstance().caricaManualeEsercizi();
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
