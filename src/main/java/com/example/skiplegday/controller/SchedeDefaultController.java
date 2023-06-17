package com.example.skiplegday.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class SchedeDefaultController {
    @FXML
    GridPane gridPaneDefault;
    public void addSchedaDefault(Node node,int i,int j) {
        gridPaneDefault.add(node,j,i);  //l'add del gridPane è column,row
    }
}
