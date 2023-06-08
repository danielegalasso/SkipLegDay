package com.example.skiplegday.view;

import com.example.skiplegday.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatisticheHandler {
    private BorderPane paneCalendar;
    private AnchorPane paneGrafoRadar;
    private AnchorPane paneGrafo;
    private Text textChiliSollevati;
    private Text textPercentualeProgresso;
    private ImageView downImage;
    private ImageView upImage;

    private final GetDatesOfTrainings service = new GetDatesOfTrainings();
    private static StatisticheHandler instance = new StatisticheHandler();
    private StatisticheHandler() {}
    public static StatisticheHandler getInstance() {return instance;}
    public void setCalendarRoot(BorderPane setCalendarRoot) {
        this.paneCalendar=setCalendarRoot;
    }
    public void loadCalendar() {
        DatePicker datePicker = new DatePicker(LocalDate.now());






        service.setDati(UtenteAttuale.getInstance().getUsername()); //username DOMENICO
        service.restart();
        service.setOnSucceeded(event ->  {
            if(event.getSource().getValue() instanceof CalendarResult result) {
                datePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        // Verifica le condizioni per colorare la cella
                        System.out.println(date);
                        String d = String.valueOf(date);
                        if (date != null) {
                            System.out.println(result.allElements());

                            if (result.allElements().contains(d)) {
                                setStyle("fx-text-fill: white;\n" +
                                        "-fx-background-color: #b8fcb8;");
                            }
                        }
                    }
                });
            }
            DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
            Node popupContent = datePickerSkin.getPopupContent();
            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                System.out.println("Data selezionata: " + newValue);
                DaDataAAllenamentoService getAllenamento = new DaDataAAllenamentoService();
                getAllenamento.setDati(UtenteAttuale.getInstance().getUsername(), newValue.toString());  //IL PATTERN DELLA DATA VA BENE??
                getAllenamento.restart();
                getAllenamento.setOnSucceeded(event1 -> {
                    ArrayList<Object> esercizi = getAllenamento.getValue();
                    System.out.println("Esercizi: " + esercizi);
                    if (esercizi.get(0) == "") {
                        ErrorMessage.getInstance().showErrorMessage("Non hai effettuato un allenamento in questa data");
                    } else {
                        System.out.println("Allenamento trovato");
                        try {
                            SceneSecondaryHandler.getInstance().setLastScene();
                            SceneSecondaryHandler.getInstance().accediSchedaPersonalizzataScene((String) esercizi.get(0)); //newValue.toString()
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            });
            paneCalendar.setCenter(popupContent);
            System.out.println("Calendar loaded");


        });
        service.setOnFailed(event -> {System.err.println(event.getSource().getException().getMessage());});








    }
    public void loadGrafoRadar() {
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Double> dataValues = new ArrayList<>();
        //----

        CalcolaPesoGruppiMuscolariService getHash = new CalcolaPesoGruppiMuscolariService();  //devo salvarmi i dati in utente attuale!!!!!!!!!!!!!!!
        getHash.setDati(UtenteAttuale.getInstance().getUsername());
        getHash.restart();
        getHash.setOnSucceeded(event -> {  //va fatto solo all'inizio?????? altrimenti sarebbe inefficiente
            HashMap<String, Double> pesoGruppiMuscolari = getHash.getValue();
            for (Map.Entry<String, Double> entry : pesoGruppiMuscolari.entrySet()) {
                String gruppoMuscolare = entry.getKey();
                double peso = entry.getValue();
                System.out.println("aaaaaaaaaaaaaaaaaaa" + gruppoMuscolare + peso);
                categories.add(gruppoMuscolare);
                dataValues.add(peso);
                paneGrafoRadar.getChildren().setAll(new GrafoStatisticaRadar( categories, dataValues));

            }
        });


    }
    public void loadGrafo() throws IOException {
        /*
        System.out.println("width: "+paneGrafo.getPrefWidth()+" height: "+paneGrafo.getPrefHeight());
        paneGrafo.getChildren().setAll(new GrafoStatisticheEsercizio(paneGrafo.getPrefWidth(), paneGrafo.getPrefHeight()));*/
        Node grafo = (Node) loadRootFromFXML("graficoGenerale.fxml");
        paneGrafo.getChildren().setAll(grafo);
    }
    public void setGrafoRadarRoot(AnchorPane setGrafoRadarRoot) {
        this.paneGrafoRadar=setGrafoRadarRoot;
    }

    public void setGrafoRoot(AnchorPane setGrafoRoot) {
        this.paneGrafo=setGrafoRoot;
    }

    public void setTextChiliSollevati(Text textChiliSollevati) {
        this.textChiliSollevati=textChiliSollevati;
    }

    public void setTextPercentualeProgresso(Text textPercentualeProgresso) {
        this.textPercentualeProgresso=textPercentualeProgresso;
    }

    public void setDownImage(ImageView downImage) {
        this.downImage=downImage;
    }

    public void setUpImage(ImageView upImage) {
        this.upImage=upImage;
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
}
