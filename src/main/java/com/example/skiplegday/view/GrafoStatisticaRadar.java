package com.example.skiplegday.view;

import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.text.Text;


public class GrafoStatisticaRadar extends Pane {
    private Text weightText;

    public GrafoStatisticaRadar(ArrayList<String> categories, ArrayList<Double> dataValues) {
        weightText = new Text();
        weightText.setFill(Color.BLUE);
        getChildren().add(weightText);

        ArrayList<Double> inputData = dataValues;
        dataValues = normalizeDataValues(dataValues);
        int numCategories = categories.size();
        int centerX = 400;
        int centerY = 250;
        int radius = 200;

        double[] dataValues0 = new double[numCategories];
        Arrays.fill(dataValues0, 1);
        double[] dataValues1 = new double[numCategories];
        Arrays.fill(dataValues1, 3);
        double[] dataValues2 = new double[numCategories];
        Arrays.fill(dataValues2, 5);

        ArrayList<double[]> val = new ArrayList<>();
        val.add(dataValues.stream().mapToDouble(Double::doubleValue).toArray());
        val.add(dataValues0);
        val.add(dataValues1);
        val.add(dataValues2);

        //linee per bellezza
        for (double[] v : val) {
            Polygon polygon1 = new Polygon();
            for (int i = 0; i < numCategories; i++) {
                double angle = 2 * Math.PI * i / numCategories;
                double value = v[i];
                double pointX = centerX + value / 5.0 * radius * Math.sin(angle);
                double pointY = centerY - value / 5.0 * radius * Math.cos(angle);
                polygon1.getPoints().addAll(pointX, pointY);
            }
            polygon1.setStroke(Color.BLACK);
            polygon1.setFill(Color.TRANSPARENT);
            getChildren().add(polygon1);
        }

        // Creazione delle linee radiali (assi)
        for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double endX = centerX + radius * Math.sin(angle);
            double endY = centerY - radius * Math.cos(angle);

            Line axis = new Line(centerX, centerY, endX, endY);
            axis.setStroke(Color.BLACK);
            getChildren().add(axis);
        }

        // Creazione delle etichette delle categorie
        for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double textX = centerX + (radius + 20) * Math.sin(angle);
            double textY = centerY - (radius + 20) * Math.cos(angle);

            Text text = new Text(textX, textY, categories.get(i));
            text.getStyleClass().add("text-radar");
            getChildren().add(text);
        }

        // Creazione dei punti dei dati
        /*for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double value = dataValues.get(i);
            double pointX = centerX + value / 5.0 * radius * Math.sin(angle);
            double pointY = centerY - value / 5.0 * radius * Math.cos(angle);

            Circle circle = new Circle(pointX, pointY, 5, Color.RED);
            getChildren().add(circle);
        }*/

        for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double value = dataValues.get(i);
            double pointX = centerX + value / 5.0 * radius * Math.sin(angle);
            double pointY = centerY - value / 5.0 * radius * Math.cos(angle);

            Circle circle = new Circle(pointX, pointY, 5, Color.RED);
            getChildren().add(circle);

            // Aggiungi il listener per il movimento del mouse
            final int index = i;
            ArrayList<Double> finalDataValues = inputData;
            circle.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String weight = String.format("%.1f", finalDataValues.get(index)) + " kg";
                    weightText.setText(weight);

                    // Posiziona il testo sopra il punto del cerchio
                    weightText.relocate(pointX + 10, pointY - 10);
                    weightText.setVisible(true);
                }
            });

            circle.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    weightText.setVisible(false);
                }
            });
        }

        // Creazione del poligono
        Polygon polygon = new Polygon();
        for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double value = dataValues.get(i);
            double pointX = centerX + value / 5.0 * radius * Math.sin(angle);
            double pointY = centerY - value / 5.0 * radius * Math.cos(angle);

            polygon.getPoints().addAll(pointX, pointY);
        }
        polygon.setFill(Color.RED.deriveColor(1, 1, 1, 0.5));
        getChildren().add(polygon);
    }
    private ArrayList<Double> normalizeDataValues(ArrayList<Double> array){
        // Trova il massimo valore nell'array
        double max = array.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(Double.MIN_VALUE);

        // Calcola il fattore di scala per la proporzione
        double scaleFactor = 5.0 / max;

        // Applica la trasformazione proporzionale agli elementi dell'array
        ArrayList<Double> d = new ArrayList<>();
        for (Double elem: array) {
            d.add(elem * scaleFactor);
        }
        return d;

    }
}
