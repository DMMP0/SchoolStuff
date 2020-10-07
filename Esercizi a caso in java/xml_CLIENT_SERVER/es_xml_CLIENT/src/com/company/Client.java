package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

public class Client
{
    public static void main(String argv)
    {
        BufferedReader in = null;
        PrintStream out = null;
        Socket socket = null;
        String message;
        try
        {
// open a socket connection
            socket = new Socket("localhost", 4000);
// Apre i canali I/O
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
// Legge dal server
           ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());

            LinkedList<CD> LosCD = (LinkedList<CD>) inStream.readObject();
            System.out.println("Object received ");
            message = in.readLine();
            for (CD cd:LosCD
                 ) {
                System.out.println(cd.toString());
            }
            out.close();
            in.close();
        }
        catch(Exception e) { System.out.println(e.getMessage());}
    }
}
