package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class RegisterUserService extends Service<Boolean> {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String sesso;
    private String peso;
    private String dataNascita;
    private String altezza;

    private String css;
    private String valutazione;
    private String recensione;
    @Override    //DA CHIAMARE A FINE REGISTRAZIONE, MERGE CO DE FRA
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().registerUser(username,password,nome,cognome,sesso,peso,dataNascita,altezza,css, valutazione, recensione);
                return res;
            }
        };
    }
    public void setDati(String username, String password, String nome, String cognome, String sesso, String peso, String dataNascita, String altezza, String css, String valutazione, String recensione) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.peso = peso;
        this.dataNascita = dataNascita;
        this.altezza = altezza;
        this.css = css;
        this.valutazione = valutazione;
        this.recensione = recensione;
    }
}
