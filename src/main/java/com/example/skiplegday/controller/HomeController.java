package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;

public class HomeController {
    public void logoutAction(ActionEvent actionEvent) {
        //model.logout();  daniele galasso   devo salvare qualcosa nell'account utente??
        SceneHandler.getInstance().createLoginScene();
    }

    public void profiloAction(ActionEvent actionEvent) {
        //galasso
    }

    public void eserciziAction(ActionEvent actionEvent) {
        //galasso
    }

    public void statisticheAction(ActionEvent actionEvent) {
    }

    public void schedePredefiniteAction(ActionEvent actionEvent) {
    }

    public void schedaPersonaleAction(ActionEvent actionEvent) {
    }
}
