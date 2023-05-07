package com.example.skiplegday.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class UsersReader {
    private final HashMap<String,String> users;
    private static UsersReader instance = null;
    private static final String path = "files" + File.separator + "users.txt";
    private UsersReader() {
        users = new HashMap<>();
        try {
            List<String> allUsers = Files.readAllLines(Path.of(path));
            allUsers.forEach(str -> {
                String[] res = str.split(";");
                users.put(res[0], res[1]);
            });
        } catch(Exception ignored) {
        }
    }
    public static UsersReader getInstance() {
        if(instance == null)
            instance = new UsersReader();
        return instance;
    }
    public boolean checkAccess(String user, String password) {
        String pass = users.get(user);
        if(pass == null)
            return false;
        return pass.equals(password);
    }
    public boolean userEsistente(String user){
        return users.containsKey(user);
    }
    public void createUser(String text, String passw) {
        users.put(text,passw); //devo aggiornarla
        try {
            updateFileUsers(text,passw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateFileUsers(String text, String passw) throws IOException {
        FileWriter writer= new FileWriter(path,true);
        BufferedWriter bw = new BufferedWriter(writer); //per scrivere su file
        bw.newLine();
        bw.write(text+";"+passw);
        bw.close();
    }
}
