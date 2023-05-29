package com.example.skiplegday.view;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class StatisticheHandler {
    private BorderPane paneCalendar;
    private AnchorPane paneGrafoRadar;
    private AnchorPane paneGrafo;
    private Text textChiliSollevati;
    private Text textPercentualeProgresso;
    private ImageView downImage;
    private ImageView upImage;
    private static StatisticheHandler instance = new StatisticheHandler();
    private StatisticheHandler() {}
    public static StatisticheHandler getInstance() {return instance;}
    public void setCalendarRoot(BorderPane setCalendarRoot) {
        this.paneCalendar=setCalendarRoot;
    }
    public void loadCalendar() {
        DatePicker datePicker = new DatePicker(LocalDate.now());
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node popupContent = datePickerSkin.getPopupContent();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Data selezionata: " + newValue);
        });
        paneCalendar.setCenter(popupContent);
        System.out.println("Calendar loaded");
    }
    public void loadGrafoRadar() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Categoria 1");
        categories.add("Categoria 2");
        categories.add("Categoria 3");
        categories.add("Categoria 4");
        categories.add("Categoria 5");
        double[] dataValues = {1.5, 1, 0.5, 1,1};
        paneGrafoRadar.getChildren().setAll(new GrafoStatisticaRadar( categories, dataValues));
    }
    public void loadGrafo() {
        System.out.println("width: "+paneGrafo.getPrefWidth()+" height: "+paneGrafo.getPrefHeight());
        paneGrafo.getChildren().setAll(new GrafoStatisticheEsercizio(paneGrafo.getPrefWidth(), paneGrafo.getPrefHeight()));
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
}
