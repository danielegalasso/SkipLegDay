package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.HashMap;
public class CalcolaPesoGruppiMuscolariService extends Service<HashMap<String, Double>> {
    @Override
    protected Task<HashMap<String, Double>> createTask() {
        return new Task<>() {
            @Override
            protected HashMap<String, Double> call() throws Exception {
                HashMap<String, Double> risultato = Database.getInstance().calcolaPesoGruppiMuscolariSettimanaCorrente();
                return risultato;
            }
        };
    }
}
