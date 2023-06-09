package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ModRecensioneService extends Service<Boolean> {
    private String username;
    private Double valutazione;
    private String recensione;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().modRecensione(username, valutazione, recensione);
                return res;
            }
        };
    }
    public void setDati(String username, Double valutazione, String recensione) {
        this.username = username;
        this.valutazione = valutazione;
        this.recensione = recensione;
    }
}
