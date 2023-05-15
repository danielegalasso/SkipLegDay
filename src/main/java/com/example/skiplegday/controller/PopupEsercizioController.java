package com.example.skiplegday.controller;

import com.example.skiplegday.view.Popup;
import com.example.skiplegday.view.PopupHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PopupEsercizioController {
    @FXML
    Text nomeEsercizio;
    @FXML
    VBox vBoxDatiEsercizi;
    @FXML
    Button addDatiEsButton;
    private List<String> nomeEsList = new ArrayList<>();
    private List<Integer> kgList = new ArrayList<>();
    private List<Integer> repList= new ArrayList<>();
    private List<Integer> recList=new ArrayList<>(); //recupero espresso in secondi
    public void initialize(){
        PopupHandler.getInstance().setvBoxDatiEsercizi(vBoxDatiEsercizi);
        PopupHandler.getInstance().setText(nomeEsercizio);
    }
    public void addDatiEsAction(ActionEvent actionEvent) throws IOException {
        PopupHandler.getInstance().addDatiEsercizio();
    }
    public void salvaProgressiAction(ActionEvent actionEvent) {
        resetdati();
        System.out.println("salva progressi");
        ObservableList<Node> items = vBoxDatiEsercizi.getChildren();
        for (Node item : items) {
            if (item instanceof Parent) {
                Parent parent = (Parent) item;
                // Verifica se il nodo contiene i tre TextField
                if (parent.getChildrenUnmodifiable().size() == 3 && parent.getChildrenUnmodifiable().get(0) instanceof TextField) {
                    TextField textField1 = (TextField) parent.getChildrenUnmodifiable().get(0);
                    TextField textField2 = (TextField) parent.getChildrenUnmodifiable().get(1);
                    TextField textField3 = (TextField) parent.getChildrenUnmodifiable().get(2);
                    Integer kg = Integer.valueOf(textField1.getText());
                    Integer rep = Integer.valueOf(textField2.getText());
                    Integer rec = Integer.valueOf(textField3.getText());
                    nomeEsList.add(nomeEsercizio.getText());
                    kgList.add(kg);
                    repList.add(rep);
                    recList.add(rec);
                }
            }
        }
        System.out.println("ritorno a daniele galasso:");
        System.out.println(nomeEsList);
        System.out.println(kgList);
        System.out.println(repList);
        System.out.println(recList);
    }
    private void resetdati(){
        nomeEsList.clear();
        kgList.clear();
        repList.clear();
        recList.clear();
    }
}
