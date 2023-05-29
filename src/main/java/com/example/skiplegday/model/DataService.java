package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class DataService extends Service<ArrayList<Data>> {

	private final String data = "https://raw.githubusercontent.com/italia/covid19-opendata-vaccini/master/dati/somministrazioni-vaccini-summary-latest.csv";
	
	@Override
	protected Task<ArrayList<Data>> createTask() {
		return new Task<>() {
			@Override
			protected ArrayList<Data> call() throws Exception {
                ArrayList<Data> data = new ArrayList<>();
                Data d1 = new Data("2023-05-21", 100);
                Data d2 = new Data("2022-05-20", 110);
                Data d3 = new Data("2023-05-24", 120);
                Data d4 = new Data("2022-05-22", 130);
                Data d5 = new Data("2023-05-31", 140);
                data.add(d1);data.add(d2);data.add(d3);data.add(d4);data.add(d5);


				return data;
			}
		};
	}

}


