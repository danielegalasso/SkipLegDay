package com.example.skiplegday.model;

public class UtenteAttuale {
    private static UtenteAttuale instance = null;
    private UtenteAttuale() {
    }
    public static UtenteAttuale getInstance() {
        if (instance == null) {
            instance = new UtenteAttuale();
        }
        return instance;
    }
    private String username;   // in caso poi accorpo tutti questi in una classe Utente
    private String nome;
    private String cognome;
    private String email;
    private String altezza;
    private String dataNascita;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setUtenteAttuale(String username, String nome, String cognome, String email, String altezza, String dataNascita) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.altezza = altezza;
        this.dataNascita = dataNascita;
    }
}
