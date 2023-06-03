package com.example.skiplegday.view;

import com.example.skiplegday.model.UtenteAttuale;
import com.example.skiplegday.model.daDataAAllenamentoService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.DayOfWeek;
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
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                // Verifica le condizioni per colorare la cella
                if (date != null) {
                    DayOfWeek dayOfWeek = date.getDayOfWeek();
                    if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                        setStyle("-fx-background-color: green;");
                    } else{
                        setStyle("-fx-background-color: red;");
                    }
                }
            }
        });
        DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        Node popupContent = datePickerSkin.getPopupContent();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Data selezionata: " + newValue);
            daDataAAllenamentoService getAllenamento = new daDataAAllenamentoService();
            getAllenamento.setDati(UtenteAttuale.getInstance().getUsername(), newValue.toString());  //IL PATTERN DELLA DATA VA BENE??
            getAllenamento.restart();
            getAllenamento.setOnSucceeded(event -> {
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
