package com.example.skiplegday.model;

import com.example.skiplegday.view.ErrorMessage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
        if (!checkAllenamentoValido()) {
            ErrorMessage.getInstance().showErrorMessage("Nessun esercizio inserito");
            return;
        }
        System.out.println(allenamento);
        EliminaAllenamentoService eliminaAllenamentoService = new EliminaAllenamentoService();
        eliminaAllenamentoService.setDati(UtenteAttuale.getInstance().getUsername(), getData(true));
        eliminaAllenamentoService.restart();
        eliminaAllenamentoService.setOnSucceeded(event -> {
            AggiungiAllenamentoService aggiungiAllenamentoService = new AggiungiAllenamentoService();
            aggiungiAllenamentoService.setDati(UtenteAttuale.getInstance().getUsername(), nomeAllenamento, getData(true), allenamento);
            aggiungiAllenamentoService.restart();
            aggiungiAllenamentoService.setOnSucceeded(event1 -> {
                if (!aggiungiAllenamentoService.getValue()){
                    ErrorMessage.getInstance().showErrorMessage("Allenamento non aggiunto");
                }
            });
        });
    }
    private boolean checkAllenamentoValido(){
        boolean nessunEsercizio = true;
        Set<String> keys = allenamento.keySet();
        for (String key : keys) {
            if (allenamento.get(key).size() != 0) {
                nessunEsercizio = false;
                break;
            }
        }
        return !nessunEsercizio;
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
