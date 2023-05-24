package com.example.skiplegday.model;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LettoreFile {
    private LettoreFile(){}
    private static final LettoreFile instance = new LettoreFile();
    public static LettoreFile getInstance() {return instance;}
    private TextFlow textFlow = new TextFlow();
    public TextFlow leggiFileCaratteri(String path) {
        int numRiga=0;
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
                    else if(checkLink(word)){
                        toLink(word);
                    }
                    else if (checkColore(word)) {
                        toColore(word);
                    }
                    else {
                        textFlow.getChildren().add(new Text(word + " "));
                    }
                }
                textFlow.getChildren().add(new Text("\n"));
                numRiga++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return textFlow;
    }

    private void toLink(String word) {
        Hyperlink link = new Hyperlink(word.substring(1, word.length() - 1));
        link.setOnAction(event -> {
            double y = getYPositionOfLineContainingText(textFlow, "La creatività");
        });
        textFlow.getChildren().add(link);
    }
    private double getYPositionOfLineContainingText(TextFlow textFlow, String searchText) {
        double y = -1;
        for (int i = 0; i < textFlow.getChildren().size(); i++) {
            Node node = (Node) textFlow.getChildren().get(i);
            if (node instanceof Text) {
                Text t = (Text) node;
                if (t.getText().contains(searchText)) {
                    Bounds bounds = t.getBoundsInParent();
                    y = (bounds.getMaxY() + bounds.getMinY()) / 2.0;
                    break;
                }
            }
        }
        return y;
    }
    private boolean checkLink(String word) {
        return word.startsWith("?") && word.endsWith("?");
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
    public List<List<String>> leggiSchedaDefault(String path){
        List<List<String>> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(";");
                List<String> row = new ArrayList<>();
                for (String word : words) {
                    row.add(word.trim());
                }
                result.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String[] leggiFile(String percorso) {
        //ritorna in elemento 0 = nomeesercizio
        //           elemento 1 = GruppoMuscolare
        //           elemento 2 = tesoHTML
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(percorso), StandardCharsets.UTF_8))) {
            String primaRiga = br.readLine();
            StringBuilder testoBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                testoBuilder.append(line);
                testoBuilder.append(System.lineSeparator());
            }
            String[] elementi = primaRiga.split(";");
            String testoCompleto = testoBuilder.toString();
            String[] res = new String[] {elementi[0], elementi[1], testoCompleto};
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
