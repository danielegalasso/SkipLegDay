package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RemoveUserCompletelyService extends Service<Boolean> {
    private String username;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                return Database.getInstance().removeUserCompletely(username);

            }
        };
    }
    public void setDati(String username) {
        this.username = username;
    }
}
