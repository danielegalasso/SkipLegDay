package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import com.example.skiplegday.view.StatisticheHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class StatisticheController {
    @FXML
    AnchorPane setCalendarRoot;

    public void initialize() {
        StatisticheHandler.getInstance().setCalendarRoot(setCalendarRoot);
    }
}
