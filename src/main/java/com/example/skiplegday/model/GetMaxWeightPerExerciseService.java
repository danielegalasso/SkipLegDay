package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetMaxWeightPerExerciseService extends Service<Double> {
    private String username;
    private String esercizio;
    @Override
    protected Task<Double> createTask() {
        return new Task<Double>() {
            @Override
            protected Double call() throws Exception {
                double risultato = Database.getInstance().getMaxWeightPerExercise(username,esercizio);
                return risultato;
            }
        };
    }
    public void setDati(String username, String eserizio) {
        this.username = username;
        this.esercizio = eserizio;
    }
}
