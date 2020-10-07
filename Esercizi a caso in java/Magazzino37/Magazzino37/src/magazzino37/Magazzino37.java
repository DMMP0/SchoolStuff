/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino37;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author dpana
 */
public class Magazzino37 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Valida.valida("Magazzino.xsd", "Magazzino.xml");
        } catch (SAXException|IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(-1);
        } 
        
        ParseMag p = new ParseMag();
        List<Prodotto> products=null;
        try {
            System.out.println(args[0]);
            products = p.parseXMLdoc(args[0],"Magazzino.xml");
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        if (products!=null){
           
            for (Iterator it = products.iterator(); it.hasNext();) {
                Prodotto prod;
                prod = (Prodotto) it.next();
                System.out.println("Codice="+prod.getCodice()+";"+"Marca="+prod.getMarca()+";"+"Modello="+prod.getModello());
            }
        }
    }
    
}
