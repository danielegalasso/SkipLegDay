package com.example.skiplegday;
import com.example.skiplegday.model.LettoreFile;
import com.example.skiplegday.view.PopupHandler;
import com.example.skiplegday.view.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneHandler.getInstance().init(stage);
        SceneHandler.getInstance().createLoginScene();
        /*
        ArrayList<Object> lista = LettoreFile.getInstance().prendiHashListaeserciziNomigruppiDescrizioni();
        HashMap<String, String> descrizioni = (HashMap<String, String>)lista.get(3);
        Set<String> key=descrizioni.keySet();
        for (String k: key){
            System.out.println(descrizioni.get(k));
        }*/
    }
    public static void main(String[] args) {
        launch();
    }
}