package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetAllDataFromUser extends Service<DataUser> {
    private String username;

    @Override
    protected Task<DataUser> createTask() {
        return new Task<>() {
            @Override
            protected DataUser call() throws Exception {
                return Database.getInstance().getAllDataFromUser(username);

                //Boolean res=Database.getInstance().loginIn(username, password);
                //return res;
            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }
}
