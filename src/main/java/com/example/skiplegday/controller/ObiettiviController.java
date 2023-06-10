package com.example.skiplegday.controller;

import com.example.skiplegday.model.ProgressiObiettivi;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ObiettiviController {
    @FXML
    VBox vBox;
    @FXML
    ProgressBar barAllenamentiPers;
    @FXML
    ProgressBar barGruppoMuscol;
    @FXML
    ProgressBar barAllenamentiMese;
    @FXML
    ProgressBar barNumAllenamenti;
    @FXML
    ProgressBar barDipConsecutive;
    @FXML
    ProgressBar barTrazioniConsecutive;
    @FXML
    ProgressBar barPancaPianaMaxPeso;
    @FXML
    Text maxKgPancaPiana;
    public void initialize(){
        ProgressiObiettivi.getInstance().setController(this);
    }
    public void setText(String idCercare, String result) {
        String accorpata= idCercare.replaceAll("\\s+","");
        System.out.println("setText: "+accorpata+" "+result);
        ObservableList<Node> children = vBox.getChildrenUnmodifiable();
        for (Node child : children) {
            if (child instanceof AnchorPane anchorPane){
                ObservableList<Node> children2 = anchorPane.getChildrenUnmodifiable();
                for (Node child2 : children2) {
                    if (child2 instanceof Text && child2.getId()!= null && child2.getId().equalsIgnoreCase(accorpata)) {
                        ((Text) child2).setText(result);
                    }
                }
            }
        }
    }
}
