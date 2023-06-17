package com.example.skiplegday.view;

import com.example.skiplegday.Utils;
import com.example.skiplegday.model.GetEserciziSchedaService;
import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.model.UtenteAttuale;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;

public class CreateSchedaAllenamentoHandler {
    private CreateSchedaAllenamentoHandler() {}
    private final static CreateSchedaAllenamentoHandler instance = new CreateSchedaAllenamentoHandler();
    public static CreateSchedaAllenamentoHandler getInstance() {
        return instance;
    }
    private Text scegliNomeText;
    private TextField fieldNameAllenamento;
    private ScrollPane scrollPaneEsercizi;
    private VBox vBoxTuoAllenamento;
    private Button saveAllenamentoButton;
    public void caricaEserciziVbox() throws IOException {  //prima passavo come parametro il nome della scheda, ora lo prendo dal textfield
        GetEserciziSchedaService service = new GetEserciziSchedaService();
        String nomeScheda=fieldNameAllenamento.getPromptText();
        System.out.println(nomeScheda);
        if (nomeScheda==null){
            return;
        }
        service.setDati(UtenteAttuale.getInstance().getUsername(),nomeScheda);
        service.restart();
        service.setOnSucceeded(event -> {
            ArrayList<String> esercizi = service.getValue();
            vBoxTuoAllenamento.getChildren().clear();
            for (String s: esercizi) {
                try {
                    addvBoxIfUnique(s);
                } catch (IOException e) {
                    ErrorMessage.getInstance().showErrorMessage("Errore caricamento esercizi");
                }
            }
        });
    }
    public void caricaManualeEsercizi() throws IOException {
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi();
        scrollPaneEsercizi.setContent(retVbox(strings));
    }
    private VBox retVbox(ArrayList<String> esercizi) throws IOException {
        VBox vb = new VBox();
        for (String s: esercizi) {
            Node node1 = Utils.loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setDescrizioneEsercizio(Utils.loadImage(s),new DescrizioneEsercizio(s,true));
            EsercizioGeneraleHandler.getInstance().setOnMouseClickedEvent(event -> {
                try {
                    addvBoxIfUnique(s);
                } catch (IOException e) {
                    ErrorMessage.getInstance().showErrorMessage("Errore caricamento esercizi");
                }
            },"Aggiungi");
            vb.getChildren().add(node1);
        }
        return vb;
    }
    public void filter(String gruppoMuscolare) throws IOException {
        ArrayList<String> es=InformazioniEsercizi.getInstance().getEserciziGruppoMuscolare(gruppoMuscolare);
        scrollPaneEsercizi.setContent(retVbox(es));
    }

    private void addvBoxIfUnique(String text) throws IOException {
        //System.out.println("addvBoxIfUnique");
        boolean isUnique = true;
        for (Node n: vBoxTuoAllenamento.getChildren()) {
            if (n instanceof Parent) {  //nodoEsercizio:  image (0)  textFlow(1)   button(2
                Parent parent = (Parent) n;
                /*
                String textField = ((TextFlow) parent.getChildrenUnmodifiable().get(1)).getChildren().get(0).toString();
//textField: [Text[text="panca piana manubri", x=0.0, y=0.0, alignment=LEFT, origin=BASELINE, boundsType=LOGICAL, font=Font[name=System Regular, family=System, style=Re*/
                if (parent.getChildrenUnmodifiable().get(1) instanceof TextFlow textFlow){
                    Text textNode = (Text) textFlow.getChildren().get(0);
                    String textField = textNode.getText();
                    //System.out.println("textField: "+textField);
                    if (textField.equals(text)) {
                        isUnique = false;
                        break;
                    }
                }
                else {
                    ErrorMessage.getInstance().showErrorMessage("Errore caricamento esercizi");
                }
            }
        }
        //System.out.println("isUnique: "+isUnique);
        if (isUnique) {  //SE NON è PRESENTE CREO L'HOB CON ESERCIZIO E BUTTON RIMUOVI
            Node node2 = Utils.loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setDescrizioneEsercizio(Utils.loadImage(text),new DescrizioneEsercizio(text,true));
            EsercizioGeneraleHandler.getInstance().setOnMouseClickedEvent(event -> {
                vBoxTuoAllenamento.getChildren().remove(node2);
            },"Rimuovi");
            vBoxTuoAllenamento.getChildren().add(node2);
        }
    }
    public ArrayList<String> getEserciziAggiuntiScheda(){
        ObservableList<Node> children = vBoxTuoAllenamento.getChildren();
        ArrayList<String> esercizi = new ArrayList<>();
        for (Node n: children) {
            if (n instanceof Parent) {  //nodoEsercizio:  image (0)  textFlow(1)   button(2
                Parent parent = (Parent) n;
                if (parent.getChildrenUnmodifiable().get(1) instanceof TextFlow textFlow){
                    Text textNode = (Text) textFlow.getChildren().get(0);  //sia DescrTExt che sercizio ereditano da Text
                    String textField = textNode.getText();
                    esercizi.add(textField);
                }
                else {
                    ErrorMessage.getInstance().showErrorMessage("Errore caricamento esercizi");
                }
            }
        }
        return esercizi;
    }
    public void setScrollPaneEserciziAdd(ScrollPane scrollPaneEsercizi) {
        this.scrollPaneEsercizi=scrollPaneEsercizi;
    }
    public void setVBoxTuoAllenamento(VBox vBoxTuoAllenamento) {
        this.vBoxTuoAllenamento = vBoxTuoAllenamento;
    }
    public void setFieldNameAllenamento(TextField fieldCreateNameAllenamento) {this.fieldNameAllenamento=fieldCreateNameAllenamento;}
    public void caricaNomeAllenamento(String nomeScheda){fieldNameAllenamento.setPromptText(nomeScheda);}
    public void setScegliNomeText(Text scegliNomeText) {this.scegliNomeText=scegliNomeText;}
    public void caricaScegliNomeText(String message){scegliNomeText.setText(message);}
    public void setSaveAllenamentoButton(Button saveAllenamentoButton) {this.saveAllenamentoButton=saveAllenamentoButton;}
    public void setNomeButtonModifica(){saveAllenamentoButton.setText("Salva Modifica");}
}
