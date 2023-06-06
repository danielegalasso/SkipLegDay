package com.example.skiplegday.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class GrafoStatisticaRadar extends Pane {
    public GrafoStatisticaRadar(ArrayList<String> categories, double[] dataValues) {
        /*
        Group root = new Group();
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Categoria 1");
        categories.add("Categoria 2");
        categories.add("Categoria 3");
        categories.add("Categoria 4");
        categories.add("Categoria 5");
        double[] dataValues = {1.5, 1, 0.5, 1,1}; // Valori dei punti dei dati per ogni categoria
        createRadarChart(root, categories, dataValues);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Grafico Radar");
        primaryStage.setScene(scene);
        primaryStage.show();
         */
        dataValues = normalizeDataValues(dataValues);
        int numCategories = categories.size();
        int centerX = 250;
        int centerY = 250;
        int radius = 200;

        double[] dataValues0 = new double[numCategories];
        Arrays.fill(dataValues0, 1);
        double[] dataValues1 = new double[numCategories];
        Arrays.fill(dataValues1, 3);
        double[] dataValues2 = new double[numCategories];
        Arrays.fill(dataValues2, 5);

        ArrayList<double[]> val = new ArrayList<>();
        val.add(dataValues);
        val.add(dataValues0);
        val.add(dataValues1);
        val.add(dataValues2);
        //linne per bellezza
        for (double[] v: val) {
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
            text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            getChildren().add(text);
        }

        // Creazione dei punti dei dati
        for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double value = dataValues[i];
            double pointX = centerX + value / 5.0 * radius * Math.sin(angle);
            double pointY = centerY - value / 5.0 * radius * Math.cos(angle);

            Circle circle = new Circle(pointX, pointY, 5, Color.RED);
            getChildren().add(circle);
        }

        // Creazione del poligono
        Polygon polygon = new Polygon();
        for (int i = 0; i < numCategories; i++) {
            double angle = 2 * Math.PI * i / numCategories;
            double value = dataValues[i];
            double pointX = centerX + value / 5.0 * radius * Math.sin(angle);
            double pointY = centerY - value / 5.0 * radius * Math.cos(angle);

            polygon.getPoints().addAll(pointX, pointY);
        }
        polygon.setFill(Color.RED.deriveColor(1, 1, 1, 0.5));
        getChildren().add(polygon);
    }
    private double[] normalizeDataValues(double[] array){
        // Trova il massimo valore nell'array
        double max = Arrays.stream(array).max().getAsDouble();

        // Calcola il fattore di scala per la proporzione
        double scaleFactor = 5.0 / max;

        // Applica la trasformazione proporzionale agli elementi dell'array7
        double[] d = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            d[i] = array[i] * scaleFactor;
        }
        return d;

    }
}
