package com.example.skiplegday.controller;

import com.example.skiplegday.model.CalcolaPesoSettimanaService;
import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.view.StatisticheHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicReference;

public class StatisticheController {
    @FXML
    public ScrollPane scroll;
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
    @FXML
    Label tooltipLabel1;
    @FXML
    Label tooltipLabel;

    private CalcolaPesoSettimanaService service = new CalcolaPesoSettimanaService();
    private String username = UtenteAttuale.getInstance().getUsername();
    public void initialize() {

        StatisticheHandler.getInstance().setCalendarRoot(setCalendarRoot);
        StatisticheHandler.getInstance().setGrafoRadarRoot(setGrafoRadarRoot);
        StatisticheHandler.getInstance().setGrafoRoot(setGrafoRoot);
        StatisticheHandler.getInstance().setTextChiliSollevati(textChiliSollevati);
        StatisticheHandler.getInstance().setTextPercentualeProgresso(textPercentualeProgresso);
        String path = getClass().getResource("/com/example/skiplegday/icon/statisticheIcon/greenTriangle.png").toExternalForm();
        String path1 = getClass().getResource("/com/example/skiplegday/icon/statisticheIcon/redTriangle.png").toExternalForm();


        //prendo il peso sollevato questa settimana, quella di prima e vedo qual è più alto




        service.setDati(username, false);
        service.start();
        service.setOnSucceeded(event -> {
            if (event.getSource().getValue() instanceof Double result) {
                textChiliSollevati.setText(String.valueOf(result));
                service.setDati(username, true);
                service.restart();
                service.setOnSucceeded(event2 -> {
                    if (event2.getSource().getValue() instanceof Double result2) {
                        if (result >= result2) {
                            upImage.setImage(new Image(path));
                            upImage.setOnMouseEntered(this::showTooltip);
                            upImage.setOnMouseExited(this::hideTooltip);
                        }
                        else{
                            downImage.setImage(new Image(path1));
                            downImage.setOnMouseEntered(this::showTooltip1);
                            downImage.setOnMouseExited(this::hideTooltip1);
                        }
                    }
                });
            }
        });








    }

    private void hideTooltip(javafx.scene.input.MouseEvent mouseEvent) {
        tooltipLabel.setVisible(false);
    }

    private void showTooltip(javafx.scene.input.MouseEvent mouseEvent) {
            tooltipLabel.setVisible(true);
    }

    private void hideTooltip1(javafx.scene.input.MouseEvent mouseEvent) {
        tooltipLabel1.setVisible(false);
    }

    private void showTooltip1(javafx.scene.input.MouseEvent mouseEvent) {
        tooltipLabel1.setVisible(true);
    }





}
