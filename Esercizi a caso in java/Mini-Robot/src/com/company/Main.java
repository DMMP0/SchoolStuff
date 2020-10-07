package com.company;

//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main
{

   public static Queue<Thread> allThreads = new ConcurrentLinkedQueue<Thread>();



    public static void main(String[] args) throws InterruptedException
    {

        Robot[] Armata = new Robot[10];
        TheGuy Suzaku_Kururugi = new TheGuy();
        LinkedList<Integer> Nei_Dintorni_Di_Suzaku_Kururugi_X = new LinkedList<Integer>();
        //me del passato, wtf
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-10);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-9);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-8);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-7);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-6);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-5);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-4);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-3);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-2);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X-1);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+1);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+2);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+3);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+4);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+5);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+6);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+7);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+8);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+9);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.X+10);
        LinkedList<Integer> Nei_Dintorni_Di_Suzaku_Kururugi_Y = new LinkedList<Integer>();
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y-10);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y-9);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y-8);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y-7);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y-6);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y-5);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y-4);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y-3);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y-2);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y-1);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y+1);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y+2);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y+3);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y+4);
        Nei_Dintorni_Di_Suzaku_Kururugi_Y.add(Suzaku_Kururugi.Y+5);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y+6);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y+7);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y+8);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y+9);
        Nei_Dintorni_Di_Suzaku_Kururugi_X.add(Suzaku_Kururugi.Y+10);
        Robot r1 = new Robot();  Armata[0] = r1;  r1.addListener(Suzaku_Kururugi);
        Robot r2 = new Robot();  Armata[1] = r2;  r2.addListener(Suzaku_Kururugi);
        Robot r3 = new Robot();  Armata[2] = r3;  r3.addListener(Suzaku_Kururugi);
        Robot r4 = new Robot();  Armata[3] = r4;  r4.addListener(Suzaku_Kururugi);
        Robot r5 = new Robot();  Armata[4] = r5;  r5.addListener(Suzaku_Kururugi);
        Robot r6 = new Robot();  Armata[5] = r6;  r6.addListener(Suzaku_Kururugi);
        Robot r7 = new Robot();  Armata[6] = r7;  r7.addListener(Suzaku_Kururugi);
        Robot r8 = new Robot();  Armata[7] = r8;  r8.addListener(Suzaku_Kururugi);
        Robot r9 = new Robot();  Armata[8] = r9;  r9.addListener(Suzaku_Kururugi);
        Robot r10 = new Robot(); Armata[9] = r10; r10.addListener(Suzaku_Kururugi);
        WorldDevouringCalamity wdc = new WorldDevouringCalamity();

        System.out.print("Pos of g : "+Suzaku_Kururugi.X+" "+Suzaku_Kururugi.Y+"\n");

        for (Thread t; (t = allThreads.poll()) != null; ) {
            System.out.print(t.getName() + "is starting\n");
            t.start();

        }
        while(true)
         for (Robot r : Armata)
         {
            if(Nei_Dintorni_Di_Suzaku_Kururugi_X.contains(r.getIntX())&& Nei_Dintorni_Di_Suzaku_Kururugi_Y.contains(r.getIntY()))
            {
                r.saveTheGuy();
            }
         }
    }
}
