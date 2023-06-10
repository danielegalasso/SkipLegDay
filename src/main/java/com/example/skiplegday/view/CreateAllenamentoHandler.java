package com.example.skiplegday.view;

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

public class CreateAllenamentoHandler {
    private CreateAllenamentoHandler() {}
    private final static CreateAllenamentoHandler instance = new CreateAllenamentoHandler();
    public static CreateAllenamentoHandler getInstance() {
        return instance;
    }
    private Text scegliNomeText;
    private TextField fieldNameAllenamento;
    private ScrollPane scrollPaneEsercizi;
    private VBox vBoxTuoAllenamento;
    private Button saveAllenamentoButton;
    public void caricaEserciziVbox(String schedaNome) throws IOException {  //se riesco a implementarlo non mi serve piu passare il nome della scheda come parametro
        GetEserciziSchedaService service = new GetEserciziSchedaService();
        //service.setDati(UtenteAttuale.getInstance().getUsername(),schedaNome);
        String nomeScheda=fieldNameAllenamento.getPromptText();
        System.out.println(nomeScheda);
        if (nomeScheda==null){
            return;
        }
        service.setDati(UtenteAttuale.getInstance().getUsername(),nomeScheda);
        service.restart();
        service.setOnSucceeded(event -> {
            ArrayList<String> esercizi = service.getValue();
            vBoxTuoAllenamento.getChildren().clear(); //rimuovo ???
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
            Node node1 = loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setDescrizioneEsercizio(loadImage(s),new DescrizioneEsercizio(s,true));
            //rimuovibile: false perche nel manuale esercizi non si puo rimuovere, deve essere un qualcosa di statico,
            //quindi nascondo il button
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
        System.out.println("addvBoxIfUnique");
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
                    System.out.println("textField: "+textField);
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
        System.out.println("isUnique: "+isUnique);
        if (isUnique) {  //SE NON è PRESENTE CREO L'HOB CON ESERCIZIO E BUTTON RIMUOVI
            Node node2 = loadRootFromFXML("esercizio.fxml");
            EsercizioGeneraleHandler.getInstance().setDescrizioneEsercizio(loadImage(text),new DescrizioneEsercizio(text,true));
            //rimuovibile: true perche do la possibilita di rimuovere la descrizione esercizio, per modificare la scheda

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
    //TUTTI QUESTI METODI SONO RIPETUTI IN TUTTE LE CLASSI. FARE UNA CLASSE SETTINGS AD ASEMPIO PER RICHIAMARLI DA LI
    //SENZA RISCRIVERLI OGNI VOLTA
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    private Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(getClass().getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }
}
