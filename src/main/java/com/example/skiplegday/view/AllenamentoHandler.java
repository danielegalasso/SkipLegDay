package com.example.skiplegday.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class AllenamentoHandler {
    private static AllenamentoHandler instance=new AllenamentoHandler();
    private AllenamentoHandler(){}
    public static AllenamentoHandler getInstance(){ return instance; }
    private Text idGruppoMuscolare;
    private VBox vBoxListaEsercizi;
    //private Button importaButton;
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public void setAllenamentoPers(List<String> l) throws IOException {
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size()-1;++i){
            vBoxListaEsercizi.getChildren().add(new Esercizio(l.get(i)));
        }
        //imageGruppoMuscolare.setImage(new Image(getClass().getResource(l.get(l.size()-1)).openStream()));  NON C'è PIU L'IMMAGINE
        //se queste robe le metto in un path, non devo scrivere tutto il bordello dei file .txt
    }
    public void setAllenamentoPredef(List<String> l) throws IOException {
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size()-1;++i){
            HBox hBox = new HBox();
            ImageView img= new ImageView(new Image(getClass().getResource(FONT_PATH+l.get(l.size()-1)).openStream()));
            img.setFitHeight(30);
            img.setFitWidth(30);
            hBox.getChildren().addAll(img,new Text(l.get(i)));
            vBoxListaEsercizi.getChildren().add(hBox);
        }
    }
    public void setAllenamento(Text idGruppoMuscolare, VBox vBoxListaEsercizi){
        this.idGruppoMuscolare=idGruppoMuscolare;
        this.vBoxListaEsercizi=vBoxListaEsercizi;
    }

}