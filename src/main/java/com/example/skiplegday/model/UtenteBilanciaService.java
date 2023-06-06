package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class UtenteBilanciaService extends Service<ArrayList<Object>> {
    private String username;
    @Override
    protected Task<ArrayList<Object>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> res=Database.getInstance().daUtenteDateBilancia(username);
                return res;
            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }
}
