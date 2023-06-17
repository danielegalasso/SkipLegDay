package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class AddSchedaService extends Service<Boolean> {
    private String username;
    private String nomeScheda;
    private ArrayList<String> esercizi;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().addScheda(username,nomeScheda,esercizi);
                return res;
            }
        };
    }
    public void setDati(String username, String nomeScheda, ArrayList<String> esercizi){
        this.username=username;
        this.nomeScheda=nomeScheda;
        this.esercizi=esercizi;
    }
}
