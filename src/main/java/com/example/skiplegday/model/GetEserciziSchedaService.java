package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
public class GetEserciziSchedaService extends Service<ArrayList<String>> {
    private String username;
    private String nomeScheda;
    @Override
    protected Task<ArrayList<String>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<String> risultato = Database.getInstance().getEserciziScheda(username,nomeScheda);
                return risultato;
            }
        };
    }
    public void setDati(String username, String nomeScheda) {
        this.username = username;
        this.nomeScheda = nomeScheda;
    }
}
