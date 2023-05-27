package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class PunteggiUtenteEsercizioService extends Service<ArrayList<ArrayList<String>>> {
    String username;
    String nomeEsercizio;
    @Override
    protected Task<ArrayList<ArrayList<String>>> createTask() {
        return new Task<>() {
            @Override
            protected ArrayList<ArrayList<String>> call() throws Exception {
                ArrayList<ArrayList<String>> res=Database.getInstance().calcolaPunteggiUtenteXEsercizio(username, nomeEsercizio);
                return res;
            }
        };
    }
    public void setDati(String username, String nomeEsercizio) {
        this.username = username;
        this.nomeEsercizio = nomeEsercizio;
    }
}
