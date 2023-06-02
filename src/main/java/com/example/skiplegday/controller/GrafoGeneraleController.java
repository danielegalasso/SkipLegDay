package com.example.skiplegday.controller;

import com.example.skiplegday.model.Data;
import com.example.skiplegday.model.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GrafoGeneraleController {
    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField region;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    CategoryAxis xAxis;

    @FXML
    NumberAxis yAxis;

    private final DataService service = new DataService();

    @FXML
    void process(ActionEvent ignoredEvent) {
        service.restart();
    }

    @FXML
    void initialize() {
        service.setOnSucceeded(event ->  {
            if(startDate.getValue() == null || endDate.getValue() == null)
                return;
            barChart.setTitle("Somministrazioni vaccini - Regione: " + region.getText());

            //if(event.getSource().getValue() instanceof ArrayList<D> result) {
                ArrayList<Data> result = (ArrayList<Data>) event.getSource().getValue();
                XYChart.Series<String, Number> firstShot = new XYChart.Series<>();
                firstShot.setName("Prima dose");
                XYChart.Series<String, Number> secondShot = new XYChart.Series<>();
                secondShot.setName("Seconda dose");

                String formattedStartDate = startDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String formattedEndDate = endDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                for (Data d : result) {
                    //le tue cose
                }
                /*for (VaxData d : result.allElements()) {
                    if (d.region().equalsIgnoreCase(region.getText()) && d.date().compareTo(formattedStartDate) >= 0 && d.date().compareTo(formattedEndDate) <= 0) {
                        firstShot.getData().add(new XYChart.Data<>(d.date(), d.firstShot()));
                        secondShot.getData().add(new XYChart.Data<>(d.date(), d.secondShot()));
                    }
                }*/
                barChart.getData().clear();
                barChart.getData().add(firstShot);
                barChart.getData().add(secondShot);
                xAxis.setAnimated(false);
            //}
        });
        service.setOnFailed(event -> System.err.println(event.getSource().getException().getMessage()));
    }
}
