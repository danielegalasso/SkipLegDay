package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class GetAllDataFromUserService extends Service<DataUser> {
    private String username;

    @Override
    protected Task<DataUser> createTask() {
        return new Task<>() {
            @Override
            protected DataUser call() throws Exception {
                DataUser d= Database.getInstance().getAllDataFromUser(username);
                System.out.println(d);
                return d;
                //Boolean res=Database.getInstance().loginIn(username, password);
                //return res;
            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }
}
