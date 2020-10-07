/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dpana
 */
public class ServletPhonebook extends HttpServlet {

    private final static Phonebook phonebook = new Phonebook();

    // attivazione servlet (caricamento rubrica da file)
       @Override
    public void init() {
        try {
            synchronized(phonebook) {
                phonebook.loadBook();
            }
        }
        catch (IOException exception) {
        }
    }

    // disattivazione servlet (salvataggio rubrica su file)
       @Override
    public void destroy() {
        try {
            synchronized(phonebook) {
                phonebook.saveBook();
            }
        }
        catch (IOException exception) {
        }
    }
    
    // richiesta GET
       @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        String number;
        String url;
        String[] url_section;
        
        // estrazione nominativo da URL
        url = request.getRequestURL().toString();
        url_section = url.split("/");
        name = url_section[url_section.length-1];
        if (name == null) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        if (name.isEmpty()) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        try {
            // ricerca nominativo nella rubrica
            synchronized(phonebook) {
                number = phonebook.getNumber(name);
            }
            // scrittura del body della risposta
            response.setContentType("text/xml;charset=UTF-8");
            
            try(PrintWriter out = response.getWriter()) {
                out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                out.println("<entry>");
                out.print("<name>");
                out.print(name);
                out.println("</name>");
                out.print("<number>");
                out.print(number);
                out.println("</number>");
                out.println("</entry>");
            }
            
            response.setStatus(200); // OK
        }
        catch (PhonebookException e) {
            response.sendError(404, "Entry not found!");
        }
    }

    // richiesta POST
       @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String line;
        BufferedWriter file;
        try (// scrittura nel file "entry.xml" del body della richiesta
                   BufferedReader input = request.getReader()) {
               file = new BufferedWriter(new FileWriter("entry.xml"));
               while ((line = input.readLine()) != null) {
                   file.write(line);
                   file.newLine();
               }
           
            file.flush();
            file.close();
        }
        catch (Exception ex){
            response.sendError(500, "File XML not created!");
        }
        
           
           
        try {
            
            // estrazione dei valori degli elementi "name" e "number" dal file "entry.xml"
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("entry.xml");
            Element root = document.getDocumentElement();
            NodeList list = root.getElementsByTagName("name");
            String name = null;
            if (list != null && list.getLength() > 0)
                name = list.item(0).getFirstChild().getNodeValue();
            list = root.getElementsByTagName("number");
            String number = null;
            if (list != null && list.getLength() > 0)
                number = list.item(0).getFirstChild().getNodeValue();
            if (name == null || number == null) {
                response.sendError(400, "Malformed XML!");
                return;
            }
            if (name.isEmpty() || number.isEmpty()) {
                response.sendError(400, "Malformed XML!");
                return;
            }
            try {
                // inserimento voce nella rubrica
                Entry entry = new Entry(name, number);
                synchronized(phonebook) {
                    phonebook.addEntry(entry);
                }
            }
            catch (PhonebookException e) {
                response.sendError(403, "Name exist!");
                return;
            }
            response.setStatus(201); // OK
        }
        catch (ParserConfigurationException | SAXException e) {
            response.sendError(500, "XML parser error!");
        }
    }

    // richiesta PUT
       @Override
    protected void doPut (HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String url_name;
        String url;
        String line;
        String[] url_section;
        
        // estrazione nominativo da URL
        url = request.getRequestURL().toString();
        url_section = url.split("/");
        url_name = url_section[url_section.length-1];
        if (url_name == null) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        if (url_name.isEmpty()) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        // scrittura nel file "entry.xml" del body della richiesta
         
        try ( BufferedReader input = request.getReader(); BufferedWriter file = new BufferedWriter(new FileWriter("entry.xml"))) {
                while ((line = input.readLine()) != null) {
                    file.write(line);
                    file.newLine();
                }
                file.flush();
          }
         catch (Exception e) {
                response.sendError(500, "File XML not created!");
                return;
            }
            
            
        try {
           
            // estrazione dei valori degli elementi "name" e "number" dal file "entry.xml"
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("entry.xml");
            Element root = document.getDocumentElement();
            NodeList list = root.getElementsByTagName("name");
            String name = null;
            if (list != null && list.getLength() > 0)
                name = list.item(0).getFirstChild().getNodeValue();
            list = root.getElementsByTagName("number");
            String number = null;
            if (list != null && list.getLength() > 0)
                number = list.item(0).getFirstChild().getNodeValue();
            if (name == null || number == null) {
                response.sendError(400, "Malformed XML!");
                return;
            }
            if (name.isEmpty() || number.isEmpty()) {
                response.sendError(400, "Malformed XML!");
                return;
            }
            if (!name.equalsIgnoreCase(url_name)) {
                response.sendError(400, "URL name mismtach XML name!");
                return;
            }
            try {
                // aggiornamento voce nella rubrica
                synchronized(phonebook) {
                    phonebook.setNumber(name, number);
                }
            }
            catch (PhonebookException e) {
                response.sendError(404, "Entry not found!");
                return;
            }
            response.setStatus(204); // OK
        }
        catch (ParserConfigurationException | SAXException e) {
            response.sendError(500, "XML parser error!");
        }
    }

    // richiesta DELETE
       @Override
    protected void doDelete (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        String name;
        String url;
        String[] url_section;

        // estrazione nominativo da URL
        url = request.getRequestURL().toString();
        url_section = url.split("/");
        name = url_section[url_section.length-1];
        if (name == null) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        if (name.isEmpty()) {
            response.sendError(400, "Request syntax error!");
            return;
        }
        try {
            // eliminazione voce dalla rubrica
            synchronized(phonebook) {
                phonebook.delEntry(name);
            }
            response.setStatus(204); // OK
        }
        catch (PhonebookException e) {
            response.sendError(404, "Entry not found!");
        }
    }

    // richiesta informazioni servlet
       @Override
    public String getServletInfo() {
        return "Phonebook";
    }
}
