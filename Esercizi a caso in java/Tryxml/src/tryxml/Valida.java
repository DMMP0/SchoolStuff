/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tryxml;

import javax.xml.validation.*;
import org.xml.sax.*;
import java.io.*;
import javax.xml.transform.stream.StreamSource;


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
