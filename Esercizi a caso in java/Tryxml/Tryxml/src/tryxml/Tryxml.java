/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryxml;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author dpana
 */
public class Tryxml {
    private Document doc;
    private Element root;
    
        
    /**
     *
     * @param nomefile
     */
    public Tryxml(String nomefile){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
        try {
            db = dbf.newDocumentBuilder();
            doc = db.parse(new File(nomefile));
            root=doc.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    public void leggiElemento(Node e){
        System.out.println("Elemento: " + e.getNodeName());
        leggiAttributi(e.getAttributes());
        leggiFigli(e.getChildNodes());
        System.out.println("Fine elemento: " + e.getNodeName());
    }
    
    public void leggiAttributi(NamedNodeMap attribMap){
       //ogni attributo è un Nodo nel DOM; più attributi possono essere associatia ad uno stesso nodo      
               
          for(int i=0; i<attribMap.getLength(); i++) {
             
              Attr attributo = (Attr) attribMap.item(i);
              System.out.println("Attributo: "+ attributo.getNodeName() );
              System.out.println("\t"+attributo.getNodeValue());
              System.out.println("Fine Attributo" +attributo.getNodeName() );
              
          }
    }
    
    public void leggiFigli(NodeList figli) {
        for(int i=0; i<figli.getLength(); i++)
            leggiNodo(figli.item(i));
     }// leggiFigli
    
    public void leggiNodo(Node nodo) {
        switch(nodo.getNodeType()) {
          case Node.ELEMENT_NODE:
              leggiElemento(nodo);
              break;
          case Node.TEXT_NODE:
              leggiTesto(nodo);
              break;
          default:
              leggiTesto(nodo);
              break;
        }
    }
    
    public void leggiTesto(Node nodo){
        System.out.println("\t"+nodo.getTextContent());
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Valida.valida("CartaId.xsd", "CartaId.xml");
            System.out.println("Documento Valido");
            Tryxml xmldoc = new Tryxml("CartaId.xml");
            xmldoc.leggiElemento(xmldoc.root);
        } catch (SAXException| IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
      
           
    }
    
}
