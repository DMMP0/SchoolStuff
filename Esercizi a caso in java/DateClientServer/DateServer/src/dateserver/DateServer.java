/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateserver;

/**
 *
 * @author dpana
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class DateServer extends Thread{
    private ServerSocket sSock;
    
    public DateServer(int port) throws IOException, SocketException{
        sSock = new ServerSocket();
        sSock.bind(new  InetSocketAddress(InetAddress.getLoopbackAddress(), port));
        sSock.setSoTimeout(3000);
    } 
    
    @Override
    public void run(){
        Socket cSock;
        
        while (!Thread.interrupted()){
            System.out.println("Waiting...");
            InetAddress Servadd= sSock.getInetAddress();
            System.out.println(Servadd.getHostAddress());
            try{
                
                cSock = sSock.accept(); 
                System.out.println("Richiesta pervenuta dal socket client: " + cSock.getInetAddress().toString()+ ":"+ cSock.getPort());
                
                PrintWriter sOut= new PrintWriter(cSock.getOutputStream());
                String now = (new Date()).toString();
                sOut.println(now);
                sOut.flush();
                sOut.close();  //chiusura stream di output verso il client
                
                System.out.println("Inviata ora: " + now);
                cSock.close();  //chiusura del socket verso il client gestita implicitamente
            }
            catch(SocketException e){
                
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        
        try{
            sSock.close();
        }
        catch(IOException e){
            
        }
    }
    
    public static void main(String args[]) {
      try {
        DateServer Server = new DateServer(3300);
        Server.start();
        Scanner input = new Scanner(System.in);
       
          if ("E".equals(input.nextLine().toUpperCase())){
            Server.interrupt();
            Server.join();
          }
      } 
      catch (InterruptedException|IOException e){
            
        }
    }
}
