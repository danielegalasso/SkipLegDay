package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class EliminaAllenamentoService extends Service<Boolean> {
    String username;
    String data;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().eliminaAllenamento(username,data);
                return res;
            }
        };
    }
    public void setDati(String username, String data) {
        this.username = username;
        this.data = data;
    }
}
