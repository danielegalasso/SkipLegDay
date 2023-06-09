package com.example.skiplegday.view;

import com.example.skiplegday.controller.ShortCut;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
            this.stage.initStyle(StageStyle.UNDECORATED);
            createLoginScene();  //funzione che imposta la scene dello SceneHandler
            loadFonts();            //da aggiungere
            //setCSSForScene(scene);  //da aggiungere
            this.stage.setScene(scene);
            this.stage.show();
        }
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    private Parent loadRootFromFXMLandSetController(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        Parent n= fxmlLoader.load();
        n.getProperties().put("foo", fxmlLoader.getController());
        return n;
    }
    public void createLoginScene() {
        try {
            if(scene == null) {
                scene = new Scene(loadRootFromFXML("login.fxml"));
            }
            else {   //se non c'è la creo altrimenti la modifico
                scene.setRoot(loadRootFromFXML("login.fxml"));
            }
            stage.setWidth(400);
            stage.setHeight(380);
            setCentre();
            stage.setResizable(false);
        } catch (IOException e) {
            ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della schermata di login");
        }
    }
    public void createHomeScene(){
        try {
            scene.setRoot(loadRootFromFXML("home.fxml"));
            //-----------------------------------
            // Creazione della combinazione di tasti per l'evento "Esci"
            ShortCut.addExitShortCut(scene);
            stage.setWidth(1000);
            stage.setHeight(700);
            setCentre();
            stage.setResizable(false);
            SceneSecondaryHandler.getInstance().createSchedePersonaliScene();

        } catch (IOException e) {
            ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della schermata home");
        }
    }
    public void createRegistraScene() {
        try {
            Parent root = loadRootFromFXML("register.fxml");
            scene.setRoot(root);
            lastSceneRegister =root;
            stage.setWidth(420);
            stage.setHeight(420);
            setCentre();
            stage.setResizable(false);
        } catch (IOException e) {
            ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della schermata di registrazione");
        }
    }
    public Parent createRegister2PhaseScene(){
        try {
            if (nexttSceneRegisterP2 == null)
                nexttSceneRegisterP2 = loadRootFromFXMLandSetController("register2phase.fxml");
            scene.setRoot(nexttSceneRegisterP2);

            stage.setWidth(420);
            stage.setHeight(420);
            setCentre();
            stage.setResizable(false);
            return nexttSceneRegisterP2;
        } catch (IOException ignored) {
            ignored.printStackTrace();
            ErrorMessage.getInstance().showErrorMessage("Errore durante il caricamento della schermata di registrazione");
        }
        return null;
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
    private Parent lastSceneRegister;
    private Parent nexttSceneRegisterP2;
    public void tornaRegisterScene() { //createLastScene
        scene.setRoot(lastSceneRegister);
        setCentre();
    }
    public void resetRegisterPhase2() {
        nexttSceneRegisterP2 = null;
    }
}
