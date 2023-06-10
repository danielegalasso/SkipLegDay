package com.example.skiplegday.view;

import com.example.skiplegday.model.Data;
import com.example.skiplegday.model.DataResult;
import com.example.skiplegday.model.PunteggiUtenteEsercizioService;
import com.example.skiplegday.model.UtenteAttuale;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class PopupHandler {
    private static final PopupHandler instance = new PopupHandler();
    private PopupHandler() {}
    public static PopupHandler getInstance() {return instance;}
    private VBox vBoxDatiEsercizi; //vbox che contiene le serie kr rep rec (fxml)
    public Text nomeEsercizio;
    private Text errorText;
    private LineChart<Number, Number> lineChart;
    private NumberAxis AX;
    private NumberAxis AY;
    private String username = UtenteAttuale.getInstance().getUsername();
    private final PunteggiUtenteEsercizioService service = new PunteggiUtenteEsercizioService();
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public void addDatiEsercizio() throws IOException {
        vBoxDatiEsercizi.getChildren().add(loadRootFromFXML("kgRepRec.fxml"));
    }
    public void loadGraph() {
        //System.out.println(username + this.nomeEsercizio.getText());
        service.setDati(username, nomeEsercizio.getText());
        service.restart();
        service.setOnSucceeded(event ->  {
            AX.setLabel("Giorno");
            AY.setLabel("Punteggio");

            String formattedStartDate;
            String formattedEndDate;   //per avere un range di data di 3 mesi
            formattedEndDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            formattedStartDate = LocalDate.now().minusMonths(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if(event.getSource().getValue() instanceof DataResult result) {
                //creo serie dati per X e Y entrambi numerici
                XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>(); // definisco i dati come data e punteggio (nota: la data non è una stringa
                // poiché poi faccio la conversione, faccio questo perché voglio poter avere
                // la distanza che decido io nell'asse x, e lo faccio mediante le coordinate
                LocalDate firstDate = LocalDate.parse(formattedStartDate);  //converto la data in formato stringa in formato LocalDate
                LocalDate lastDate = LocalDate.parse(formattedEndDate);

                double intervallo_giorni = ChronoUnit.DAYS.between(firstDate, lastDate);
                ObservableList<XYChart.Data<Number, Number>> data = FXCollections.observableArrayList(); //è una lista di date e punteggi
                double min = Double.MAX_VALUE; // mi trovo il minimo di quel range perché anche se la prima data in cui ho un dato dista vari giorni voglio che inizia dopo un giorno l'asse x
                for (Data d : result.allElements()) {
                    if (d.date().compareTo(formattedStartDate) >= 0 && d.date().compareTo(formattedEndDate) <= 0){
                        LocalDate for_date = LocalDate.parse(d.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        long days = ChronoUnit.DAYS.between(firstDate, for_date);
                        if (days < min) {
                            min = days;
                        }
                    }
                }
                int slide_giorni = (int) (intervallo_giorni/25);  //per determinare la distanza tra un punto e l'altro nell'asse x
                for (Data d : result.allElements()) {
                    if (d.date().compareTo(formattedStartDate) >= 0 && d.date().compareTo(formattedEndDate) <= 0) { //se la data del dato è compresa tra la data di inizio e la data di fine
                        LocalDate for_date = LocalDate.parse(d.date(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        long days = ChronoUnit.DAYS.between(firstDate, for_date);  //per avere la distanza in giorni tra la data del dato e la data di inizio
                        //System.out.println(days);
                        //creo un oggetto XYChart.Data<Number, Number> utilizzando i giorni trascorsi e il punteggio associato al dato
                        XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(days-min +slide_giorni, d.punteggio());
                        data.add(dataPoint);
                        // Personalizzazione dell'etichetta del punto con la data
                        String dateLabel = for_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        dataPoint.setNode(new HoveredThresholdNode(dateLabel));
                    }
                }
                dataSeries.setData(data);
                lineChart.getData().clear();
                lineChart.getData().add(dataSeries);
                lineChart.setLegendVisible(false);  //visibilità della legenda del grafico
                AX.setAnimated(false);
                AX.setTickLabelFormatter(new StringConverter<Number>() {
                    @Override
                    public String toString(Number object) { //per avere la data in formato stringa
                        long days = object.longValue();
                        LocalDate date = firstDate.plusDays((long) (days+2 - slide_giorni));
                        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                    @Override
                    public Number fromString(String string) { //per avere la data in formato numerico
                        LocalDate date = LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        long days = ChronoUnit.DAYS.between(firstDate, date);
                        return days;
                    }
                });
            }
        });
        service.setOnFailed(event -> {ErrorMessage.getInstance().showErrorMessage("Errore nel caricamento dei dati");});
    }
    public void setNomeEsercizio(String nomeEsercizio) {
        this.nomeEsercizio.setText(nomeEsercizio);
        //una volta che setto il nome esercizio posso caricare il grafico
        loadGraph();
    }
    public void setvBoxDatiEsercizi(VBox vBoxDatiEsercizi) { //mi carico il vBox dal controller col metodo initialize, in modo che posso gestirmi tutto da questa classe
        this.vBoxDatiEsercizi = vBoxDatiEsercizi;
    }
    public void setText(Text nomeEsercizio) { //per ottenere riferimento al nome dell'esercizio del controller
        this.nomeEsercizio = nomeEsercizio;
    }
    public void setGraph(LineChart<Number, Number> lineChart, NumberAxis AX, NumberAxis AY) {
        this.lineChart = lineChart;
        this.AX = AX;
        this.AY = AY;
    }
    public void showErrorText(String text){
        errorText.setText(text);
        errorText.setVisible(true);
    }
    public boolean chekValueNull() {
        ObservableList<Node> items = vBoxDatiEsercizi.getChildren();
        for (Node item : items) {
            if (item instanceof Parent parent) {
                if (parent.getChildrenUnmodifiable().get(0) instanceof TextField textField1 &&
                        parent.getChildrenUnmodifiable().get(1) instanceof TextField textField2 &&
                        parent.getChildrenUnmodifiable().get(2) instanceof TextField textField3) {
                    if (textField1.getText().equals("") || textField2.getText().equals("") || textField3.getText().equals("")) {
                        return true;
                    }
                }
                else {ErrorMessage.getInstance().showErrorMessage("");}
            }
        }
        return false;
    }
    public void setErrorText(Text errorText) {
        this.errorText = errorText;
    }
    public void hideErrorText() {
        errorText.setVisible(false);
    }
    private boolean saved=false;
    public void setSaved(boolean saved) {this.saved = saved;}
    public boolean chekNotSave() {
        return !saved;
    }

}
