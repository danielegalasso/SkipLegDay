package com.example.skiplegday.view;

import com.example.skiplegday.model.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class GrafoStatisticheEsercizio extends Pane {
    public GrafoStatisticheEsercizio(Double width, Double height) {
        // Aggiunta dei dati alla serie
        ArrayList<Data> dataList = new ArrayList<>();
        dataList.add(new Data("2023-02-01", 100));
        dataList.add(new Data("2023-03-06", 170));
        dataList.add(new Data("2023-05-02", 110));
        dataList.add(new Data("2023-06-06", 170));
        dataList.add(new Data("2023-08-01", 120));
        // Calcolo della data di inizio come un giorno prima della data del primo dato inserito
        LocalDate firstDate = LocalDate.parse(dataList.get(0).date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Calcolo della data di fine come qualche giorno dopo l'ultima data inserita
        LocalDate lastDate = LocalDate.parse(dataList.get(dataList.size() - 1).date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //Calcolo lunghezza pannello
        double intervallo_giorni = ChronoUnit.DAYS.between(firstDate, lastDate);
        System.out.println(intervallo_giorni);
        double lunghezza_panello = intervallo_giorni*8;

        LocalDate startDate = firstDate.minusDays((long) (1*intervallo_giorni/20));
        LocalDate endDate = lastDate.plusDays((long) (1*intervallo_giorni/20));

        // Creazione dell'asse x come NumberAxis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Giorno");
        xAxis.setLowerBound(ChronoUnit.DAYS.between(startDate, firstDate));

        // Creazione dell'asse y come NumberAxis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Punteggio");

        // Creazione del grafico a linee
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Grafico Allenamenti");

        // Creazione della serie dati
        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Punteggio");

        ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList();
        for (int i = 0; i < dataList.size(); i++) {
            Data d = dataList.get(i);
            LocalDate currentDate = LocalDate.parse(d.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long days = ChronoUnit.DAYS.between(startDate, currentDate);
            XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(days, d.punteggio());
            data.add(dataPoint);

            // Personalizzazione dell'etichetta del punto con la data
            String dateLabel = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dataPoint.setNode(new HoveredThresholdNode(dateLabel));
        }
        dataSeries.setData(data);

        // Aggiunta della serie al grafico
        lineChart.getData().add(dataSeries);

        // Configurazione degli assi per mostrare le date corrette sull'asse x
        xAxis.setTickLabelFormatter(new javafx.util.StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                LocalDate date = startDate.plusDays(object.longValue());
                return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            @Override
            public Number fromString(String string) {
                LocalDate date = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return ChronoUnit.DAYS.between(startDate, date);
            }
        });
        ScrollPane root = new ScrollPane(lineChart);
        //root.setMinSize(lunghezza_panello, 600);
        root.setMinSize(width-10, height-30);
        root.setMaxSize(width-10, height-30);
        lineChart.setMinSize(lunghezza_panello, root.getMinHeight() - 20);
        lineChart.setMaxSize(lunghezza_panello, root.getMinHeight() - 20);
        getChildren().add(root);
    }
}
