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

        System.out.println("ciao");
        Database db = Database.getInstance();
        db.createConnection();
        /*
        try {
            db.registerUser("daniele", "daniele", "Daniele", "Rossi", "M", "70", "1990-01-01", "180", "blue", "5", "Ottimo utente");
            db.registerUser("federico", "federico", "Federico", "Bianchi", "M", "80", "1995-05-10", "175", "green", "4", "Utente affidabile");
            db.registerUser("domenico", "domenico", "Domenico", "Verdi", "M", "65", "1988-11-15", "170", "red", "3", "Buon utente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
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
        });
        AggiungiAllenamentoService aggiungiAllenamentoService = new AggiungiAllenamentoService();
        HashMap<String, ArrayList<Serie>> seriePerEsercizio = new HashMap<>();
        ArrayList<Serie> serie = new ArrayList<>();
        serie.add(new Serie(50, 10,30));
        serie.add(new Serie(80, 4,30));
        serie.add(new Serie(100, 2,90));
        seriePerEsercizio.put("croci", serie);
        seriePerEsercizio.put("panca piana bilanciere", serie);
        seriePerEsercizio.put("alzate frontali", serie);
        seriePerEsercizio.put("squat", serie);
        serie.add(new Serie(100, 2,90));
        seriePerEsercizio.put("stacchi da terra", serie);
        seriePerEsercizio.put("curl manubri", serie);
        seriePerEsercizio.put("curl bilanciere", serie);
        aggiungiAllenamentoService.setDati("domenico", "scheda majin bu", "2023-06-05", seriePerEsercizio);
        aggiungiAllenamentoService.restart();
        aggiungiAllenamentoService.setOnSucceeded(event -> {
            if (aggiungiAllenamentoService.getValue()){
                System.out.println("successo");
            }
            else{
                System.out.println("fallimento");
            }
        });
        DaDataAAllenamentoService daDataAAllenamentoService = new DaDataAAllenamentoService();
        daDataAAllenamentoService.setDati("domenico", "2023-06-04");
        daDataAAllenamentoService.restart();
        daDataAAllenamentoService.setOnSucceeded(event -> {
            System.out.println(daDataAAllenamentoService.getValue());
        });
        PunteggiUtenteEsercizioService punteggiUtenteEsercizioService = new PunteggiUtenteEsercizioService();
        punteggiUtenteEsercizioService.setDati("domenico", "panca piana bilanciere");*/
        //db.closeConnection();
    }
}