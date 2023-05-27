package com.example.skiplegday.model;

import org.springframework.security.crypto.bcrypt.BCrypt;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Database{
    private static Database instance = new Database();
    private Database(){}
    public static Database getInstance() {return instance;}
    private prendiHashService prendiHashService = new prendiHashService();

    private Connection con = null;

    public void createConnection() throws SQLException {
        String url = "jdbc:sqlite:db_name.db";
        this.con = DriverManager.getConnection(url);
        if (this.con != null && !this.con.isClosed()) {
            System.out.println("Connected!");
        }

    }

    public void closeConnection() throws SQLException {
        if (this.con != null) {
            this.con.close();
        }

        this.con = null;
    }

    public PreparedStatement prepareQuery(String query, ArrayList<String> params) throws SQLException {
        //per fare una query semplice : PreparedStatement statement = prepareQuery("select username from utenti", new String[0]);
        PreparedStatement stmt = this.con.prepareStatement(query);
        for (int i = 0; i < params.size(); i++) {
            stmt.setString(i + 1, params.get(i));
        }
        return stmt;
    }

    public ArrayList<Object> fromPrepStatementToArrayList(PreparedStatement stmt, int numeroColonneSelezionare) throws SQLException {
        //se devo selezionare solo una colonna ottengo ArrayList<String> altrimenti ArrayList<ArrayList<String>>
        ArrayList<Object> risultato = new ArrayList<>();
        if (this.con != null && !this.con.isClosed()) {
            ResultSet rs = stmt.executeQuery();


            if (numeroColonneSelezionare == 1) {
                ArrayList<String> array = new ArrayList<>();
                while (rs.next()) {
                    array.add(rs.getString(1));
                }
                risultato.addAll(array);
            } else {
                ArrayList<ArrayList<String>> risultati = new ArrayList<>();
                while (rs.next()) {
                    ArrayList<String> riga = new ArrayList<>();
                    for (int i = 1; i <= numeroColonneSelezionare; i++) {
                        riga.add(rs.getString(i));
                    }
                    risultati.add(riga);
                }
                risultato.addAll(risultati);
            }

        }
        stmt.close();

        return risultato;

        /* Per fare prove di stampa
        PreparedStatement ps = prepareQuery("select password from utenti", new ArrayList<>());
        ArrayList<Object> objs = fromPrepStatementToArrayList(ps, 1);
        for (Object elemento : objs) {
            System.out.println(elemento);
        }
         */
    }

    public double calcolaPesoSettimanaCorrente(String username, boolean SettimanaPrecedente) throws SQLException {
        double somma = 0.0;

        String query = "";
        if (!SettimanaPrecedente) {
            query = "SELECT SUM(peso * ripetizioni) AS somma_pesi_ripetizioni FROM serie WHERE allenamento_username = ? AND strftime('%Y-%W', allenamento_data) = strftime('%Y-%W', 'now');";
        } else {
            query = "SELECT SUM(peso * ripetizioni) AS somma_pesi_ripetizioni FROM serie WHERE allenamento_username = ? AND strftime('%Y-%W', allenamento_data) = strftime('%Y-%W', 'now', '-1 week');";
        }

        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            somma = rs.getDouble("somma_pesi_ripetizioni");
        }
        return somma;
    }
    public HashMap<String, Double> calcolaPesoGruppiMuscolariSettimanaCorrente() throws SQLException {
        ArrayList<String> gruppiMuscolari = (ArrayList<String>) prendiHashListaeserciziNomigruppiDescrizioni().get(2);
        HashMap<String, Double> pesoGruppiMuscolari = new HashMap<>();
        String query = "SELECT e.gruppoMuscolare, SUM(s.peso * s.ripetizioni) AS peso_totale FROM serie AS s, esercizi AS e WHERE allenamento_username = 'marco' AND s.esercizio_nome = e.nome AND strftime('%Y-%W', s.allenamento_data) = strftime('%Y-%W', 'now') GROUP BY e.gruppoMuscolare;";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String gruppoMuscolare = rs.getString("gruppoMuscolare");
            double pesoTotale = rs.getDouble("peso_totale");
            pesoGruppiMuscolari.put(gruppoMuscolare, pesoTotale);
        }

        // Inizializza i gruppi muscolari che non hanno pesi associati con valore zero
        for (String gruppoMuscolare : gruppiMuscolari) {
            if (!pesoGruppiMuscolari.containsKey(gruppoMuscolare)) {
                pesoGruppiMuscolari.put(gruppoMuscolare, 0.0);
            }
        }

        return pesoGruppiMuscolari;
    }
    public boolean registerUser(String username, String password, String nome, String cognome, String email, String dataNascita, String altezza) throws SQLException {
        if (this.con != null && !this.con.isClosed()) {
            PreparedStatement stmt = this.con.prepareStatement("INSERT INTO utenti (username, password, nome, cognome, email, dataNascita, altezza, css) VALUES(?, ?, ?, ?, ?, ?, ?, ?);");
            stmt.setString(1, username);
            String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            stmt.setString(2, generatedSecuredPasswordHash);
            stmt.setString(3, nome);
            stmt.setString(4, cognome);
            stmt.setString(5, email);
            stmt.setString(6, dataNascita);
            stmt.setString(7, altezza);
            stmt.setString(8, "");
            stmt.execute();
            stmt.close();
            return true;
        }
        return false;
    }


    public ArrayList<String> getSchede(String username) throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(username);
        PreparedStatement ps = Database.getInstance().prepareQuery("select distinct nome_scheda from schedepersonalizzate where username = ?;", temp);
        ArrayList<Object> nomi_schede = fromPrepStatementToArrayList(ps, 1);

        ArrayList<String> nomi_schede_string = new ArrayList<String>();

        for (Object obj : nomi_schede) {
            String str = (String) obj; // Casting dell'oggetto in una String
            nomi_schede_string.add(str);
        }
        return nomi_schede_string;
    }

    public ArrayList<String> getEserciziScheda(String username, String scheda) throws SQLException {

        ArrayList<String> temp = new ArrayList<>();
        temp.add(username);
        temp.add(scheda);
        PreparedStatement ps = Database.getInstance().prepareQuery("select nome_esercizi from schedepersonalizzate where username=? and nome_scheda = ?;", temp);
        ArrayList<Object> nomi_schede = fromPrepStatementToArrayList(ps, 1);

        ArrayList<String> nomi_schede_string = new ArrayList<String>();

        for (Object obj : nomi_schede) {
            String str = (String) obj; // Casting dell'oggetto in una String
            nomi_schede_string.add(str);
        }
        return nomi_schede_string;
    }


    public boolean loginIn(String username, String password) throws SQLException {
        ArrayList<String> args = new ArrayList<>();
        args.add(username);
        PreparedStatement ps = prepareQuery("select password from utenti where username = ?", args);
        ArrayList<Object> objs = fromPrepStatementToArrayList(ps, 1);
        if (objs.isEmpty()) {
            return false;
        }
        boolean matched = BCrypt.checkpw(password, (String) objs.get(0));
        return matched;
    }
    public boolean removeUser(String username, String password) throws SQLException {
        if (this.con != null && !this.con.isClosed() && loginIn(username, password)) {
            String query = "DELETE FROM utenti WHERE username = ?";
            PreparedStatement pstmt = this.con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        }
        return false;
    }
    public ArrayList<Object> prendiHashListaeserciziNomigruppiDescrizioni() throws SQLException {
        HashMap<String, ArrayList<String>> gruppoMuscolareEsercizi = new HashMap<>();
        ArrayList<String> ListaTuttiEsercizi = new ArrayList<>();
        ArrayList<String> nomiGruppiMuscolari = new ArrayList<>();
        HashMap<String, String> descrizioni = new HashMap<>();

        // Query per ottenere tutti gli esercizi con i rispettivi gruppi muscolari
        String queryEsercizi = "SELECT nome, gruppoMuscolare FROM esercizi";
        Statement statementEsercizi = con.createStatement();
        ResultSet resultSetEsercizi = statementEsercizi.executeQuery(queryEsercizi);

        while (resultSetEsercizi.next()) {
            String nomeEsercizio = resultSetEsercizi.getString("nome");
            String gruppoMuscolare = resultSetEsercizi.getString("gruppoMuscolare");

            // Aggiungi l'esercizio alla lista di tutti gli esercizi
            ListaTuttiEsercizi.add(nomeEsercizio);

            // Aggiungi l'esercizio al gruppo muscolare corrispondente nella mappa
            if (gruppoMuscolareEsercizi.containsKey(gruppoMuscolare)) {
                gruppoMuscolareEsercizi.get(gruppoMuscolare).add(nomeEsercizio);
            } else {
                ArrayList<String> esercizi = new ArrayList<>();
                esercizi.add(nomeEsercizio);
                gruppoMuscolareEsercizi.put(gruppoMuscolare, esercizi);
            }
        }
        statementEsercizi.close();
        resultSetEsercizi.close();
        // Query per ottenere le descrizioni degli esercizi
        String queryDescrizioni = "SELECT nome, descrizione FROM esercizi";
        Statement statementDescrizioni = con.createStatement();
        ResultSet resultSetDescrizioni = statementDescrizioni.executeQuery(queryDescrizioni);

        while (resultSetDescrizioni.next()) {
            String nomeEsercizio = resultSetDescrizioni.getString("nome");
            String descrizioneEsercizio = resultSetDescrizioni.getString("descrizione");

            // Aggiungi la descrizione dell'esercizio alla mappa
            descrizioni.put(nomeEsercizio, descrizioneEsercizio);
        }
        statementDescrizioni.close();
        resultSetDescrizioni.close();

        // Query per ottenere tutti i nomi dei gruppi muscolari
        String queryGruppiMuscolari = "SELECT DISTINCT gruppoMuscolare FROM esercizi";
        Statement statementGruppiMuscolari = con.createStatement();
        ResultSet resultSetGruppiMuscolari = statementGruppiMuscolari.executeQuery(queryGruppiMuscolari);

        while (resultSetGruppiMuscolari.next()) {
            String gruppoMuscolare = resultSetGruppiMuscolari.getString("gruppoMuscolare");

            // Aggiungi il nome del gruppo muscolare alla lista
            nomiGruppiMuscolari.add(gruppoMuscolare);
        }
        statementGruppiMuscolari.close();
        resultSetGruppiMuscolari.close();


        ArrayList<Object> risultato = new ArrayList<>();
        risultato.add(gruppoMuscolareEsercizi);
        risultato.add(ListaTuttiEsercizi);
        risultato.add(nomiGruppiMuscolari);
        risultato.add(descrizioni);

        return risultato;
        /*
        ArrayList<Object> risultato = prendiHashListaeserciziNomigruppiDescrizioni();
        HashMap<String, ArrayList<String>> gruppoMuscolareEsercizi = (HashMap<String, ArrayList<String>>) risultato.get(0);
        ArrayList<String> ListaTuttiEsercizi = (ArrayList<String>) risultato.get(1);
        ArrayList<String> nomiGruppiMuscolari = (ArrayList<String>) risultato.get(2);
        HashMap<String, String> descrizioni = (HashMap<String, String>) risultato.get(3);
         */
    }

    public boolean aggiungiAllenamento(String username, String scheda, String data, HashMap<String,ArrayList<Serie>> seriePerEsercizio){
        try {
            // Inserimento dell'allenamento nella tabella "allenamenti"
            String queryAllenamento = "INSERT INTO allenamenti (username, data, scheda) VALUES (?, ?, ?)";
            PreparedStatement statementAllenamento = con.prepareStatement(queryAllenamento);
            statementAllenamento.setString(1, username);
            statementAllenamento.setString(2, data);
            statementAllenamento.setString(3, scheda);
            statementAllenamento.executeUpdate();
            statementAllenamento.close();

            // Inserimento delle serie per ciascun esercizio nella tabella "serie"
            String querySerie = "INSERT INTO serie (allenamento_username, allenamento_data, esercizio_nome, peso, ripetizioni, recuperoSecondi) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statementSerie = con.prepareStatement(querySerie);

            for (String esercizio : seriePerEsercizio.keySet()) {
                ArrayList<Serie> serie = seriePerEsercizio.get(esercizio);
                for (Serie s : serie) {
                    statementSerie.setString(1, username);
                    statementSerie.setString(2, data);
                    statementSerie.setString(3, esercizio);
                    statementSerie.setDouble(4, s.getPeso());
                    statementSerie.setInt(5, s.getRipetizioni());
                    statementSerie.setInt(6, s.getRecuperoSecondi());
                    statementSerie.executeUpdate();
                }
            }
            statementSerie.close();

        /*
        // Dati di esempio per l'allenamento
        String username = "marco";
        String scheda = "Scheda A";
        String data = "2023-05-23";
        HashMap<String, ArrayList<Serie>> seriePerEsercizio = new HashMap<>();
        ArrayList<Serie> seriePancaPiana = new ArrayList<>();
        seriePancaPiana.add(new Serie(50.0, 10, 60));
        seriePancaPiana.add(new Serie(60.0, 8, 60));
        seriePancaPiana.add(new Serie(70.0, 6, 90));
        seriePerEsercizio.put("panca piana", seriePancaPiana);
        ArrayList<Serie> serieSquat = new ArrayList<>();
        serieSquat.add(new Serie(80.0, 10, 90));
        serieSquat.add(new Serie(90.0, 8, 90));
        serieSquat.add(new Serie(100.0, 6, 120));
        seriePerEsercizio.put("squat", serieSquat);
        ArrayList<Serie> serieCroci = new ArrayList<>();
        serieCroci.add(new Serie(20.0, 12, 60));
        serieCroci.add(new Serie(25.0, 10, 60));
        serieCroci.add(new Serie(30.0, 8, 90));
        seriePerEsercizio.put("croci", serieCroci);
        // Aggiunta dell'allenamento al database
        aggiungiAllenamento(username, scheda, data, seriePerEsercizio);
         */
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminaAllenamento(String username, String data){
        try {
            // Elimina l'allenamento dalla tabella "allenamenti"
            String queryEliminaAllenamento = "DELETE FROM allenamenti WHERE username = ? AND data = ?";
            PreparedStatement statementEliminaAllenamento = con.prepareStatement(queryEliminaAllenamento);
            statementEliminaAllenamento.setString(1, username);
            statementEliminaAllenamento.setString(2, data);
            statementEliminaAllenamento.executeUpdate();

            // Elimina le serie associate all'allenamento dalla tabella "serie"
            String queryEliminaSerie = "DELETE FROM serie WHERE allenamento_username = ? AND allenamento_data = ?";
            PreparedStatement statementEliminaSerie = con.prepareStatement(queryEliminaSerie);
            statementEliminaSerie.setString(1, username);
            statementEliminaSerie.setString(2, data);
            statementEliminaSerie.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Object> daDataAAllenamento(String username, String dataAllenamento) throws SQLException {
        String nomeScheda = "";
        HashMap<String, ArrayList<Serie>> seriePerEsercizio = new HashMap<>();

        PreparedStatement stmt = con.prepareStatement("SELECT scheda FROM allenamenti WHERE username = ? AND data = ?");
        stmt.setString(1, username);
        stmt.setString(2, dataAllenamento);
        ResultSet rsScheda = stmt.executeQuery();

        if (rsScheda.next()) {
            nomeScheda = rsScheda.getString("scheda");
        }

        PreparedStatement stmtSerie = con.prepareStatement("SELECT esercizio_nome, peso, ripetizioni, recuperoSecondi FROM serie WHERE allenamento_username = ? AND allenamento_data = ?");
        stmtSerie.setString(1, username);
        stmtSerie.setString(2, dataAllenamento);
        ResultSet rsSerie = stmtSerie.executeQuery();

        while (rsSerie.next()) {
            String nomeEsercizio = rsSerie.getString("esercizio_nome");
            double peso = rsSerie.getDouble("peso");
            int ripetizioni = rsSerie.getInt("ripetizioni");
            int recuperoSecondi = rsSerie.getInt("recuperoSecondi");

            Serie serie = new Serie(peso, ripetizioni, recuperoSecondi);

            if (seriePerEsercizio.containsKey(nomeEsercizio)) {
                seriePerEsercizio.get(nomeEsercizio).add(serie);
            } else {
                ArrayList<Serie> serieList = new ArrayList<>();
                serieList.add(serie);
                seriePerEsercizio.put(nomeEsercizio, serieList);
            }
        }

        stmt.close();
        stmtSerie.close();


        ArrayList<Object> results = new ArrayList<>();
        results.add(nomeScheda);
        results.add(seriePerEsercizio);
        return results;
    }

    public ArrayList<Object> daUtenteDateBilancia(String username) throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(username);
        PreparedStatement ps = prepareQuery("select datapesata, peso from peso where username = ? ORDER BY dataPesata;", temp);
        ArrayList<Object> results = fromPrepStatementToArrayList(ps, 2);
        //[[data, peso],[data1, peso],...]

        return results;
    }
    public ArrayList<ArrayList<String>> calcolaPunteggiUtenteXEsercizio(String username, String nomeEsercizio) throws SQLException {
        ArrayList<ArrayList<String>> punteggiUtente = new ArrayList<>();
        String query = "select peso, ripetizioni, allenamento_data from serie where allenamento_username = ? AND esercizio_nome = ?;";
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, nomeEsercizio);
        ResultSet rs = stmt.executeQuery();
        System.out.println("ciao");
        HashMap<String, ArrayList<Serie>> seriePerGiorno = new HashMap<>();

        while (rs.next()) {
            String data = rs.getString("allenamento_data");
            double peso = rs.getDouble("peso");
            int ripetizioni = rs.getInt("ripetizioni");
            // Creare l'oggetto Serie
            Serie serie = new Serie(peso, ripetizioni, 0); // il recuperoSecondi non necessario per il calcolo del punteggio
            if (seriePerGiorno.containsKey(data)) {
                // Aggiungere la serie alla lista delle serie del giorno
                seriePerGiorno.get(data).add(serie);
            } else {
                // Creare una nuova lista di serie per il giorno e aggiungere la serie
                ArrayList<Serie> serieGiorno = new ArrayList<>();
                serieGiorno.add(serie);
                seriePerGiorno.put(data, serieGiorno);
            }
        }
        // Ordinare le date in ordine cronologico
        ArrayList<String> dateOrdinate = new ArrayList<>(seriePerGiorno.keySet());
        Collections.sort(dateOrdinate);

        // Calcolare il punteggio per ogni giorno
        for (String data : dateOrdinate) {
            ArrayList<Serie> serieGiorno = seriePerGiorno.get(data);

            double punteggioGiorno = 0.0;
            for (Serie serie : serieGiorno) {
                double punteggioSerie = daSerieAPunteggio(serie);
                punteggioGiorno += punteggioSerie;
            }
            ArrayList<String> entry = new ArrayList<>();
            entry.add(data);
            entry.add(Double.toString(punteggioGiorno));

            punteggiUtente.add(entry);
        }
        return punteggiUtente; //[[2023-05-23, 222.5], [2023-05-31, 188.0]]
    }
    public double daSerieAPunteggio(Serie s) {
        //1RM = peso sollevato / [1.0278 - (0.0278 x numero di ripetizioni)]
        return Math.round((s.getPeso() / (1.0278 - (0.0278 * s.getRipetizioni()))) * 10.0) / 10.0;
    }
}