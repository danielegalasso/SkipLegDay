package com.example.skiplegday.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneSecondaryHandler {
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private Stage stage;
    private Scene scene;
    public void init(Stage stage) {
        if(this.stage==null){
            this.stage = stage;
            this.stage.setTitle("SkipLegDay");
            //createLoginScene();  //funzione che imposta la scene dello SceneHandler
            this.stage.setScene(scene);
            this.stage.show();
        }
    }
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public Node createSchedePredefiniteScene() throws IOException {
        return loadRootFromFXML("schedeDefault.fxml");
    }
    public Node createPrincipianteScene() throws IOException{
        return loadRootFromFXML("principianteScene.fxml");
    }

    /*
    public Node createEserciziScene(){
        WebView webView = new WebView();
        String htmlContent=ClasseModel.ritorna "";
        webView.getEngine().loadContent(htmlContent);
        return webView;
    }  */
}
