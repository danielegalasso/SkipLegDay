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
}
