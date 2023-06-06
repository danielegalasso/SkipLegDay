package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CheckSchedaInDbService extends Service<Boolean> {
    private String utente;
    private String scheda;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().checkSchedaInDb(utente,scheda);
                return res;
            }
        };
    }
    public void setDati(String utente,String scheda){
        this.utente=utente;
        this.scheda=scheda;
    }
}
