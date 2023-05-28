package com.example.skiplegday.view;

import com.example.skiplegday.model.GetEserciziSchedaService;
import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.model.UtenteAttuale;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    private TextField fieldNameAllenamento;
    private ScrollPane scrollPaneEsercizi;
    private VBox vBoxTuoAllenamento;
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
                } catch (IOException ignoredEvent) {}
            }
        });
    }
    public void caricaManualeEsercizi() throws IOException {
        ArrayList<String> strings = InformazioniEsercizi.getInstance().getListaTuttiEsercizi(); // RIMETTERE DECOMMENTANDOLO!!!!!!!!!!!
        System.out.println(strings);
        //ArrayList<String> strings = new ArrayList<>(); //mi serve come prova, poi lo elimino e tengo lo string di sopra
        //strings.add("panca piana manubri");strings.add("croci cavi");strings.add("squat");
        VBox vb = new VBox();
        for (String s: strings) {
            Node node1 = (Node) loadRootFromFXML("esercizio.fxml");
            EsercizioHandler.getInstance().setDescrizioneEsercizio(loadImage(s),new DescrizioneEsercizio(s));
            EsercizioHandler.getInstance().setOnMouseClickedEvent(event -> {
                try {
                    addvBoxIfUnique(s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            },"Aggiungi");
            vb.getChildren().add(node1);
        }
        scrollPaneEsercizi.setContent(vb); //rimettere t
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
                TextFlow textFlow = (TextFlow) parent.getChildrenUnmodifiable().get(1);
                Text textNode = (Text) textFlow.getChildren().get(0);
                String textField = textNode.getText();
                System.out.println("textField: "+textField);
                if (textField.equals(text)) {
                    isUnique = false;
                    break;
                }
            }
        }
        System.out.println("isUnique: "+isUnique);
        if (isUnique) {  //SE NON è PRESENTE CREO L'HOB CON ESERCIZIO E BUTTON RIMUOVI
            Node node2 = (Node) loadRootFromFXML("esercizio.fxml");
            EsercizioHandler.getInstance().setDescrizioneEsercizio(loadImage(text),new DescrizioneEsercizio(text));
            EsercizioHandler.getInstance().setOnMouseClickedEvent(event -> {
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
                TextFlow textFlow = (TextFlow) parent.getChildrenUnmodifiable().get(1);
                Text textNode = (Text) textFlow.getChildren().get(0); //sia DescrTExt che sercizio ereditano da Text
                String textField = textNode.getText();
                esercizi.add(textField);
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
