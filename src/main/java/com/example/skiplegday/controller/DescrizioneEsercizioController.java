package com.example.skiplegday.controller;

import com.example.skiplegday.model.InformazioniEsercizi;
import com.example.skiplegday.view.ManualeEserciziHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DescrizioneEsercizioController {
    @FXML
    Text nomeEsercizioSpiegazione;
    @FXML
    ImageView immagineEsercizioSpiegazione;
    @FXML
    ScrollPane scrollPaneEsercizioSpiegazione;
    @FXML
    AnchorPane paneCloseDesc;
    @FXML
    ImageView iconCloseDesc;
    @FXML
    AnchorPane paneExit;
    @FXML
    ImageView iconExit;
    public void indietroAction(ActionEvent actionEvent) {
    }
    public void loadDati(String nomeEsercizio) throws IOException {
        nomeEsercizioSpiegazione.setText(nomeEsercizio);
        scrollPaneEsercizioSpiegazione.setContent(new Text(InformazioniEsercizi.getInstance().getDescrizioni().get(nomeEsercizio)));
        immagineEsercizioSpiegazione.setImage(loadImage(nomeEsercizio));
    }
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    private Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(getClass().getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }
    public void closeDescAction(MouseEvent mouseEvent) {
        ManualeEserciziHandler.getInstance().removeDescrizione();
    }
    public void buttonInvisibile() {
        paneCloseDesc.setVisible(false);
        paneExit.setVisible(true);
        //iconCloseDesc.setVisible(false);
    }

    public void exitAction(MouseEvent mouseEvent) {
        if (paneExit.getScene().getWindow() instanceof Stage stage)
            stage.close();
    }
}
