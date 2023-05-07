package com.example.skiplegday.view;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHandler {
    private static final SceneHandler instance = new SceneHandler();
    private SceneHandler() {}
    public static SceneHandler getInstance() {return instance;}
    private Stage stage;
    private Scene scene;
    public void init(Stage stage) {
        if(this.stage==null){
            this.stage = stage;
            this.stage.setTitle("SkipLegDay");
            createLoginScene();  //funzione che imposta la scene dello SceneHandler
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
            stage.setWidth(800);
            stage.setHeight(600);
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

    private void setCentre(){
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double x = (bounds.getWidth() - scene.getWidth()) / 2;
        double y = (bounds.getHeight() - scene.getHeight()) / 2;
        stage.setX(x);
        stage.setY(y);
    }
}
