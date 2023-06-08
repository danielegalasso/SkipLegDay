package com.example.skiplegday.controller;

import com.example.skiplegday.model.RemoveSchedaService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.ConfirmationAlert;
import com.example.skiplegday.view.GridPaneAllenamentiHandler;
import com.example.skiplegday.view.SceneSecondaryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SchedaPersonaleController {
    @FXML
    Label labelSchedaPersonalizzata;
    @FXML
    ImageView imageSchedaPersonalizzata; //questa era per il vecchio fxml
    @FXML
    ImageView imageV;
    @FXML
    Button deleteSchedaButton;
    @FXML
    AnchorPane paneCard;
    boolean modalitàDefault=false;
    public void setLabelSchedaPersonalizzata(String text) {
        labelSchedaPersonalizzata.setText(text);
    }
    public void accediSchedaPersonalizzata(ActionEvent actionEvent) throws IOException {
        if (modalitàDefault){
            SceneSecondaryHandler.getInstance().createSchedaDefaultNameScene(labelSchedaPersonalizzata.getText());
        }
        else {
            labelSchedaPersonalizzata.getText();
            SceneSecondaryHandler.getInstance().setLastScene();
            SceneSecondaryHandler.getInstance().accediSchedaPersonalizzataScene(labelSchedaPersonalizzata.getText());
        }
    }
    public void deleteSchedaAction(ActionEvent actionEvent) {
        //devo cancellare la scheda dal database  !!!!!!!!!!!!!!!!!!!
        int res = ConfirmationAlert.showConfirmationAlert("Sei sicuro di voler eliminare la scheda?","Esci","Elimina");
        if (res !=0) return;
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
        //SceneSecondaryHandler.getInstance().repositionSchede(children);
        GridPaneAllenamentiHandler.getInstance().repositionSchede(children);
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
    public void setImageViewSchedaPersonalizzata(Image img) {
        imageV.setImage(img);
        imageV.setEffect(new DropShadow(5, Color.BLACK));


        Color imageColor = getAverageColor(imageV);
        paneCard.setStyle("-fx-background-color: " + toHexCode(imageColor) + ";");
        //paneCard.setOpacity(0.5);
    }
    public void setSchedaDefault() {
        modalitàDefault=true;
        deleteSchedaButton.setVisible(false);
    }
    private Color getAverageColor(ImageView img) {
        Image image = img.getImage();
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        double totalRed = 0;
        double totalGreen = 0;
        double totalBlue = 0;
        int totalPixels = width * height;
        // Ottenere il PixelReader associato all'immagine
        javafx.scene.image.PixelReader pixelReader = image.getPixelReader();
        // Calcolare la somma dei valori RGB di tutti i pixel
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                javafx.scene.paint.Color pixelColor = pixelReader.getColor(x, y);
                totalRed += pixelColor.getRed();
                totalGreen += pixelColor.getGreen();
                totalBlue += pixelColor.getBlue();
            }
        }
        // Calcolare i valori medi di RGB
        double averageRed = totalRed / totalPixels;
        double averageGreen = totalGreen / totalPixels;
        double averageBlue = totalBlue / totalPixels;
        return new Color(averageRed, averageGreen, averageBlue, 1.0);
    }
    private String toHexCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
