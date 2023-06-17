package com.example.skiplegday.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record CalendarResult(ArrayList<String> allElements) {
	public CalendarResult {
		Collections.sort(allElements);
	}

}

