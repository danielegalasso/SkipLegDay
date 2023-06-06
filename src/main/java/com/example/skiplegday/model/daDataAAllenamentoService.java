package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class daDataAAllenamentoService extends Service<ArrayList<Object>> {
    private String username;
    private String dataAllenamento;
    @Override
    protected Task<ArrayList<Object>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> risultato = Database.getInstance().daDataAAllenamento(username,dataAllenamento);
                return risultato;
            }
        };
    }
    public void setDati(String username, String dataAllenamento) {
        this.username = username;
        this.dataAllenamento = dataAllenamento;
    }
}
