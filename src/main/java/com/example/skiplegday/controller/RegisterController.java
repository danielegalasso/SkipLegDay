package com.example.skiplegday.controller;

import com.example.skiplegday.view.SceneHandler;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class RegisterController {

    public void creaAccountAction(ActionEvent actionEvent) {
        SceneHandler.getInstance().createHomeScene();
    }

    public void tornaALoginAction(MouseEvent mouseEvent) {
        SceneHandler.getInstance().createLoginScene();
    }
}
