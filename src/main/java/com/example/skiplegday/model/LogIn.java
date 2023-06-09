package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class LogIn extends Service<Boolean> {
    private String username;
    private String password;

    @Override    //DA CHIAMARE A FINE REGISTRAZIONE, MERGE CO DE FRA
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                return Database.getInstance().loginIn(username,password);

            }
        };
    }
    public void setDati(String username, String password) {
        this.username = username;
        this.password = password;

    }
}
