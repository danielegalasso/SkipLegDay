package com.example.skiplegday.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class InformazioniEsercizi {
    private InformazioniEsercizi(){}
    private static final InformazioniEsercizi instance = new InformazioniEsercizi();
    public static InformazioniEsercizi getInstance() {return instance;}
    private ArrayList<String> ListaTuttiEsercizi = new ArrayList<>();
    private ArrayList<String> nomiGruppiMuscolari = new ArrayList<>();
    private HashMap<String, ArrayList<String>> gruppoMuscolareEsercizi = new HashMap<>();
    private HashMap<String, String> descrizioni = new HashMap<>(); //esercizi-descrizione
    public void caricaEsercizi() throws SQLException {
        PrendiHashService infServ = new PrendiHashService();
        infServ.restart();
        infServ.setOnSucceeded(event -> {
            ArrayList<Object> risultato = infServ.getValue();
            if (risultato.get(0) instanceof HashMap<?,?> && risultato.get(1) instanceof ArrayList<?> && risultato.get(2) instanceof ArrayList<?> && risultato.get(3) instanceof HashMap<?,?>){
                gruppoMuscolareEsercizi = (HashMap<String, ArrayList<String>>) risultato.get(0);
                ListaTuttiEsercizi = (ArrayList<String>) risultato.get(1);
                nomiGruppiMuscolari = (ArrayList<String>) risultato.get(2);
                descrizioni = (HashMap<String, String>) risultato.get(3);
            }
        });
    }
    public ArrayList<String> getListaTuttiEsercizi() {
        System.out.println("lista tutti esercizi: "+ListaTuttiEsercizi);
        return ListaTuttiEsercizi;
    }
    public HashMap<String, String> getDescrizioni() {
        return descrizioni;
    }
    public ArrayList<String> getNomiGruppiMuscolari() {return nomiGruppiMuscolari;}  //alla fine queste due funzioni non mi sono servite
    public HashMap<String, ArrayList<String>> getGruppoMuscolareEsercizi() {return gruppoMuscolareEsercizi;}
    public ArrayList<String> getEserciziGruppoMuscolare(String gruppoMuscolare) {  //se magari voglio prendermene solo uno
        return gruppoMuscolareEsercizi.get(gruppoMuscolare);
    }
}
