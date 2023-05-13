package com.example.skiplegday;
import com.example.skiplegday.model.LettoreFile;
import com.example.skiplegday.view.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneHandler.getInstance().init(stage);
        SceneHandler.getInstance().createLoginScene();


        ArrayList<Object> lista = LettoreFile.getInstance().prendiHashListaeserciziNomigruppiDescrizioni();
        ArrayList<String> descrvhvhyjvyizioni = (ArrayList<String>)lista.get(3);
        for (String elem : descrizioni){
            System.out.println(elem);
        }
    }
    public static void main(String[] args) {
        launch();
    }

}


