/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino37;

import java.io.File;
import java.io.IOException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author dpana
 */
public class Valida {
    public static void valida(String fileXSD, String fileXML)throws SAXException, IOException {
        
            SchemaFactory sf= SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema sc= sf.newSchema(new File(fileXSD));
           
            Validator v = sc.newValidator();
            v.validate (new StreamSource(fileXML));
    }
}
