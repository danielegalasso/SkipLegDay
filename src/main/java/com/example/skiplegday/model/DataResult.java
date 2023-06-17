package com.example.skiplegday.model;


import java.util.Collections;
import java.util.List;

public record DataResult(List<Data> allElements) {   //Data è un record  di data e punteggio con l'override del compareTo
    public DataResult {
        Collections.sort(allElements);
    }
}
