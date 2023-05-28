package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class OttieniSeriePerEsercizioService extends Service<ArrayList<Object>> {
    String username;
    String dataAllenamento;
    @Override
    protected Task<ArrayList<Object>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<Object> call() throws Exception {
                ArrayList<Object> res=Database.getInstance().ottieniSeriePerEsercizio(username, dataAllenamento);
                return res;
            }
        };
    }
    public void setDati(String username, String dataAllenamento){
        this.username=username;
        this.dataAllenamento=dataAllenamento;
    }
}
