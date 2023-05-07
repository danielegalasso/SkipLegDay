package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class LoginController {

    public void RegistraAction(MouseEvent mouseEvent) {
        //model.createUser();  daniele galasso
        SceneHandler.getInstance().createRegistraScene();
    }

    public void loginAction(ActionEvent actionEvent) {
        //model.controllaDati();  daniele galasso
        SceneHandler.getInstance().createHomeScene();
    }
}
