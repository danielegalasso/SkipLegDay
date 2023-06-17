package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.HashMap;

public class GetTrainingDaysByMuscleGroupService extends Service<HashMap<String, Integer>> {
    private String username;
    private boolean veroSegueMeseFalsoSegueTotale;
    @Override
    protected Task<HashMap<String, Integer>> createTask() {
        return new Task<HashMap<String, Integer>>() {
            @Override
            protected HashMap<String, Integer> call() throws Exception {
                return Database.getInstance().getTrainingDaysByMuscleGroup(username,veroSegueMeseFalsoSegueTotale);
            }
        };
    }
    public void setDati(String username, boolean veroSegueMeseFalsoSegueTotale) {
        this.username = username;
        this.veroSegueMeseFalsoSegueTotale = veroSegueMeseFalsoSegueTotale;
    }
}
