package com.example.skiplegday.view;

import com.example.skiplegday.controller.SchedaPersonaleController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridPaneAllenamentiHandler {
    private static GridPaneAllenamentiHandler instance = new GridPaneAllenamentiHandler();
    private GridPaneAllenamentiHandler(){}
    public static GridPaneAllenamentiHandler getInstance() {return instance;}
    private GridPane gridPane; //per le schedePersonali
    private int columnCount = 3;//gridPane.getColumnCount();
    private int rowIndex = 0;  //quando ricclicco su scheda personale le resetto, finche non prendo le cose da daniele
    private int columnIndex = 0;
    void setPosInitialGridPane() {
        rowIndex = 0;
        columnIndex = 0;
    }
    public void aggiungiSchedaPersonaleScene(String nameExercise) throws IOException {
        /*
        nameExercise= nameAllenamento(nameExercise);
        mieiAllenamenti.add(nameExercise);*/
        //Node node = (Node) loadRootFromFXML("schedaPersonale.fxml"); non posso farla con questa funzione perche mi serve
        //controller associato
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/schedaPersonale.fxml"));
        Node node = loader.load();
        SchedaPersonaleController controller = loader.getController();
        controller.setLabelSchedaPersonalizzata(nameExercise);
        controller.setImageViewSchedaPersonalizzata(randomImage());
        addSchedaNextPositionGridPane(node);
    }
    private ArrayList<Integer> immaginiUscite = new ArrayList<>();
    private Image randomImage(){
        Random random = new Random();
        int min = 0; // Valore minimo
        int max = 10; // Valore massimo
        if (immaginiUscite.size() == 10) { //se ho aggiunto tutte le immagini le resetto
            immaginiUscite.clear();
        }
        int n = random.nextInt(max - min + 1) + min;
        while (immaginiUscite.contains(n)) {
            n = random.nextInt(max - min + 1) + min;
        }
        immaginiUscite.add(n);
        return new Image(getClass().getResource("/com/example/skiplegday/imageSchede/img" + n + ".jpg").toExternalForm());
    }
    private void addSchedaNextPositionGridPane(Node node) {
        gridPane.add(node, columnIndex, rowIndex);
        columnIndex++;
        if (columnIndex >= columnCount) {
            columnIndex = 0;
            rowIndex++;
        }
    }
    public void repositionSchede(List<Node> children){
        columnCount = gridPane.getColumnCount();
        rowIndex = 0;
        columnIndex = 0;
        for (Node node : children) {
            // Riposiziona il nodo nel GridPane
            addSchedaNextPositionGridPane(node);
        }
    }
    public void setGridPaneSchede(GridPane gridPane) {
        this.gridPane = gridPane;
    }
    public void setModificaNomeAllenamento(String nomeAllenamentoNuovo, String nomeAllenamentoVecchio) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (node instanceof AnchorPane) {
                AnchorPane anchorPane = (AnchorPane) node;
                if(((Label) anchorPane.getChildren().get(0)).getText().equals(nomeAllenamentoVecchio)) {
                    ((Label) anchorPane.getChildren().get(0)).setText(nomeAllenamentoNuovo);
                }
            }
        }
    }
}
