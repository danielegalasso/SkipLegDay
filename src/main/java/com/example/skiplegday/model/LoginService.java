package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class LoginService extends Service<Boolean> {
    private String username;
    private String password;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                return true;
                //Boolean res=Database.getInstance().loginIn(username, password);
                //return res;
            }
        };
    }
    public void setDati(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
