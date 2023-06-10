package com.example.skiplegday.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class AllenamentoHandler {
    private static AllenamentoHandler instance=new AllenamentoHandler();
    private AllenamentoHandler(){}
    public static AllenamentoHandler getInstance(){ return instance; }
    private Text idGruppoMuscolare;   //lo uso sia per gruppo Muscolare nel caso degli allenamenti default
    //sia per nome allenamento per quelli personali, es: allenamento saitama
    private VBox vBoxListaEsercizi;
    private Button importaButton;
    private Button saveAllenamentoButton;
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public void setAllenamentoPers(List<String> l) throws IOException {
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size();++i){
            Node node = loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setEsercizio(loadImage(l.get(i)),new Esercizio(l.get(i)),false);
            vBoxListaEsercizi.getChildren().add(node);
        }
        importaButton.setVisible(false);
        saveAllenamentoButton.setVisible(true);
        //imageGruppoMuscolare.setImage(new Image(getClass().getResource(l.get(l.size()-1)).openStream()));  NON C'è PIU L'IMMAGINE
        //se queste robe le metto in un path, non devo scrivere tutto il bordello dei file .txt
    }
    public void setAllenamentoPredef(List<String> l) throws IOException {
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size();++i){
            Node node = loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setEsercizioSchedeDefault(loadImage(l.get(i)),new DescrizioneEsercizio(l.get(i),true));
            //popup: true perche va caricato su un popUp in caso venga premuto
            vBoxListaEsercizi.getChildren().add(node);
        }
        importaButton.setVisible(true);  // in schedaAllenamentoPredefinito  non devo inserire l'allenamento e salvarlo
        saveAllenamentoButton.setVisible(false);  //posso solo importare la scheda nei miei allenamenti
    }
    private Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(getClass().getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }
    public void setAllenamento(Text idGruppoMuscolare, VBox vBoxListaEsercizi, Button importaButton, Button saveAllenamentoButton){
        this.idGruppoMuscolare=idGruppoMuscolare;
        this.vBoxListaEsercizi=vBoxListaEsercizi;
        this.importaButton=importaButton;
        this.saveAllenamentoButton=saveAllenamentoButton;
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
}
