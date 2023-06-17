package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetTrainingDaysForUserService extends Service<Integer> {
    private String username;
    private boolean veroSegueMeseFalsoSegueTotale;
    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return Database.getInstance().getTrainingDaysForUser(username,veroSegueMeseFalsoSegueTotale);

            }
        };
    }
    public void setDati(String username, boolean veroSegueMeseFalsoSegueTotale) {
        this.username = username;
        this.veroSegueMeseFalsoSegueTotale = veroSegueMeseFalsoSegueTotale;
    }
}
