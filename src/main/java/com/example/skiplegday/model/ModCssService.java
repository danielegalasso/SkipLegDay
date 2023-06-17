package com.example.skiplegday.model;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ModCssService extends Service<Boolean> {
    private String username;
    private String css;
    @Override
    protected Task<Boolean> createTask() {
        return new Task<>() {
            @Override
            protected Boolean call() throws Exception {
                Boolean res=Database.getInstance().modCss(username, css);
                return res;
            }
        };
    }
    public void setDati(String username, String css) {
        this.username = username;
        this.css = css;
    }
}
