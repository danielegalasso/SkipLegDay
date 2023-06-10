package com.example.skiplegday.model;

import com.example.skiplegday.controller.ObiettiviController;
import javafx.application.Platform;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class ProgressiObiettivi {
    private final static ProgressiObiettivi instance = new ProgressiObiettivi();
    private ProgressiObiettivi() {}
    public static ProgressiObiettivi getInstance() {
        return instance;
    }
    private ObiettiviController controller;
    public void loadObiettivi() {
        getTrainingDaysMuscleGroup();
        //per sempre
        getTrainingDayUser();
        //per sempre
        getMaxWeightExercise("croci");
        getMaxWeightExercise("panca piana");
        getMaxRepsExercise("croci");
        getMaxRepsExercise("panca piana");
        getAllenamentiCreatiAttuali();
    }
    private void getAllenamentiCreatiAttuali(){
        GetSchedeService g = new GetSchedeService();
        g.setDati(UtenteAttuale.getInstance().getUsername());
        g.restart();
        g.setOnSucceeded(event->{
            int numAll=g.getValue().size();
            controller.setText("allenamentiCreatiAttuali", String.valueOf(numAll));
        });
    }
    private void getTrainingDaysMuscleGroup(){
        GetTrainingDaysByMuscleGroup g = new GetTrainingDaysByMuscleGroup();
        g.setDati(UtenteAttuale.getInstance().getUsername(), true);
        g.restart();
        g.setOnSucceeded(event->{
            //System.out.println("allenamenti per gruppo muscolare: "+g.getValue());
            HashMap<String, Integer> map = g.getValue();
            Set<String> keys = map.keySet();
            for(String key: keys){
                controller.setText("allenamenti"+key, map.get(key).toString());
            }
        });
    }
    private void getTrainingDayUser(){
        GetTrainingDaysForUser g = new GetTrainingDaysForUser();
        g.setDati(UtenteAttuale.getInstance().getUsername(), true);
        g.restart();
        g.setOnSucceeded(event->{
            //System.out.println("allenamenti totali: "+g.getValue());
            controller.setText("allenamentiMese", g.getValue().toString());
        });
    }
    private void getMaxWeightExercise(String text){
        GetMaxWeightPerExercise g = new GetMaxWeightPerExercise();
        g.setDati(UtenteAttuale.getInstance().getUsername(), text);
        g.restart();
        g.setOnSucceeded(event->{
            controller.setText("maxKg"+text, g.getValue().toString());
        });
    }
    private void getMaxRepsExercise(String text){
        GetMaxRepsPerExercise g = new GetMaxRepsPerExercise();
        g.setDati(UtenteAttuale.getInstance().getUsername(), text);
        g.restart();
        g.setOnSucceeded(event->{
            int rep = g.getValue().intValue();
            controller.setText("maxRep"+text, rep+"");
        });
    }
    public void setController(ObiettiviController controller) {
        this.controller = controller;
    }
}
