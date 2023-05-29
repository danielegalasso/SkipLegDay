package com.example.skiplegday.model;

public record Data(String date, int punteggio) implements Comparable<Data>{
	@Override
	public int compareTo(Data o) {
		return date.compareTo(o.date);
	}
}


