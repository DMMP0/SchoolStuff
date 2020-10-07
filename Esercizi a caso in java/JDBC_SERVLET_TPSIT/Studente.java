package com.company;

import java.io.Serializable;

public class Studente implements Serializable
{
    private static  final long serialVersionUID = 6297385302078200513L;

    private int ID;
    private String Cognome;
    private String Nome;
    private String DataNascita;
    private String LuogoNascita;
    private int Classe;


    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getCognome() {
        return Cognome;
    }
    public void setCognome(String cognome) {
        Cognome = cognome;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getDataNascita() {
        return DataNascita;
    }
    public void setDataNascita(String dataNascita) {
        if(dataNascita.length()== 10)
        {
            if(dataNascita.charAt(4) == '-' && dataNascita.charAt(7) == '-')
                DataNascita = dataNascita;
            else
                System.out.print("La data deve essere in formato YYYY-MM-DD");
        }
        else
            System.out.print("La data deve essere in formato YYYY-MM-DD");

    }
    public String getLuogoNascita() {
        return LuogoNascita;
    }
    public void setLuogoNascita(String luogoNascita) {
        LuogoNascita = luogoNascita;
    }
    public int getClasse() {
        return Classe;
    }
    public void setClasse(int classe) {
        Classe = classe;
    }

    public Studente(int ID, String cognome, String nome, String dataNascita, String luogoNascita, int classe) {
        setID(ID);
        setCognome(cognome);
        setNome(nome);
        setDataNascita(dataNascita);
        setLuogoNascita(luogoNascita);
        setClasse(classe);
    }
}
