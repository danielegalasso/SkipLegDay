package com.example.skiplegday.model;

import java.util.ArrayList;
import java.util.List;

public class GestoreDatabase {
    private GestoreDatabase(){}
    private static final GestoreDatabase instance =new GestoreDatabase();
    public static GestoreDatabase getInstance(){ return instance;}
    private List<String> esercizi= new ArrayList<>();
    private List<String> rep=new ArrayList<>();
    private List<String> kg=new ArrayList<>();
    public void loadSerie(){

    }
}