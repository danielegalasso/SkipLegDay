package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneSecondaryHandler;
import com.example.skiplegday.view.StatisticheHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

public class StatisticheController {
    @FXML
    BorderPane setCalendarRoot;
    @FXML
    AnchorPane setGrafoRadarRoot;
    @FXML
    AnchorPane setGrafoRoot;
    @FXML
    Text textChiliSollevati;
    @FXML
    Text textPercentualeProgresso;
    @FXML
    ImageView downImage;
    @FXML
    ImageView upImage;
    public void initialize() {
        StatisticheHandler.getInstance().setCalendarRoot(setCalendarRoot);
        StatisticheHandler.getInstance().setGrafoRadarRoot(setGrafoRadarRoot);
        StatisticheHandler.getInstance().setGrafoRoot(setGrafoRoot);
        StatisticheHandler.getInstance().setTextChiliSollevati(textChiliSollevati);
        StatisticheHandler.getInstance().setTextPercentualeProgresso(textPercentualeProgresso);
        StatisticheHandler.getInstance().setDownImage(downImage);
        StatisticheHandler.getInstance().setUpImage(upImage);
    }
}
