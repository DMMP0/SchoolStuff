package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.company.Main.cds;

class Connect extends Thread
{
    private Socket client = null;
    BufferedReader in = null;
    PrintStream out = null;
    public Connect() {}
    public Connect(Socket clientSocket)
    {
        client = clientSocket;
        try
        {
            in = new BufferedReader(
                    new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
        }
        catch(Exception e1)
        {
            try { client.close(); }
            catch(Exception e) { System.out.println(e.getMessage());}
            return;
        }
        this.start();
    }
    public void run()
    {
        try
        {
           ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());

           // System.out.println("Object to be written = " + cds);
            outputStream.writeObject(cds);
            //out.println("Generico messaggio per il Client");
            out.flush();
// chiude gli stream e le connessioni
            out.close();
            in.close();
            client.close();

        }
        catch(Exception e) {}
    }
}


public class Server extends Thread
{
    private ServerSocket Server;
    public static void main(String argv[]) throws Exception
    {
        new Server();
    }
    public Server() throws Exception
    {
        Server = new ServerSocket(4000);
        System.out.println("Il Server è in attesa sulla porta 4000.");
        this.start();
    }
    public void run()
    {
        while(true)
        {
            try {
                System.out.println("In attesa di Connessione.");
                Socket client = Server.accept();
                System.out.println("Connessione accettata da: "+
                        client.getInetAddress());
                Connect c = new Connect(client);

            }
            catch(Exception e) {}
        }
    }
}
