package com.example.skiplegday.model;
public class Serie {
    private double peso;
    private int ripetizioni;
    private int recuperoSecondi;

    public Serie(double peso, int ripetizioni, int recuperoSecondi) {
        this.peso = peso;
        this.ripetizioni = ripetizioni;
        this.recuperoSecondi = recuperoSecondi;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getRecuperoSecondi() {
        return recuperoSecondi;
    }

    public void setRecuperoSecondi(int recuperoSecondi) {
        this.recuperoSecondi = recuperoSecondi;
    }
}
