package com.example.skiplegday.controller;

import com.example.skiplegday.view.ErrorMessage;
import com.example.skiplegday.view.ManualeEserciziHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class ManualeEserciziController {
    @FXML
    private Button indexesButton;
    @FXML
    private ScrollPane scrollPaneManualeEs;
    @FXML
    private AnchorPane paneDescrizioneRoot;
    public void scrollToIndexesAction(ActionEvent actionEvent) {
        scrollPaneManualeEs.setVvalue(0.0);
    }
    public void initialize(){
        ManualeEserciziHandler.getInstance().setScrollPane(scrollPaneManualeEs);
        ManualeEserciziHandler.getInstance().setPaneDescrizioneRoot(paneDescrizioneRoot);
    }
    public void filterAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof MenuItem menuItem){
            setMenuButtonName(menuItem);
            ManualeEserciziHandler.getInstance().filter(menuItem.getText());
        }
        else{
            ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento esercizi");
        }
    }
    public void filterAllAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() instanceof MenuItem menuItem) {
            setMenuButtonName(menuItem);
        }
        ManualeEserciziHandler.getInstance().aggiungiManualeEsercizi();
    }
    private void setMenuButtonName(MenuItem menuItem){
        ContextMenu contextMenu = menuItem.getParentPopup();
        if (contextMenu != null && contextMenu.getOwnerNode() instanceof MenuButton menuButton) {
            menuButton.setText(menuItem.getText());
        }
    }
}
