/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geocoding;


import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
 
public class URLConnectionProxy {
     
    /**
     * Used to store the proxy settings
     */
    private Proxy proxy;
    //private String prompt;
    //private String proxyHost;
    //private int    proxyPort;
    
    
    public URLConnectionProxy(){
        super();
        proxy=null;
    }
    /**
     * 
     * Method used to add proxy settings
     *
     * @param ip
     *            the proxy IP
     * @param port
     *            the proxy port
     */
    public void setProxy(String ip, int port) {
       this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
    }
 
    /**
     * 
     * Method used to add proxy settings with authentication
     *
     * @param ip
     * @param port
     * @param username
     * @param password
     */
    public void setProxy(String ip, int port, String username, String password) {
        //imposta il Proxy attraverso cui passare indicando il tipo 
    
      this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
      Authenticator authenticator;
      
      authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                /* Seconda soluzione:
                  eseguire dal prompt dei comandi il setup dei parametri per la JVM:
                  https://stackoverflow.com/questions/1626549/authenticated-http-proxy-with-java
                  java -Dhttp.proxyHost=myproxy -Dhttp.proxyPort=myport
                  per poter autilizzare i metodi della classe Authenticator per ottenere:
                    final String prompt=getRequestingPrompt();
                    final String proxyHost= getRequestingHost();
                    final String proxyPort= getRequestingPort();
                */
               /* Terza soluzione
                 se si è impostato da prompt dei comandi 
                 java -Dhttps.proxyUser=myuser -Dhttps.proxyPassword=mypass
                 si possono utilizzare:
                  final String proxyUser     = System.getProperty("http.proxyUser");
                  final String proxyPassword = System.getProperty("http.proxyPassword");
                 
                 analogamente, se si sono impostati dal prompt dei comandi:
                 java -Dhttp.proxyHost=myproxy -Dhttp.proxyPort=myport
                 
                 si possono utilizzare i comandi:
                  final String host = System.getProperty(prot + ".proxyHost", "");
                  final String port = System.getProperty(prot + ".proxyPort", "80");

                */ 
               

                return (new PasswordAuthentication(username, password.toCharArray()));
            }
        };
      
      Authenticator.setDefault(authenticator);
      //this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
       
     }
 
    /**
     * The method will return the a URLConnection instance
     * 
     * @param domain
     *            The Domain name
     * 
     * @return the connection as an URLConnection object
     * @throws java.net.MalformedURLException
     */
   public URLConnection getURLConnection(String domain) throws MalformedURLException, IOException  {
 
     URL url = new URL(domain);
     URLConnection connection; 
     if (this.proxy != null)
        connection = url.openConnection(this.proxy);
     else
        connection = url.openConnection();
    
     return connection;
    }
 
   public static void main(String[] args) throws Exception {
    System.out.println("URLConnection with Proxy Example");
    System.out.println();
 
    
    URLConnectionProxy con = new URLConnectionProxy();
 
    /**
     * activate this line if you are behind a proxy server - change the settings
     * accordingly
     */
    //con.setProxy("127.0.0.1", 7000);
 
    /**
     * activate this line if you are behind a proxy server with authentication -
     * change the settings accordingly
     */
    con.setProxy("127.0.0.1", 7000, "user", "passowrd");
     
    }
}