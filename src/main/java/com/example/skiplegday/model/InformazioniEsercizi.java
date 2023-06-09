package com.example.skiplegday.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class InformazioniEsercizi {
    private InformazioniEsercizi(){}
    private static final InformazioniEsercizi instance = new InformazioniEsercizi();
    public static InformazioniEsercizi getInstance() {return instance;}
    private HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
    private ArrayList<String> ListaTuttiEsercizi = new ArrayList<>();
    private ArrayList<String> nomiGruppiMuscolari = new ArrayList<>();
    private HashMap<String, ArrayList<String>> gruppoMuscolareEsercizi = new HashMap<>();
    private HashMap<String, String> descrizioni = new HashMap<>(); //esercizi-descrizione
    public void caricaEsercizi() throws SQLException {
        PrendiHashService infServ = new PrendiHashService();
        infServ.restart();
        infServ.setOnSucceeded(event -> {
            ArrayList<Object> risultato = infServ.getValue();
            gruppoMuscolareEsercizi = (HashMap<String, ArrayList<String>>) risultato.get(0);
            ListaTuttiEsercizi = (ArrayList<String>) risultato.get(1);
            nomiGruppiMuscolari = (ArrayList<String>) risultato.get(2);
            descrizioni = (HashMap<String, String>) risultato.get(3);
            hashMap = gruppoMuscolareEsercizi;
        });/*
        ArrayList<Object> risultato = Database.getInstance().prendiHashListaeserciziNomigruppiDescrizioni();
        gruppoMuscolareEsercizi = (HashMap<String, ArrayList<String>>) risultato.get(0);
        ListaTuttiEsercizi = (ArrayList<String>) risultato.get(1);
        nomiGruppiMuscolari = (ArrayList<String>) risultato.get(2);
        descrizioni = (HashMap<String, String>) risultato.get(3);*/
    }
    public ArrayList<String> getListaTuttiEsercizi() {
        return ListaTuttiEsercizi;
    }
    public HashMap<String, String> getDescrizioni() {
        return descrizioni;
    }
    public ArrayList<String> getNomiGruppiMuscolari() {return nomiGruppiMuscolari;}
    public HashMap<String, ArrayList<String>> getGruppoMuscolareEsercizi() {return gruppoMuscolareEsercizi;}
}
