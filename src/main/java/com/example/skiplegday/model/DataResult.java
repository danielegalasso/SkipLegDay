package com.example.skiplegday.model;


import java.util.Collections;
import java.util.List;

public record DataResult(List<Data> allElements) {
    public DataResult {
        Collections.sort(allElements);
    }
}
