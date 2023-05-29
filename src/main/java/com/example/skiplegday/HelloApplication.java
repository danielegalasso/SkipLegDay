package com.example.skiplegday;
import com.example.skiplegday.model.*;
import com.example.skiplegday.view.PopupHandler;
import com.example.skiplegday.view.SceneHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.BorderPane;
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
        /*db.registerUser("domenico", "1234", "Dome", "Visci", "dsduiws@gmail.com", "03-02-2009","1.80");
        RegisterUserService registerUserService = new RegisterUserService();
        registerUserService.setDati("domenico", "1234", "Dome", "Visci", "M","80" ,"03-02-2009","180");
        registerUserService.restart();
        registerUserService.setOnSucceeded(event -> {
            System.out.println("successo");
        });*/
        /*
        AddSchedaService addSchedaService = new AddSchedaService();
        ArrayList<String> esercizi = new ArrayList<>();
        esercizi.add("panca piana bilanciere");
        esercizi.add("alzate frontali");
        esercizi.add("squat");
        addSchedaService.setDati("domenico", "scheda majin bu", esercizi);
        addSchedaService.restart();
        addSchedaService.setOnSucceeded(event -> {
            System.out.println("successo");
        });*/
        /*
        RemoveSchedaService removeSchedaService = new RemoveSchedaService();
        removeSchedaService.setDati("domenico", "");
        removeSchedaService.restart();
        removeSchedaService.setOnSucceeded(event -> {
            if (removeSchedaService.getValue()){
                System.out.println("successo");
            }
            else{
                System.out.println("fallimento");
            }
        });*/
        /*
        EliminaAllenamentoService eliminaAllenamentoService = new EliminaAllenamentoService();
        eliminaAllenamentoService.setDati("domenic2o", "2020-05-15");
        eliminaAllenamentoService.restart();
        eliminaAllenamentoService.setOnSucceeded(event -> {
            if (eliminaAllenamentoService.getValue()){
                System.out.println("successo");
            }
            else{
                System.out.println("fallimento");
            }
        });*/
        //db.closeConnection();
    }
}