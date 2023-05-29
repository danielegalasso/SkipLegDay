package com.example.skiplegday;

import javafx.scene.image.Image;

import java.io.IOException;

public class Setting {
    private static Setting instance = new Setting();
    private Setting(){}
    public static Setting getInstance() {return instance;}
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(getClass().getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }
}
