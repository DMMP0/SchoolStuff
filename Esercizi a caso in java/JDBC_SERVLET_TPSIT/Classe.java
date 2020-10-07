package com.company;

import java.io.Serializable;

public class Classe implements Serializable
{
    private static  final long serialVersionUID = 6297385302078200511L;

    private String Scuola;
    private String Nome;
    private String Indirizzo;
    private String Opzione;



    public String getScuola() {
        return Scuola;
    }
    public void setScuola(String scuola) {
        Scuola = scuola;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getIndirizzo() {
        return Indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }
    public String getOpzione() {
        return Opzione;
    }
    public void setOpzione(String opzione) {
        Opzione = opzione;
    }

    public Classe(String scuola, String nome, String indirizzo, String opzione)
    {
        setIndirizzo(indirizzo);
        setNome(nome);
        setOpzione(opzione);
        setScuola(scuola);

    }

    @Override
    public String toString() {
        String ris =
                        "Nome: " + Nome+
                        "\nScuola: " + Scuola+
                        "\nIndirizzo: " +Indirizzo +
                        " -> " + Opzione +"\n";
        return  ris;
    }
}
