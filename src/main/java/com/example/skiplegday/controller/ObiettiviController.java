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
    ProgressBar barCompletati;
    @FXML
    Text obiettiviCompletati;
    private int obiettiviComp=0;

    private int obiettiviTot=-1;

    public void initialize(){
        ProgressiObiettivi.getInstance().setController(this);
        ObservableList<Node> children = vBox.getChildrenUnmodifiable();
        for (Node child : children) {
            if (child instanceof AnchorPane anchorPane)
                obiettiviTot++;
        }
    }

    public void setText(String idCercare, String result) {
        String accorpata= idCercare.replaceAll("\\s+","");
        //System.out.println("setText: "+accorpata+" "+result);
        ObservableList<Node> children = vBox.getChildrenUnmodifiable();
        for (Node child : children) {
            if (child instanceof AnchorPane anchorPane){
                ObservableList<Node> children2 = anchorPane.getChildrenUnmodifiable();
                for (Node child2 : children2) {
                    if (child2 instanceof Text && child2.getId()!= null && child2.getId().equalsIgnoreCase(accorpata)) {
                        String testo = ((Text) child2).getText().split("/")[1];
                        Double num = Double.parseDouble(result);
                        Double den = Double.parseDouble(testo);
                        if (num >= den) {
                            result = testo;
                            obiettiviComp++;
                        }
                        ((Text) child2).setText(result+"/"+testo);
                        updateProgressBar(num, den, children2);
                    }
                }
            }
        }
        obiettiviCompletati.setText(obiettiviComp+"/"+obiettiviTot);
        double progress = (double) obiettiviComp/ obiettiviTot;
        barCompletati.setProgress(progress);
    }
    private void updateProgressBar(Double num, Double den, ObservableList<Node> children2){
        double progress = num / den;
        for (Node child2 : children2) {
            if (child2 instanceof ProgressBar && child2.getId()!= null) {
                ((ProgressBar) child2).setProgress(progress);
            }
        }
    }
}
