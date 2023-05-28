package com.example.skiplegday.controller;

import com.example.skiplegday.model.*;
import com.example.skiplegday.view.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class AllenamentoController {
    @FXML
    Text idGruppoMuscolare; //ridenominato nomeAllenamento !!!!!!!!!!!!
    @FXML
    VBox vBoxListaEsercizi;
    @FXML
    Button importaButton;  //se lo apro da default
    @FXML
    ImageView imageGruppoMuscolare;
    @FXML
    Button saveAllenamentoButton;
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public Esercizio converterTextEsercizio(String nomeEsercizio){
        return new Esercizio(nomeEsercizio);
    }
    public void importaSchedaAction(ActionEvent actionEvent) throws IOException {
        //da fare con daniele gay   salvare tutta questa scheda default nel database
        CheckSchedaInDbService checkSchedaInDbService = new CheckSchedaInDbService();
        checkSchedaInDbService.setDati(UtenteAttuale.getInstance().getUsername(), idGruppoMuscolare.getText());
        checkSchedaInDbService.restart();
        checkSchedaInDbService.setOnSucceeded(event ->{
            if (checkSchedaInDbService.getValue()) {
                AddSchedaService addSchedaService = new AddSchedaService();
                //QUANDO INVECE MODIFICO DEVO CAPIRE DA DOVE PRENDERE IL NOME DELLA SCHEDA ALLENAMENTO VVVVV
                addSchedaService.setDati(UtenteAttuale.getInstance().getUsername(),idGruppoMuscolare.getText(), CreateAllenamentoHandler.getInstance().getEserciziAggiuntiScheda());
                addSchedaService.restart();
                addSchedaService.setOnSucceeded(event1 ->{
                    if (addSchedaService.getValue()) {
                        try {
                            SceneSecondaryHandler.getInstance().aggiungiSchedaPersonaleScene(idGruppoMuscolare.getText());
                            SceneSecondaryHandler.getInstance().createSchedePersonaliScene();
                        } catch (IOException ignoredEvent) {}
                    } else {
                        System.out.println("Scheda non aggiunta");
                    }
                });
            }
            else{
                ErrorMessage.getInstance().showErrorMessage("Scheda già presente");
            }
        });
        //gli esercizi dell'allenamento li salvo nel database, cliccando sulla label dell'allenamento, lo cerco nel database
        //e mi prendo tutti gli esercizi che ci sono dentro
    }
    public void initialize(){
        AllenamentoHandler.getInstance().setAllenamento(idGruppoMuscolare,vBoxListaEsercizi,importaButton,saveAllenamentoButton);
    }
    public void saveAllenamentoAction(ActionEvent actionEvent) throws SQLException {
        AllenamentoSaver.getInstance().loadAllenameto(idGruppoMuscolare.getText()); //in base al nome che gli metto come promp text
    }
}
