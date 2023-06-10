package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetMaxRepsPerExerciseService extends Service<Double> {
    private String username;
    private String esercizio;
    @Override
    protected Task<Double> createTask() {
        return new Task<Double>() {
            @Override
            protected Double call() throws Exception {
                double risultato = Database.getInstance().getMaxRepsPerExercise(username,esercizio);
                return risultato;
            }
        };
    }
    public void setDati(String username, String eserizio) {
        this.username = username;
        this.esercizio = eserizio;
    }
}
