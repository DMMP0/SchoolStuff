/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author utente
 */
class CD implements Serializable
{
    private static final long serialVersionUID = 5950169519310163575L;
    private static int TheID = 0;
    private int ID;
    private String Titolo;
    private String Autore;
    private String[] Brani = new String[101];

   
    
    

    public int getID() {
        return ID;
    }
    public String getTitolo() {
        return Titolo;
    }
    public void setTitolo(String Titolo) {
        this.Titolo = Titolo;
    }

    public String getAutore() {
        return Autore;
    }
    public void setAutore(String Autore) {
        this.Autore = Autore;
    }
    public String[] getBrani() {
        return Brani;
    }
    public void setBrani(String[] brani) {
        for(int i=0;i<brani.length;i++)
        {
            if(i>=50)
                break;
            else
                Brani[i] = brani[i];
        }
    }
    
    public CD()
    {
     ID = TheID+1;
     TheID++;
     Titolo = "nessuno";
     Autore = "nessuno";
    }
    public CD(String titolo,String autore,String[] brani)
    {
     ID = TheID+1;
     TheID++;
     Titolo = titolo;
     Autore = autore;
     setBrani(brani);
    }
    public CD(int id,String titolo,String autore,String[] brani)
    {
     ID = id;
     Titolo = titolo;
     Autore = autore;
     setBrani(brani);
    }

    @Override
    public String toString()
    {
        StringBuilder ris = new StringBuilder();
        ris.append("\tCD n° "+ID+"\n\t\tTitolo: "+Titolo+"\n\t\tAutore: "+Autore+"\n\t\tBrani:\n");
        for(int i=0; i<101;i++)
        {
            if(Brani[i]==null)
                break;
            ris.append("\t\t"+i+": "+Brani[i]+"\n");
        }
        return ris.toString();
    }
}


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client.main("");
    }
    
}
