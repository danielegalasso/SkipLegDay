/*IMPORTANTE:
    PER PROVARE L'APP UTILIZZARE QUESTI DATI DI ACCESSO:     UTENTI REALMENTE IMPLEMENTATI
    domenico domenico    (username e password)
    daniele daniele      (username e password)
    federico federico    (username e password)
    sono tre utenti con gia schede di allenamento create, recensioni e voti. Db popolato con circa 60 mila dati.
     */


ESEMPIO DI UTENTI NON SONO QUELLI EFFETTIVI.:
username | password  | nome  | cognome | sesso       | peso       | dataNascita | altezza	|css
--------------------------------------------------------------------------------------------------------  
marco    | password1 | Marco | Rossi   |   M         |    70.8    | 1990-01-01  | 180		| css1
giulia   | password2 | Giulia| Verdi   | F           |    77.8    | 1992-05-10  | 165		| css2

Peso:
id | username | dataPesata | peso
----------------------------------
1  | marco    | 2023-05-31 | 75.5
2  | giulia   | 2023-05-31 | 62.2

Alenamenti:
username | data       | scheda
----------------------------------------
marco    | 2023-05-31 | petto-braccia
giulia   | 2023-05-31 | gambe-glutei

SchedePersonalizzate:
id   |  username | nome_scheda     |     nome_esercizi 
------------------------------------------------------------
1    |  marco    | petto-braccia   |  panca piana
2    |  giulia   | gambe-glutei	   |  squat
3    |  marco    | dorso-tricipiti |  croci
1    |  marco    | petto-braccia   |  croci



Esercizi:
nome        | descrizione                              | gruppoMuscolare
--------------------------------------------------------------------------
panca piana | Esercizio per pettorali e tricipiti      | petto
squat       | Esercizio per gambe e glutei             | gambe
croci	    | Esercizio petto 			       | petto

Serie:
id | allenamento_username | allenamento_data | esercizio_nome | peso | ripetizioni | recuperoSecondi
------------------------------------------------------------------------------------------------------
1  | marco                | 2023-05-31       | panca piana    | 60.0 | 10          | 60
2  | marco                | 2023-05-31       | panca piana    | 75.0 | 12          | 60
3  | giulia               | 2023-05-31       | squat          | 9.0  | 30          | 10




CREATE TABLE utenti (
    username TEXT PRIMARY KEY,
    password TEXT,
    nome TEXT,
    cognome TEXT,
    sesso TEXT,
    peso REAL,
    dataNascita TEXT,
    altezza REAL,
    css TEXT,
    valutazione REAL,
    recensione TEXT
);


CREATE TABLE allenamenti (
    username TEXT,
    data TEXT,
    scheda TEXT,
    PRIMARY KEY (username, data),
    FOREIGN KEY (username) REFERENCES utenti(username)
    FOREIGN KEY (scheda) REFERENCES schedepersonalizzate(nome_scheda)
);

CREATE TABLE schedepersonalizzate ( 
    id INTEGER,
    username TEXT,
    nome_scheda TEXT,
    nome_esercizi TEXT,
    PRIMARY KEY (username, nome_scheda, nome_esercizi),
    FOREIGN KEY (username) REFERENCES utenti(username),
    FOREIGN KEY (nome_esercizi) REFERENCES esercizi(nome)
);

 
CREATE TRIGGER increment_id
AFTER INSERT ON schedepersonalizzate
BEGIN
    UPDATE schedepersonalizzate
    SET id = (SELECT COALESCE(MAX(id), 0) + 1 FROM schedepersonalizzate)
    WHERE username = new.username AND nome_scheda = new.nome_scheda and nome_esercizi = new.nome_esercizi;
END;




CREATE TABLE esercizi (
    nome TEXT PRIMARY KEY,
    descrizione TEXT,
    gruppoMuscolare TEXT
);

CREATE TABLE serie (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    allenamento_username TEXT,
    allenamento_data TEXT,
    esercizio_nome TEXT,
    peso REAL,
    ripetizioni INTEGER,
    recuperoSecondi INTEGER,
    FOREIGN KEY (allenamento_username, allenamento_data) REFERENCES allenamenti(username, data),
    FOREIGN KEY (esercizio_nome) REFERENCES esercizi(nome)
);



Documentazione professore per type affinity: 
https://sqlite.org/faq.html#q3
https://sqlite.org/datatype3.html#affinity




CREATE TABLE utenti (
    username TEXT PRIMARY KEY,
    password TEXT,
    nome TEXT,
    cognome TEXT,
    sesso TEXT,
    peso REAL,
    dataNascita TEXT,
    altezza REAL,
    css TEXT,
    valutazione REAL,
    recensione TEXT
);


CREATE TABLE allenamenti (
    username TEXT,
    data TEXT,
    scheda TEXT,
    PRIMARY KEY (username, data),
    FOREIGN KEY (username) REFERENCES utenti(username)
    FOREIGN KEY (scheda) REFERENCES schedepersonalizzate(nome_scheda)
);

CREATE TABLE schedepersonalizzate (
    id INTEGER,
    username TEXT,
    nome_scheda TEXT,
    nome_esercizi TEXT,
    PRIMARY KEY (username, nome_scheda, nome_esercizi),
    FOREIGN KEY (username) REFERENCES utenti(username),
    FOREIGN KEY (nome_esercizi) REFERENCES esercizi(nome)
);


CREATE TRIGGER increment_id
AFTER INSERT ON schedepersonalizzate
BEGIN
    UPDATE schedepersonalizzate
    SET id = (SELECT COALESCE(MAX(id), 0) + 1 FROM schedepersonalizzate)
    WHERE username = new.username AND nome_scheda = new.nome_scheda and nome_esercizi = new.nome_esercizi;
END;
CREATE TABLE esercizi (
    nome TEXT PRIMARY KEY,
    descrizione TEXT,
    gruppoMuscolare TEXT
);
CREATE TABLE serie (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    allenamento_username TEXT,
    allenamento_data TEXT,
    esercizio_nome TEXT,
    peso REAL,
    ripetizioni INTEGER,
    recuperoSecondi INTEGER,
    FOREIGN KEY (allenamento_username, allenamento_data) REFERENCES allenamenti(username, data),
    FOREIGN KEY (esercizio_nome) REFERENCES esercizi(nome)
);


-------------------------------------------