/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
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

class Valida {

    public static void valida(String fileXSD, String fileXML) throws IOException, org.xml.sax.SAXException {

        SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema sc = sf.newSchema(new File(fileXSD));

        javax.xml.validation.Validator v = sc.newValidator();
        v.validate(new StreamSource(fileXML));
    }

}

class ReadXMLFile {

    public static LinkedList<CD> main(String Xml) {
        LinkedList<CD> ris = new LinkedList<>();

        try {

            String xml = Xml;
            String xsd = new String("CD.xsd");
            Valida.valida(xsd, xml);
            File fXmlFile = new File(Xml);


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("CD");

            // System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                
                String[] brani = new String[101];
                

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                   
                    for (int i = 0; i < eElement.getElementsByTagName("Brano").getLength(); i++) {
                        if (i == 101)
                            break;
                        brani[i] = (eElement.getElementsByTagName("Brano").item(i).getTextContent() + "\n");
                    }
                    
                    
                    ris.add(new CD(Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent()),
                            eElement.getElementsByTagName("Titolo").item(0).getTextContent(),
                            eElement.getElementsByTagName("Autore").item(0).getTextContent(),
                            brani));
                            


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ris;

    }
}

public class Main {

    /**
     * @param args the command line arguments
     */
    public  static LinkedList<CD> cds = new LinkedList<>();
    public static void main(String[] args) throws Exception {

        String Xml = new String("RaccoltaCD.xml");
        cds = ReadXMLFile.main(Xml);
        /*for(CD c : a)
        {
            System.out.print(c.toString());
        }*/
       Server s = new Server();
        
    }
    
}
