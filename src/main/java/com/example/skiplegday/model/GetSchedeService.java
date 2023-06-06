package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;
public class GetSchedeService extends Service<ArrayList<String>> {
    private String username;
    @Override
    protected Task<ArrayList<String>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                ArrayList<String> risultato = Database.getInstance().getSchede(username);
                return risultato;
            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }

}
