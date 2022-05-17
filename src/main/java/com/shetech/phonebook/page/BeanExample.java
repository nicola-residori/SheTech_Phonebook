package com.shetech.phonebook.page;

public class BeanExample {

    /* - attributi - */
    private String nome;
    private String cognome;
    private int eta;

    private int count;

    private boolean donna;

    public BeanExample() {
        eta = 15;
        count = 0;
    }

    public boolean isDonna() {
        return donna;
    }

    public void setDonna(boolean donna) {
        this.donna = donna;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public int getCount() {
        return count++;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
