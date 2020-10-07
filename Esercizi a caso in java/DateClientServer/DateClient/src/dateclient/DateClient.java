/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateclient;

/**
 *
 * @author dpana
 */
import java.net.*;
import java.io.*;

public class DateClient {
    private InetAddress sAdd;  //server address
    private int sPort;         //server port
    
    public DateClient(InetAddress serverAdd, int serverPort){
      sAdd=serverAdd;
      sPort=serverPort;
    }
    
    public String getServerDate(){
        Socket cSock=null;
        BufferedReader bIn=null;
        String sDate=null;
        
        try {  
          cSock = new Socket(sAdd, sPort); //crea socket sul Client e lo connette al server
          bIn = new BufferedReader(new InputStreamReader(cSock.getInputStream()));
          sDate= bIn.readLine();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        try{
            cSock.close();
        }
         catch(IOException e){
            System.out.println(e.getMessage());
        }
        
        return sDate;
      
    }
    public static void main(String args[]) {
       DateClient client = new DateClient(InetAddress.getLoopbackAddress(), 3300);
       
       System.out.println("Data fornita dal Server:" + client.getServerDate());
    
    }
}
