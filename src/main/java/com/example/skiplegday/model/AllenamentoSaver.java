package com.example.skiplegday.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class AllenamentoSaver {
    //public void aggiungiAllenamento(String username, String scheda, String data, HashMap<String, ArrayList<Serie>> seriePerEsercizio)
    private static AllenamentoSaver instance = new AllenamentoSaver();
    private AllenamentoSaver(){}
    public static AllenamentoSaver getInstance() {return instance;}
    private HashMap<String, ArrayList<Serie>> allenamento = new HashMap<>();
    public void resetdati(){
        //serie.clear();
    }
    public void addSerie(String nomeEsercizio, ArrayList<Serie> serie) {
        if (allenamento.containsKey(nomeEsercizio)) {
            allenamento.remove(nomeEsercizio);
        }
        allenamento.put(nomeEsercizio, serie);
    }
    public void loadAllenameto(String nomeAllenamento) throws SQLException {
        System.out.println(allenamento);
        /*
        AggiungiAllenamentoService aggiungiAllenamentoService = new AggiungiAllenamentoService();
        aggiungiAllenamentoService.setDati(UtenteAttuale.getInstance().getUsername(), nomeAllenamento, getData(true), allenamento);
        aggiungiAllenamentoService.restart();
        aggiungiAllenamentoService.setOnSucceeded(event -> {
            System.out.println("Aggiunto allenamento");
        });
        Database.getInstance().aggiungiAllenamento(UtenteAttuale.getInstance().getUsername(), nomeAllenamento, getData(true), allenamento);
        //salva esterno, carico queste list nel database*/
    }
    private String getData(boolean formatoCorto){
        if (formatoCorto){
            LocalDate currentDate= LocalDate.now();
            return currentDate.toString();
        }
        else{
            LocalDateTime currentDate = LocalDateTime.now();
            return currentDate.toString();
        }
    }
    public void stampa() {
        System.out.println(allenamento);
    }
}
