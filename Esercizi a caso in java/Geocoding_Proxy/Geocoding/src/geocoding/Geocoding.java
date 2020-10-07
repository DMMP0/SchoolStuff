/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 *
 * @author dpana
 */
public class Geocoding {
    
    private String prefix = "http://maps.googleapis.com/maps/api/geocode/xml?address=";
    
    private String url;
    private boolean saved = false;
    
    public Geocoding(String address){
        
      
        BufferedReader input;
        int status;
        String line;
     
        
      
        try {
            //url="http://api.openweathermap.org/data/2.5/forecast?q=Vicenza,it&mode=xml&appid=aa221a9eeba7d5fdbbb8febc46795c65";
            //url="http://openweathermap.org/data/2.5/forecast?q=London&mode=xml&appid=b6907d289e10d714a6e88b30761fae22";
            
            url= prefix+URLEncoder.encode(address, "UTF-8");
            
            
            URLConnectionProxy conn = new URLConnectionProxy();
          
            //conn.setProxy("127.0.0.1", 7000, "user", "passowrd");
            //conn.setProxy("80.211.168.17", 3128);
            
            conn.setProxy("80.211.168.17", 3128);
                                      
            HttpURLConnection webserv=(HttpURLConnection)conn.getURLConnection(url);
            
            webserv.setRequestProperty("Accept-Encoding", "application/xml");
            webserv.setRequestProperty("Accept-Charset","UTF-8");
  
            
            webserv.setRequestMethod("GET");
            
            webserv.setDoInput(true);
            webserv.connect();
            status=webserv.getResponseCode();
            
            System.out.println("http status= "+status);
            System.out.println("http response message=" + webserv.getResponseMessage());
            System.out.println();
           // if (status==HttpURLConnection.HTTP_OK){
                input=new BufferedReader(new InputStreamReader(webserv.getInputStream(), "UTF-8"));
                while ((line=input.readLine()) != null){
                    System.out.println(line);   
           // }
           
            webserv.disconnect();
                
            }
            
         
        
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Geocoding geocoord = new Geocoding("via Gallieno 25 Vicenza Italia");
    }
    
}
