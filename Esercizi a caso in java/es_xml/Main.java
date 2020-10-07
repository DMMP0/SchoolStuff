package com.company;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;


// CARICA IN MEMORIA

 class ReadXMLFile {

    public static LinkedList<Cassonetto> main(String Xml) {
        LinkedList<Cassonetto> ris = new LinkedList<>();

        try {

            String xml = new String(Xml);
            String xsd = new String("Cassonetto.xsd");
            Valida.valida(xsd, xml);
            File fXmlFile = new File(Xml);



            LinkedList<UltimoSvuotamento> ulv = new LinkedList<UltimoSvuotamento>();


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();
            //   System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Cassonetto");

            // System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                int[] impiegati = new int[50];
                String[] veicoli = new String[50];
                Element coord;
                Element ul;
                String Coordinate;
                String[] note = new String[5];

                // System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    coord = (Element) eElement.getElementsByTagName("Coordinate_Geografiche").item(0);
                    Coordinate = ("X:" + coord.getElementsByTagName("Longitudine").item(0).getTextContent() + " Y:" + coord.getElementsByTagName("Latitudine").item(0).getTextContent() + "\n");
                    ul = (Element) eElement.getElementsByTagName("Ultimo_Svuotamento").item(0);

                    for (int i = 0; i < eElement.getElementsByTagName("Note_Testuali").getLength(); i++) {
                        if (i == 5)
                            break;
                        note[i] = (eElement.getElementsByTagName("Note_Testuali").item(i).getTextContent() + "\n");
                    }
                    for (int i = 0; i < ul.getElementsByTagName("Operatore").getLength(); i++) {
                        if (i == 50)
                            break;
                        impiegati[i] = (Integer.parseInt(ul.getElementsByTagName("Operatore").item(i).getTextContent()));
                    }
                    for (int i = 0; i < ul.getElementsByTagName("Veicolo").getLength(); i++) {
                        if (i == 50)
                            break;
                        veicoli[i] = (ul.getElementsByTagName("Veicolo").item(i).getTextContent());
                    }
                    ulv.add(new UltimoSvuotamento(impiegati, veicoli, ul.getElementsByTagName("Data_Ora").item(0).getTextContent()));
                    ris.add(new Cassonetto(Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent()),
                            eElement.getElementsByTagName("Tipologia").item(0).getTextContent(),
                            eElement.getElementsByTagName("Indirizzo").item(0).getTextContent(),
                            Coordinate,
                            note,
                            eElement.getElementsByTagName("Data_Posizionamento").item(0).getTextContent(),
                            ulv.get(temp),
                            Integer.parseInt(eElement.getElementsByTagName("Volume").item(0).getTextContent())));


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ris;

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

class UltimoSvuotamento
{
    private int[] Operatori=new int[50];
    private String[] Targhe = new String[50];
    private String DataUltimoSvuotamento;

    public int[] getOperatori() {
        return Operatori;
    }
    public void setOperatori(int[] operatori) {
        for(int i = 0; i< 50;i++)
        {
            Operatori[i] = operatori[i];
        }
    }
    public String[] getTarghe() {
        return Targhe;
    }
    public void setTarghe(String[] targhe) {
        for(int i = 0; i< 50;i++)
        {
            Targhe[i]=targhe[i];
        }
    }
    public String getDataUltimoSvuotamento() {

        String t = DataUltimoSvuotamento.replace('T', '/');
        return t;

    }
    public String getDataUltimoSvuotamento2() {


        return DataUltimoSvuotamento;

    }
    public void setDataUltimoSvuotamento(String dataUltimoSvuotamento) {
        DataUltimoSvuotamento = dataUltimoSvuotamento;
    }

    public UltimoSvuotamento()
    {

        DataUltimoSvuotamento = "Non ancora effettuto";

    }
    public UltimoSvuotamento(int[] a,String[] b, String c)
    {
        setOperatori(a);
        setTarghe(b);
        DataUltimoSvuotamento = c;

    }
}

class Cassonetto
{
    private  static  int TheID=0;
    private int ID;
    private String Tipologia;
    private String Indirizzo;
    private String CoordinateGeografiche;
    private String[] NoteTestuali = new String[5];
    private String DataPosizionamento;
    private UltimoSvuotamento UlSv;
    private int Volume;

    public int getID() {
        return ID;
    }
    public String getTipologia() {
        return Tipologia;
    }
    public void setTipologia(String tipologia) {
        Tipologia = tipologia;
    }
    public String getIndirizzo() {
        return Indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }
    public String getCoordinateGeografiche() {
        return CoordinateGeografiche;
    }
    public void setCoordinateGeografiche(String coordinateGeografiche) {
        CoordinateGeografiche = coordinateGeografiche;
    }
    public String[] getNoteTestuali() {
        return NoteTestuali;
    }
    public void setNoteTestuali(String[] noteTestuali) {
        for(int i = 0; i< 5;i++)
        {
            NoteTestuali[i]=noteTestuali[i];
        }
    }
    public String getDataPosizionamento() {
        return DataPosizionamento;
    }
    public void setDataPosizionamento(String dataPosizionamento) {
        DataPosizionamento = dataPosizionamento;
    }
    public UltimoSvuotamento getUlSv() {
        return UlSv;
    }
    public void setUlSv(UltimoSvuotamento ulSv) {
        UlSv = ulSv;
    }
    public int getVolume() {
        return Volume;
    }
    public void setVolume(int volume) {
        Volume = volume;
    }

    private Cassonetto()
    {
        ID = TheID+1;
        TheID++;
        Tipologia = "Generico";
        Indirizzo = "Non ancora posizionato";
        CoordinateGeografiche = "Non ancora posizionato";
        NoteTestuali = null;
        DataPosizionamento = "Non ancora posizionato";
        UlSv = new UltimoSvuotamento();
        Volume = 1;
    }
    public Cassonetto(int id,String tipo,String ind, String cg,String[] nt,String datapos,UltimoSvuotamento ulsv,int vol)
    {
        ID = id;
        setTipologia(tipo);
        setIndirizzo(ind);
        setCoordinateGeografiche(cg);
        setNoteTestuali(nt);
        setDataPosizionamento(datapos);
        setUlSv(ulsv);
        setVolume(vol);
    }

    @Override
    public String toString()
    {
        StringBuilder ris =new StringBuilder ("Cassonetto n°"+ID+"\n\tTipologia: "+getTipologia()+"\n\tIndirizzo: "+getIndirizzo()+"\n\tCoordinate Geografiche: "+getCoordinateGeografiche());
        ris.append("\tNote:\n");
        for (String s:NoteTestuali)
        {
            if(s==null)
                break;
            ris.append("\t\t\t\t"+s);
        }
        ris.append("\n\tData Posizionamento:" + getDataPosizionamento());
        ris.append("\n\tUltimo Svuotamento: \n\t\t\t\tOperatori:- ");
        for(int i = 0; i<50;i++)
        {
            if(UlSv.getOperatori()[i] == 0)
                break;
            ris.append(""+UlSv.getOperatori()[i]+" - ");
        }
        ris.append("\n\t\t\t\tVeicoli:- ");
        for(int i = 0; i<50;i++)
        {
            if(UlSv.getTarghe()[i] == null)
                break;
            ris.append(UlSv.getTarghe()[i]+" - ");
        }
        ris.append("\n\t\t\t\tData/ora: "+UlSv.getDataUltimoSvuotamento());
        ris.append("\n\tVolume: "+Volume+" metri cubi\n");

        return  ris.toString();
    }
    public  boolean SvuotatoNelleUltime24Ore()
    {
       String ldt =  LocalDateTime.now().toString();
       if(Integer.parseInt(UlSv.getDataUltimoSvuotamento2().substring(0,3))==Integer.parseInt(ldt.substring(0,3) ))
       {
           if(Integer.parseInt(UlSv.getDataUltimoSvuotamento2().substring(5,6))==Integer.parseInt(ldt.substring(5,6) ))
           {
               if(Integer.parseInt(UlSv.getDataUltimoSvuotamento2().substring(8,9))==Integer.parseInt(ldt.substring(8,9) ))
               {return true;}
               else
               {
                   if((Integer.parseInt(ldt.substring(8,9)) - Integer.parseInt(UlSv.getDataUltimoSvuotamento2().substring(8,9)) == 1 ))
                   {
                       if((24+Integer.parseInt(ldt.substring(11,12)) - Integer.parseInt(UlSv.getDataUltimoSvuotamento2().substring(11,12)) <= 24 ))
                       {
                           return true;
                       }
                       else
                       {
                           return  false;
                       }
                   }
                   else
                   {
                      return false;
                   }
               }
           }
           else
           {
               return false;
           }
       }
       else
       {
           return false;
       }
    }
}

//LEGGE SCORRENDO
public class Main {

    public static void main(String[] args) throws org.xml.sax.SAXException {

        LinkedList<Cassonetto> Cassonetti = new LinkedList<>();
        Cassonetti = ReadXMLFile.main(args[0]);
        for (Cassonetto c : Cassonetti)
        {
            if((c.getTipologia().equals("Generico")||c.getTipologia().equals("generico"))&&!c.SvuotatoNelleUltime24Ore())
            {
                System.out.print(c.toString());
            }
        }



    }


}