package com.example.skiplegday.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SchedaPersonaleController {
    @FXML
    Label labelSchedaPersonalizzata;
    @FXML
    ImageView imageSchedaPersonalizzata;
    @FXML
    Button deleteSchedaButton;
    public void accediSchedaPersonalizzata(MouseEvent mouseEvent) {
        labelSchedaPersonalizzata.getText();
        //e co sto testo devo trova l'allenamento nel database dal model
    }

    public void deleteSchedaAction(ActionEvent actionEvent) {
        //devo cancellare la scheda dal database  !!!!!!!!!!!!!!!!!!!

        Parent schedaPersonale = deleteSchedaButton.getParent(); // Ottieni il nodo padre (schedaPersonale.fxml)
        int row = GridPane.getRowIndex(schedaPersonale); // Ottieni l'indice di riga del nodo
        int col = GridPane.getColumnIndex(schedaPersonale); // Ottieni l'indice di colonna del nodo
        GridPane gridPane = (GridPane) schedaPersonale.getParent(); // Ottieni il nodo padre (GridPane)
        removeAndShiftNodes(gridPane, row, col);
        /*
        gridPane.getChildren().remove(schedaPersonale);
        System.out.println("Riga: " + row + " Colonna: " + col);
        System.out.println("Righe: " + gridPane.getRowCount() + " Colonne: " + gridPane.getColumnCount());

        for (int rowIndex = row; rowIndex < gridPane.getRowCount(); rowIndex++) {
            for (int colIndex = col; colIndex < gridPane.getColumnCount(); colIndex++) {

            }
        }*/
    }
    public void removeAndShiftNodes(GridPane gridPane, int row, int column) {
        ObservableList<Node> children = gridPane.getChildren();

        // Rimuovi il nodo dalla posizione specificata
        Node nodeToRemove = getNodeInGridPane(gridPane, row, column);
        if (nodeToRemove != null) {
            children.remove(nodeToRemove);

            // Aggiorna le posizioni degli elementi successivi
            for (Node node : children) {
                Integer nodeRow = GridPane.getRowIndex(node);
                Integer nodeColumn = GridPane.getColumnIndex(node);

                if (nodeRow != null && nodeColumn != null && nodeRow+nodeColumn >= row + column) {
                    if (nodeColumn == 0) {
                        GridPane.setColumnIndex(node, gridPane.getColumnCount() - 1);
                    } else {
                        GridPane.setColumnIndex(node, nodeColumn - 1);
                    }
                   // GridPane.setRowIndex(node, nodeRow - 1);
                }
            }
        }
    }
    public Node getNodeInGridPane(GridPane gridPane, int row, int column) {
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeColumn = GridPane.getColumnIndex(node);

            if (nodeRow != null && nodeColumn != null && nodeRow == row && nodeColumn == column) {
                return node;
            }
        }
        return null;  // Nodo non trovato
    }
}
