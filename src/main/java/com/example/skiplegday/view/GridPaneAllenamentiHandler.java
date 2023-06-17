package com.example.skiplegday.view;

import com.example.skiplegday.controller.CardSchedaController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private GridPane gridPane;
    private int columnCount = 3;//gridPane.getColumnCount();
    private int rowIndex = 0;
    private int columnIndex = 0;
    void setPosInitialGridPane() {
        rowIndex = 0;
        columnIndex = 0;
    }
    public void aggiungiSchedaPersonaleScene(String nameExercise) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/skiplegday/cardScheda.fxml"));
        Node node = loader.load();
        CardSchedaController controller = loader.getController();
        controller.setLabelCardScheda(nameExercise);
        controller.setImageViewSchedaPersonalizzata(randomImage());
        addSchedaNextPositionGridPane(node);
    }
    private ArrayList<Integer> immaginiUscite = new ArrayList<>();
    private Image randomImage(){  //genera un numero random da 0 a 10 e ritorna l'immagine corrispondente per la schedaCard
        Random random = new Random();
        int min = 0; // Valore minimo
        int max = 10; // Valore massimo
        if (immaginiUscite.size() == 10) { //se ho aggiunto tutte le immagini le resetto, in modo da non avere immagini uguali
            immaginiUscite.clear();        //finchè non escono tutte
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
            addSchedaNextPositionGridPane(node);
        }
    }
    public void setGridPaneSchede(GridPane gridPane) {
        this.gridPane = gridPane;
    }
    public void setModificaNomeSchedaAllenamento(String nomeAllenamentoNuovo, String nomeAllenamentoVecchio) {
        ObservableList<Node> children = gridPane.getChildren();
        for (Node node : children) {
            if (node instanceof AnchorPane anchorPane){
                if(anchorPane.getChildren().get(0) instanceof Label label) {
                    if(label.getText().equals(nomeAllenamentoVecchio)) {
                        label.setText(nomeAllenamentoNuovo);
                        return;
                    }
                }
            }
        }
        ErrorMessage.getInstance().showErrorMessage("Errore modifica nome allenamento");
        //cambio il nome della scheda allenamento visivamente per renderlo piu efficiente, altrimenti dovrei ogni volta
        // ricaricare la pagina facendo la query al db per le modifiche. Molto meglio salvare nel db, ma non rifare la
        // query per ottenere la modifica, bensi solo modificarlo visivamente. Al prossimo login sarà comunque visibile
        // in quanto all'avvio prende i dati dal db, ovviamente modificati
    }
}
