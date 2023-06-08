package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.ArrayList;

public class GetDatesOfTrainings extends Service<CalendarResult> {
    private String username = "";
    @Override
    protected Task<CalendarResult> createTask() {
        return new Task<CalendarResult>() {
            @Override
            protected CalendarResult call() throws Exception {
                ArrayList<String> risultato = Database.getInstance().getDatesOfTrainings(username);
                CalendarResult cr = new CalendarResult(risultato);
                return cr;
            }
        };
    }
    public void setDati(String username_) {
        username = username_;
    }
}
