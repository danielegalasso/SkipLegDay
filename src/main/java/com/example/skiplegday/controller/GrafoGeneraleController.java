package com.example.skiplegday.controller;

import com.example.skiplegday.model.DataResult;
import com.example.skiplegday.model.Data;
import com.example.skiplegday.model.PunteggiUtenteEsercizioService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.HoveredThresholdNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class GrafoGeneraleController {

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField es;
    @FXML
    private MenuButton menuButton;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    NumberAxis xAxis;

    @FXML
    NumberAxis yAxis;

    private String username = UtenteAttuale.getInstance().getUsername(); //DOMENICO
    private String nomeEsercizio = "panca piana";
    private final PunteggiUtenteEsercizioService service = new PunteggiUtenteEsercizioService();

    @FXML
    void process(ActionEvent ignoredEvent) {
        nomeEsercizio = es.getText();
        service.setDati(username, nomeEsercizio);
        service.restart();
    }

    @FXML
    void initialize() {
        ListView<MenuItem> listView = new ListView<>();
        listView.getItems().addAll(new MenuItem("Item 1"),new MenuItem("Item 1"),new MenuItem("Item 1"),new MenuItem("Item 1"));

        // Imposta l'altezza massima desiderata per la ListView
        listView.setMaxHeight(200);

        // Crea il CustomMenuItem e assegna la ListView come contenuto
        CustomMenuItem customMenuItem = new CustomMenuItem(listView);

        // Aggiungi il CustomMenuItem al MenuButton
        menuButton.getItems().add(customMenuItem);

        service.setDati(username, nomeEsercizio); //username DOMENICO
        service.start();
        service.setOnSucceeded(event ->  {
            //System.out.println("i");
            xAxis.setLabel("Giorno");
            yAxis.setLabel("Punteggio");

            String nomeEsercizio;
            String formattedStartDate;
            String formattedEndDate;
            if(startDate.getValue() == null || endDate.getValue() == null){
                formattedEndDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                formattedStartDate = LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            else{
                formattedStartDate = startDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //start DATEPICKER
                formattedEndDate = endDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));     //end DATEPICKER
            }
            if(event.getSource().getValue() instanceof DataResult result) {
                //System.out.println("a");
                XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>(); // definisco i dati come data e punteggio (nota: la data non è una stringa
                // poiché poi faccio la conversione, faccio questo perché voglio poter avere
                // la distanza che decido io nell'asse x, e lo faccio mediante le coordinate

                LocalDate firstDate = LocalDate.parse(formattedStartDate);  //start DATEPICKER LocalDate
                LocalDate lastDate = LocalDate.parse(formattedEndDate);  //end DATEPICKER LocalDate

                double intervallo_giorni = ChronoUnit.DAYS.between(firstDate, lastDate);
                ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList(); //è una lista di date e punteggi
                double min = Double.MAX_VALUE; // mi trovo il minimo di quel range perché anche se la prima data in cui ho un dato dista vari giorni voglio che inizia dopo un giorno l'asse x
                for (Data d : result.allElements()) {
                    if (d.date().compareTo(formattedStartDate) >= 0 && d.date().compareTo(formattedEndDate) <= 0){
                        LocalDate for_date = LocalDate.parse(d.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        long days = ChronoUnit.DAYS.between(firstDate, for_date);
                        if (days < min) {
                            min = days;
                        }
                    }
                }
                int slide_giorni = (int) (intervallo_giorni/25);
                for (Data d : result.allElements()) {
                    if (d.date().compareTo(formattedStartDate) >= 0 && d.date().compareTo(formattedEndDate) <= 0) {
                        LocalDate for_date = LocalDate.parse(d.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        long days = ChronoUnit.DAYS.between(firstDate, for_date);
                        //System.out.println(days);
                        XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(days-min +slide_giorni, d.punteggio());
                        data.add(dataPoint);
                        // Personalizzazione dell'etichetta del punto con la data
                        String dateLabel = for_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        dataPoint.setNode(new HoveredThresholdNode(dateLabel));
                    }
                }
                dataSeries.setData(data);
                lineChart.getData().clear();
                lineChart.getData().add(dataSeries);
                lineChart.setLegendVisible(false);
                xAxis.setAnimated(false);
                xAxis.setTickLabelFormatter(new StringConverter<Number>() {
                    @Override
                    public String toString(Number object) {
                        long days = object.longValue();
                        LocalDate date = firstDate.plusDays((long) (days+2 - slide_giorni));
                        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                    @Override
                    public Number fromString(String string) {
                        LocalDate date = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        long days = ChronoUnit.DAYS.between(firstDate, date);
                        return days;
                    }
                });
            }
        });
        service.setOnFailed(event -> {System.err.println(event.getSource().getException().getMessage());});
    }
}
