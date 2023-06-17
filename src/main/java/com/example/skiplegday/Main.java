package com.example.skiplegday;
import com.example.skiplegday.model.*;
import com.example.skiplegday.view.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        Database db = Database.getInstance();
        db.createConnection();
        SceneHandler.getInstance().init(stage);
        SceneHandler.getInstance().createLoginScene();
    }
    /*IMPORTANTE:
    PER PROVARE L'APP UTILIZZARE QUESTI DATI DI ACCESSO:
    domenico domenico    (username e password)
    daniele daniele      (username e password)
    federico federico    (username e password)
    sono tre utenti con gia schede di allenamento create, recensioni e voti. Db popolato con circa 60 mila dati.
     */
}
/*
Schemi comportamentali del progetto:
Esplorazione sicura: completa libertà nella navigazione dell’app, per i button “più pericolosi” viene aperto un popup
                     per confermare la scelta, in modo da rendere la navigazione piu sicura.
Gratifica immediata: non appena l’utente salva l’allenamento, può subito vedere i suoi risultati e i suoi progressi
                     attraverso i grafici di tutte le schermate.
Minima soddisfazione: guida per gli utenti generale, più diverse piccole guide nelle varie interfacce. Uso
                      dell’interfaccia per comunicare subito i risultati attraverso i grafici e i vari contatori.
Cambi di idee: possibilità di cambiare e modificare schede allenamento, nomi delle schede, esercizi, serie per esercizi,
               in caso di errori o semplicemente cambi di idee.
Abitudini: combinazione tasti implementata come quella universale: es ctrl s salvare. Pulsanti simili posizionati sempre
           allo stesso modo nell’interfaccia, es : button indietro.
Micropause: riduzione minima dei tempi di caricamento, diverse ottimizzazioni per far caricare i dati e le query da db.
            Db di circa 60.000 tuple.
Costruzione incrementale: si può passare al semplice import delle schede di default, alla creazione di schede personalizzate.
Solo tastiera: diversi shortcut in varie schermate come la creazione dell’allenamento cntrl+n, il salvataggio
               dell’allenamento cntr+s,  esc per chiudere ecc.
Approvazione sociale: possibilità di lasciare una recensione ed un commento in una schermata dedicata.
*/