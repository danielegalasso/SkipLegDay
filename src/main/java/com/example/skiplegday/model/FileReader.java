package com.example.skiplegday.model;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private FileReader(){}
    private static final FileReader instance = new FileReader();
    public static FileReader getInstance() {return instance;}
    private TextFlow textFlow = new TextFlow();
    public TextFlow leggiFile(String path) {
        textFlow.getChildren().clear();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                for (String word : words) {
                    if (checkGrassetto(word)) {
                        toGrassetto(word);
                    }
                    else if (checkColore(word)) {
                        toColore(word);
                    }
                    else {
                        textFlow.getChildren().add(new Text(word + " "));
                    }
                }
                textFlow.getChildren().add(new Text("\n"));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textFlow;
    }
    private boolean checkGrassetto(String word) {
        return word.startsWith("*") && word.endsWith("*");
    }
    private boolean checkColore(String word) {
        return word.startsWith("$") && word.endsWith("$");
    }
    private void toGrassetto(String word){
        Text boldText = new Text(word.substring(2, word.length() - 2));
        boldText.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        textFlow.getChildren().add(boldText);
        textFlow.getChildren().add(new Text(" "));
    }
    private void toColore(String word){
        Text boldText = new Text(word.substring(1, word.length() - 1));
        boldText.setFont(Font.font("Arial", FontWeight.BLACK, 12));
        boldText.setFill(Color.RED);
        textFlow.getChildren().add(boldText);
        textFlow.getChildren().add(new Text(" "));
    }
}
