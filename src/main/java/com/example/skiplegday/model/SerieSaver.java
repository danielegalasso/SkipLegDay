package com.example.skiplegday.model;

import java.util.ArrayList;
import java.util.List;

public class SerieSaver {
    private static SerieSaver instance = new SerieSaver();
    private SerieSaver(){}
    public static SerieSaver getInstance() {return instance;}
    private List<Serie> serie= new ArrayList<>();
    /*
    private List<String> nomeEsList = new ArrayList<>();
    private List<Integer> kgList = new ArrayList<>();
    private List<Integer> repList= new ArrayList<>();
    private List<Integer> recList=new ArrayList<>(); //recupero espresso in secondi

    public void addNomeEs(String nomeEs){nomeEsList.add(nomeEs);}
    public void addKg(Integer kg){kgList.add(kg);}
    public void addRep(Integer rep){repList.add(rep);}
    public void addRec(Integer rec){recList.add(rec);}*/
    public void printAll(){
        System.out.println("serie: "+serie);
        /*
        System.out.println("nome esercizio: "+nomeEsList);
        System.out.println("kg: "+kgList);
        System.out.println("rep: "+repList);
        System.out.println("rec: "+recList);*/
    }
    public void resetdati(){
        serie.clear();
        /*
        nomeEsList.clear();
        kgList.clear();
        repList.clear();
        recList.clear();*/

    }
    public void addSerie(Serie serie) {
        this.serie.add(serie);
    }
    public void loadSerie(){
        //salva esterno, carico queste list nel database
    }
}
