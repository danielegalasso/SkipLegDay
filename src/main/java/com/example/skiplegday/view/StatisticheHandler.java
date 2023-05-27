package com.example.skiplegday.view;

import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;

public class StatisticheHandler {
    private BorderPane paneCalendar;
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

}
