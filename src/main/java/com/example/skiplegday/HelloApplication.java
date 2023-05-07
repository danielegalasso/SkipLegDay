package com.example.skiplegday;

import com.example.skiplegday.view.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneHandler.getInstance().init(stage);
        SceneHandler.getInstance().createLoginScene();
    }

    public static void main(String[] args) {
        launch();
    }
}