/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino37;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.*;

/**
 *
 * @author dpana
 */
public class ParseMag {
  private List<Prodotto> prodotti;

  public ParseMag(){
      prodotti=new ArrayList();
  }  
  
  public List parseXMLdoc(String categ, String nomeFile) throws ParserConfigurationException, SAXException, IOException{
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db=dbf.newDocumentBuilder();
      Document doc = db.parse(new File(nomeFile));
      Element root=doc.getDocumentElement();
                 
      NodeList prodList;
      if(null!=(prodList = root.getElementsByTagName("prodotto"))){
          prodotti.clear();
          for(int i=0;i<prodList.getLength(); i++){
             
              Element prod = (Element)prodList.item(i);
              Prodotto p;
              p = get_prodotto(prod, categ);
              if (p!=null){
                prodotti.add(p);
              }
              
          }
      }
          
      return prodotti;
  }
  
  private Prodotto get_prodotto(Element prod, String cat){
      Prodotto p=null;
           
      int qta=Integer.parseInt(prod.getElementsByTagName("quantità").item(0).getFirstChild().getTextContent());
      String categoria = prod.getElementsByTagName("categoria").item(0).getFirstChild().getTextContent();
      
      if(cat.equals(categoria) && (qta<=1)){
          
          p = new Prodotto();
          p.setCategoria(Prodotto.cat.valueOf(cat));
          p.setCodice(prod.getElementsByTagName("codice").item(0).getFirstChild().getTextContent());
          p.setDescrizione(prod.getElementsByTagName("descrizione").item(0).getFirstChild().getTextContent());
          p.setMarca(prod.getElementsByTagName("marca").item(0).getFirstChild().getTextContent());
          p.setModello(prod.getElementsByTagName("modello").item(0).getFirstChild().getTextContent());
          String prezzo;
          prezzo = prod.getElementsByTagName("prezzo").item(0).getFirstChild().getTextContent();
          p.setPrezzo(Float.parseFloat(prezzo));
          p.setQuantità(qta);
                 
      } 
      
      return p;
      
  }
}
