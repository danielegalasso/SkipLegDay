package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class PunteggiUtenteEsercizioService extends Service<DataResult> {
    String username;
    String nomeEsercizio;
    @Override
    protected Task<DataResult> createTask() {
        return new Task<DataResult>() {
            @Override
            protected DataResult call() throws Exception {
                ArrayList<Data> r=Database.getInstance().calcolaPunteggiUtenteXEsercizio(username, nomeEsercizio);
                DataResult res = new DataResult(r);
                return res;
            }

        };

    }
    public void setDati(String username, String nomeEsercizio) {
        this.username = username;
        this.nomeEsercizio = nomeEsercizio;
    }

    public void stopService() {
        if (getState() == State.RUNNING) {
            cancel();
        }
    }
}
