package com.example.skiplegday.controller;

import com.example.skiplegday.model.RemoveSchedaService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SchedaPersonaleController {
    @FXML
    Label labelSchedaPersonalizzata;
    @FXML
    ImageView imageSchedaPersonalizzata;
    @FXML
    Button deleteSchedaButton;
    @FXML
    TextField fieldSetNameScheda;
    public void setLabelSchedaPersonalizzata(String text) {
        labelSchedaPersonalizzata.setText(text);
    }
    public void accediSchedaPersonalizzata(MouseEvent mouseEvent) throws IOException {
       //questa posso eliminarla, faccio tutto con la funzione on MOuseClicked
    }

    public void deleteSchedaAction(ActionEvent actionEvent) {
        //devo cancellare la scheda dal database  !!!!!!!!!!!!!!!!!!!
        RemoveSchedaService removeSchedaService = new RemoveSchedaService();
        removeSchedaService.setDati(UtenteAttuale.getInstance().getUsername(), labelSchedaPersonalizzata.getText());
        removeSchedaService.start();
        removeSchedaService.setOnSucceeded(event -> {
            Parent schedaPersonale = deleteSchedaButton.getParent(); // Ottieni il nodo padre (schedaPersonale.fxml)
            int row = GridPane.getRowIndex(schedaPersonale); // Ottieni l'indice di riga del nodo
            int col = GridPane.getColumnIndex(schedaPersonale); // Ottieni l'indice di colonna del nodo
            GridPane gridPane = (GridPane) schedaPersonale.getParent(); // Ottieni il nodo padre (GridPane)
            removeAndShiftNodes(gridPane, row, col);
        });
    }
    public void removeAndShiftNodes(GridPane gridPane, int row, int column) {
        List<Node> children = new ArrayList<>(gridPane.getChildren());
        Node nodeToRemove = getNodeInGridPane(children, row, column);  // Ottieni il nodo da rimuovere
        children.remove(nodeToRemove);
        gridPane.getChildren().clear();
        SceneSecondaryHandler.getInstance().repositionSchede(children);
    }
    public Node getNodeInGridPane(List<Node> children, int row, int column) {
        for (Node node : children) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeColumn = GridPane.getColumnIndex(node);
            if (nodeRow != null && nodeColumn != null && nodeRow == row && nodeColumn == column) {
                return node;
            }
        }
        return null;
    }
    public void setNameSchedaAction(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getClickCount() == 2) {   //da caccaire non ha senso
            // Nascondi la label
            labelSchedaPersonalizzata.setVisible(false);

            // Mostra il TextField e imposta il testo della label come testo iniziale del TextField
            fieldSetNameScheda.setVisible(true);
            fieldSetNameScheda.setText(labelSchedaPersonalizzata.getText());
            fieldSetNameScheda.requestFocus();
        }
        else{
            System.out.println("Accedi scheda personalizzata");
            labelSchedaPersonalizzata.getText();
            SceneSecondaryHandler.getInstance().accediSchedaPersonalizzataScene(labelSchedaPersonalizzata.getText());
            //e co sto testo devo trova l'allenamento nel database dal model
        }
    }
    @FXML
    private void handleTextFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // Sostituisci il testo della label con il testo del TextField
            labelSchedaPersonalizzata.setText(fieldSetNameScheda.getText());

            // Nascondi il TextField e mostra di nuovo la label
            fieldSetNameScheda.setVisible(false);
            labelSchedaPersonalizzata.setVisible(true);
        } else if (event.getCode() == KeyCode.ESCAPE) {
            // Annulla la modifica e ripristina il testo originale della label
            fieldSetNameScheda.setVisible(false);
            labelSchedaPersonalizzata.setVisible(true);
        }
    }
}
