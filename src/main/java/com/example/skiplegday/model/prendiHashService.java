package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class prendiHashService extends Service<ArrayList<Object>> {
    @Override
    protected Task<ArrayList<Object>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> risultato = Database.getInstance().prendiHashListaeserciziNomigruppiDescrizioni();
                return risultato;
            }
        };
    }
}
