package com.example.skiplegday.model;

public record Serie(String esercizio, Integer ripetizioni, Integer kg, Integer rec) {
    @Override    //questo poi lo tolgo mi serve solo per debug
    public String toString() {
        return "Serie{" +
                "esercizio='" + esercizio + '\'' +
                ", ripetizioni=" + ripetizioni +
                ", kg=" + kg +
                ", rec=" + rec +
                '}';
    }
}
