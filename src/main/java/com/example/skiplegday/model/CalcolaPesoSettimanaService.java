package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CalcolaPesoSettimanaService extends Service<Double> {
    private String username;
    private Boolean settimanaPrecedente;
    @Override
    protected Task<Double> createTask() {
        return new Task<>() {
            @Override
            protected Double call() throws Exception {
                Double risultato = Database.getInstance().calcolaPesoSettimanaCorrente(username,settimanaPrecedente);
                return risultato;
            }
        };
    }
    public void setDati(String username, Boolean settimanaPrecedente) {
        this.username = username;
        this.settimanaPrecedente = settimanaPrecedente;
    }
}
