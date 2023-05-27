package com.example.skiplegday.view;

import javafx.scene.layout.AnchorPane;

public class StatisticheHandler {
    private AnchorPane paneCalendar;
    private static StatisticheHandler instance = new StatisticheHandler();
    private StatisticheHandler() {}
    public static StatisticheHandler getInstance() {return instance;}
    public void setCalendarRoot(AnchorPane setCalendarRoot) {
        this.paneCalendar=setCalendarRoot;
    }

}
