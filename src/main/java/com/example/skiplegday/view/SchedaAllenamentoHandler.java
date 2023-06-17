package com.example.skiplegday.view;

import com.example.skiplegday.Utils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class SchedaAllenamentoHandler {
    private static SchedaAllenamentoHandler instance=new SchedaAllenamentoHandler();
    private SchedaAllenamentoHandler(){}
    public static SchedaAllenamentoHandler getInstance(){ return instance; }
    private Text idGruppoMuscolare;   //lo uso sia per gruppo Muscolare nel caso degli allenamenti default
    //sia per nome allenamento per quelli personali, es: allenamento saitama
    private VBox vBoxListaEsercizi;
    private Button importaButton; //per importare la schedaAllenamento
    private Button saveAllenamentoButton; //vogli salvare l'allenamento della mia schedaAllenamento
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public void setAllenamentoPers(List<String> l) throws IOException { //in allenamento personale carico gli esercizi, in quanto mi devo allenare
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size();++i){
            Node node = Utils.loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setEsercizio(Utils.loadImage(l.get(i)),new Esercizio(l.get(i)));
            vBoxListaEsercizi.getChildren().add(node);
        }
        importaButton.setVisible(false);
        saveAllenamentoButton.setVisible(true);
    }
    public void setAllenamentoPredef(List<String> l) throws IOException {
        idGruppoMuscolare.setText(l.get(0));
        for(int i=1;i<l.size();++i){
            Node node = Utils.loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setEsercizioSchedeDefault(Utils.loadImage(l.get(i)),new DescrizioneEsercizio(l.get(i),true));
            //popup: true perche va caricato su un popUp in caso venga premuto
            vBoxListaEsercizi.getChildren().add(node);
        }
        importaButton.setVisible(true);  // in schedaAllenamentoPredefinito  non devo inserire l'allenamento e salvarlo
        saveAllenamentoButton.setVisible(false);  //posso solo importare la scheda nei miei allenamenti
    }
    public void setAllenamento(Text idGruppoMuscolare, VBox vBoxListaEsercizi, Button importaButton, Button saveAllenamentoButton){
        this.idGruppoMuscolare=idGruppoMuscolare;
        this.vBoxListaEsercizi=vBoxListaEsercizi;
        this.importaButton=importaButton;
        this.saveAllenamentoButton=saveAllenamentoButton;
    }
}
