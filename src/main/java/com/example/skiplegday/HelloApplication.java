package com.example.skiplegday;
import com.example.skiplegday.model.Database;
import com.example.skiplegday.model.LettoreFile;
import com.example.skiplegday.view.PopupHandler;
import com.example.skiplegday.view.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {

        Database db = Database.getInstance();
        db.createConnection();
        SceneHandler.getInstance().init(stage);
        SceneHandler.getInstance().createLoginScene();


        //db.registerUser("domenico", "1234", "Dome", "Visci", "dsduiws@gmail.com", "03-02-2009","1.80");

        //db.closeConnection();
    }
    public static void main(String[] args) {
        launch();
    }
}