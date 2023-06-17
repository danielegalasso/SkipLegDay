package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RemoveUserService extends Service<Boolean> {
    private String username;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().removeUser(username);
                return res;
            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }
}
