package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class AggiungiAllenamentoService extends Service<Boolean> {
    String username;
    String scheda;
    String data;
    HashMap<String, ArrayList<Serie>> seriePerEsercizio;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().aggiungiAllenamento(username, scheda, data, seriePerEsercizio);
                return res;
            }
        };
    }
    public void setDati(String username, String scheda, String data, HashMap<String, ArrayList<Serie>> seriePerEsercizio) {
        this.username = username;
        this.scheda = scheda;
        this.data = data;
        this.seriePerEsercizio = seriePerEsercizio;
    }
}
