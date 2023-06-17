package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ModInfoUserService extends Service<Boolean> {
    private String username;
    private String nome;
    private String cognome;
    private String sesso;
    private String peso;
    private String dataNascita;
    private String altezza;


    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().modInfoUser(username,nome,cognome,sesso,peso,dataNascita,altezza);
                return res;
            }
        };
    }
    public void setDati(String username,String nome, String cognome, String sesso, String peso, String dataNascita, String altezza) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.peso = peso;
        this.dataNascita = dataNascita;
        this.altezza = altezza;
    }
}
