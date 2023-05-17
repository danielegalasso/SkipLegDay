package com.example.skiplegday.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneHandler {
    private static final SceneHandler instance = new SceneHandler();
    private SceneHandler() {}
    public static SceneHandler getInstance() {return instance;}
    private Stage stage;
    private Scene scene;
    private String theme = "light";                                                  //da aggiungere
    private static final String RESOURCE_PATH = "/com/example/skiplegday/";         //da aggiungere
    private static final String CSS_PATH = RESOURCE_PATH + "css/";                  //da aggiungere
    private static final String FONTS_PATH = RESOURCE_PATH + "fonts/";              //da aggiungere
    private static final String THEMES_PATH = CSS_PATH + "themes/";                //da aggiungere
    public void init(Stage stage) {
        if(this.stage==null){
            this.stage = stage;
            this.stage.setTitle("SkipLegDay");
            createLoginScene();  //funzione che imposta la scene dello SceneHandler
            loadFonts();            //da aggiungere
            setCSSForScene(scene);  //da aggiungere
            this.stage.setScene(scene);
            this.stage.show();
        }
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public void createLoginScene() {
        try {
            if(scene == null) {
                scene = new Scene(loadRootFromFXML("login.fxml"));
            }
            else   //se non c'è la creo altrimenti la modifico
                scene.setRoot(loadRootFromFXML("login.fxml"));
            stage.setWidth(400);
            stage.setHeight(380);
            setCentre();
            stage.setResizable(false);
        } catch (IOException ignored) {
        }
    }
    public void createHomeScene(){
        try {
            scene.setRoot(loadRootFromFXML("home.fxml"));
            stage.setWidth(1000);
            stage.setHeight(700);
            setCentre();
            stage.setResizable(false);

        } catch (IOException ignored) {
        }
    }
    public void createRegistraScene() {
        try {
            scene.setRoot(loadRootFromFXML("register.fxml"));
            stage.setWidth(400);
            stage.setHeight(380);
            setCentre();
            stage.setResizable(false);
        } catch (IOException ignored) {
        }
    }
    private void setCentre() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primaryScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primaryScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
    void loadFonts() {
        for (String font : List.of(FONTS_PATH + "Lato/Lato-Regular.ttf", FONTS_PATH + "Lato/Lato-Bold.ttf")) {
            Font.loadFont(Objects.requireNonNull(SceneHandler.class.getResource(font)).toExternalForm(), 10);
        }
    }
    List<String> loadCSS() {
        List<String> resources = new ArrayList<>();
        for (String style : List.of(THEMES_PATH + theme + ".css", CSS_PATH + "fonts.css", CSS_PATH + "style.css")) {
            String resource = Objects.requireNonNull(SceneHandler.class.getResource(style)).toExternalForm();
            resources.add(resource);
        }
        return resources;
    }
    void setCSSForScene(Scene scene) {
        Objects.requireNonNull(scene, "Scene cannot be null");
        List<String> resources = loadCSS();
        scene.getStylesheets().clear();
        for(String resource : resources)
            scene.getStylesheets().add(resource);
    }
}
