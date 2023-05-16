package com.example.skiplegday.model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InformazioniEsercizi {
    private InformazioniEsercizi(){}
    private static final InformazioniEsercizi instance = new InformazioniEsercizi();
    public static InformazioniEsercizi getInstance() {return instance;}
    private HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
    private ArrayList<String> ListaTuttiEsercizi = new ArrayList<>();
    private ArrayList<String> nomiGruppiMuscolari = new ArrayList<>();
    private HashMap<String, String> descrizioni = new HashMap<>(); //esercizi-descrizione
    public void caricaEsercizi() {
        ArrayList<Object> ret=LettoreFile.getInstance().prendiHashListaeserciziNomigruppiDescrizioni();
        hashMap=(HashMap<String, ArrayList<String>>) ret.get(0);
        ListaTuttiEsercizi=(ArrayList<String>) ret.get(1);
        nomiGruppiMuscolari=(ArrayList<String>) ret.get(2);
        descrizioni=(HashMap<String, String>) ret.get(3);
    }
    public HashMap<String, ArrayList<String>> getHashMap() {
        return hashMap;
    }
    public ArrayList<String> getListaTuttiEsercizi() {
        return ListaTuttiEsercizi;
    }
    public ArrayList<String> getNomiGruppiMuscolari() {
        return nomiGruppiMuscolari;
    }
    public HashMap<String, String> getDescrizioni() {
        return descrizioni;
    }
    public String getImageEsercizio(String name) {
        String path = getClass().getResource("/com/example/skiplegday/icon/petto/" + name + ".png").toExternalForm();
        return path;
    }
}
