package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import com.example.skiplegday.view.StatisticheHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class StatisticheController {
    @FXML
    BorderPane setCalendarRoot;
    public void initialize() {
        StatisticheHandler.getInstance().setCalendarRoot(setCalendarRoot);
    }
}
