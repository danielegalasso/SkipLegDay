package com.example.skiplegday.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Popup extends Stage {  //è un normale stage su cui poi ci caricherò un fxml che sarà la finestra popup
    //non la creo singleton in quanto al costruttore devo passargli lo stage, e se è gia presente non lo posso risettare,
    //se la mettessi singleton, ogni volta che la richiamo dovrei fare setStage, ma se ne ha gia uno mi causa eccezione
    private String theme = "light";                                                  //da aggiungere
    private static final String RESOURCE_PATH = "/com/example/skiplegday/";         //da aggiungere
    private static final String CSS_PATH = RESOURCE_PATH + "css/";                  //da aggiungere
    private static final String FONTS_PATH = RESOURCE_PATH + "fonts/";              //da aggiungere
    private static final String THEMES_PATH = CSS_PATH + "themes/";                //da aggiungere
    public Popup(Stage ownerStage, Scene scene) {
        this.setScene(scene);
        this.setHeight(450);
        this.setWidth(300);
        //loadFonts();            //da aggiungere
        //setCSSForScene(scene);  //da aggiungere
        this.initModality(Modality.APPLICATION_MODAL); // Imposta la finestra secondaria come modale
        this.initOwner(ownerStage); // Imposta la finestra principale come proprietario della finestra secondaria
        this.showAndWait();
        ownerStage.toFront();
        ownerStage.requestFocus();
    }
    private void loadFonts() {
        for (String font : List.of(FONTS_PATH + "Lato/Lato-Regular.ttf", FONTS_PATH + "Lato/Lato-Bold.ttf")) {
            Font.loadFont(Objects.requireNonNull(SceneHandler.class.getResource(font)).toExternalForm(), 10);
        }
    }
    private List<String> loadCSS() {
        List<String> resources = new ArrayList<>();
        for (String style : List.of(THEMES_PATH + theme + ".css", CSS_PATH + "fonts.css", CSS_PATH + "style.css")) {
            String resource = Objects.requireNonNull(SceneHandler.class.getResource(style)).toExternalForm();
            resources.add(resource);
        }
        return resources;
    }
    private void setCSSForScene(Scene scene) {
        Objects.requireNonNull(scene, "Scene cannot be null");
        List<String> resources = loadCSS();
        scene.getStylesheets().clear();
        for(String resource : resources)
            scene.getStylesheets().add(resource);
    }
}
