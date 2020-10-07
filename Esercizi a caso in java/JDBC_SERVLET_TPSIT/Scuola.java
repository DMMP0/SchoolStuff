package com.company;

import java.io.Serializable;

public class Scuola implements Serializable
{
    private static  final long serialVersionUID = 6297385302078200512L;

    private String Meccanografico;
    private String Nome;
    private String Regione;
    private String Provincia;

    public String getMeccanografico() {
        return Meccanografico;
    }
    public void setMeccanografico(String meccanografico) {
        Meccanografico = meccanografico;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getRegione() {
        return Regione;
    }
    public void setRegione(String regione) {
        Regione = regione;
    }
    public String getProvincia() {
        return Provincia;
    }
    public void setProvincia(String provincia) {
        Provincia = provincia;
    }

    public Scuola(String meccanografico, String nome, String regione, String provincia)
    {
        setMeccanografico(meccanografico);
        setNome(nome);
        setProvincia(provincia);
        setRegione(regione);

    }
}
