/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserver;

/**
 *
 * @author dpana
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class EchoServer implements Runnable{ //server multithread
    private ServerSocket sSock;
    public final static int SPORT=3300; 
    
    public EchoServer() throws IOException{
      sSock = new ServerSocket();
      sSock.bind(new InetSocketAddress(InetAddress.getLoopbackAddress(), SPORT));
      sSock.setSoTimeout(1000);
    }
    
    @Override
    public void run(){
        Socket cSock=null;
        while (!Thread.interrupted()){
          try{
             cSock=sSock.accept();
             
             //gestione del client mediante thread
             Thread clientT = new Thread(new ClientEcho(cSock));
             clientT.start();
             
          }
          catch (SocketTimeoutException e){
              System.out.println(e.getMessage());
          }
          catch (IOException e){
              System.out.println(e.getMessage());
          }
          
        }
        
        try {
            sSock.close(); //chiusura del socket del server
        }
        catch(IOException e){
           System.out.println(e.getMessage()); 
        }
    }
    
    public static void main(String args[]) {
        try{
          Thread Server=new Thread(new EchoServer());
          Scanner exitKey = new Scanner(System.in);
          if ("0".equals(exitKey.nextLine())){  //digitando 0 da tastiera si interrompe il Server
              Server.interrupt();
              Server.join();
          }
        }
        catch(IOException|InterruptedException e){
            System.out.println(e.getMessage());
        }
        
    }
}

class ClientEcho implements Runnable{
    private BufferedReader input;
    private PrintWriter output;
    private Socket cSock ;
    
    ClientEcho(Socket cSo) throws IOException{
        input = new BufferedReader(new InputStreamReader(cSo.getInputStream()));
        output= new PrintWriter(cSo.getOutputStream());
        cSock= cSo;
    }
    
    @Override
    public void run(){
       try{
         String sIn= input.readLine();
         System.out.println("Ricevuto: " + sIn);
         output.println(sIn);
         output.flush();
         System.out.println("Spedito: " + sIn + "a " + cSock.getRemoteSocketAddress().toString() );
       }
       catch (IOException e){
           System.out.println(e.getMessage());
       }
       
       //chiusura socket verso client
       try{
          input.close();
          output.close();
          cSock.close();
       }
       catch(IOException e){
          System.out.println(e.getMessage()); 
       }
       
       
    }
}
