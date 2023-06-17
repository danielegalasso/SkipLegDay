package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetCssService extends Service<String> {
    private String username;
    @Override
    protected Task<String> createTask() {
        return new Task<>() {
            @Override
            protected String call() throws Exception {
                String risultato = Database.getInstance().getCss(username);
                return risultato;
            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }

}
