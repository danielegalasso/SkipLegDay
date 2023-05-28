package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class RemoveSchedaService extends Service<Boolean> {
    private String username;
    private String nomeScheda;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().removeScheda(username,nomeScheda);
                return res;
            }
        };
    }
    public void setDati(String username, String nomeScheda){
        this.username=username;
        this.nomeScheda=nomeScheda;
    }
}
