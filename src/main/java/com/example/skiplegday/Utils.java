package com.example.skiplegday;

import com.example.skiplegday.model.Database;
import com.example.skiplegday.view.ConfirmationAlert;
import com.example.skiplegday.view.ErrorMessage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {   //metodi che c'erano in tutte le classi ho deciso di definirli qui per non fare copie di codice
    //non la faccio neanche singleton ma non tutti i metodi statici
    private static final String FONT_PATH = "/com/example/skiplegday/icon/";
    public static Image loadImage(String nomeEsercizio) throws IOException {
        Image img=new Image(Utils.class.getResource(FONT_PATH+nomeEsercizio+".png").openStream());
        return img;
    }
    public static <T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Utils.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public static void chiudiApp(Node node) {
        if (node.getScene().getWindow() instanceof Stage stage){  //basta prendere un qualsiasi elemento della finestra
            int res = ConfirmationAlert.showConfirmationAlert("Sei sicuro di voler chiudere l'app?","indietro","Esci");
            if (res !=0) return;
            try{
                Database.getInstance().closeConnection();
            } catch (Exception e) {
                ErrorMessage.getInstance().showErrorMessage("Errore durante la chiusura del database");
            }
            stage.close();
        }
    }
}
