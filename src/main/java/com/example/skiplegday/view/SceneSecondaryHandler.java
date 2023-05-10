package com.example.skiplegday.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SceneSecondaryHandler {
    private AnchorPane sceneRoot;
    private static final SceneSecondaryHandler instance = new SceneSecondaryHandler();
    private SceneSecondaryHandler() {}
    public static SceneSecondaryHandler getInstance() {return instance;}
    private<T> T loadRootFromFXML(String resourceName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SceneHandler.class.getResource("/com/example/skiplegday/"+resourceName));
        return fxmlLoader.load();
    }
    public void createSchedePredefiniteScene() throws IOException {
        Node node = (Node) loadRootFromFXML("schedeDefault.fxml");
        addAndCenter(node);
    }
    public void createPrincipianteScene() throws IOException{
        Node node = (Node) loadRootFromFXML("principianteScene.fxml");
        addAndCenter(node);
    }
    public void setHomeSceneRoot(AnchorPane sceneRoot) {
        this.sceneRoot = sceneRoot;
    }
    /*
    public Node createEserciziScene(){
        WebView webView = new WebView();
        String htmlContent=ClasseModel.ritorna "";
        webView.getEngine().loadContent(htmlContent);
        return webView;
    }  */
    private void addAndCenter(Node node){
        sceneRoot.getChildren().setAll(node);
        AnchorPane.setTopAnchor(node,10.0);
        AnchorPane.setBottomAnchor(node, 10.0);
        AnchorPane.setLeftAnchor(node, 10.0);
        AnchorPane.setRightAnchor(node, 10.0);
    }
}
