package robot;

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
        
        TheGuy Suzaku_Kururugi = new TheGuy(Float.parseFloat(args[0]),Float.parseFloat(args[1]));
        int Nei_Dintorni_Di_Suzaku_Kururugi_X = 10;        
        int Nei_Dintorni_Di_Suzaku_Kururugi_Y = 10;
        int i = 2;
        for(Robot r : Armata)
        {
            r = new Robot(Suzaku_Kururugi),Float.parseFloat(args[i]),args[i+1];
        }
        
        Robot r1 = new Robot(Suzaku_Kururugi);  Armata[0] = r1;  r1.addListener(Suzaku_Kururugi);
        Robot r2 = new Robot(Suzaku_Kururugi);  Armata[1] = r2;  r2.addListener(Suzaku_Kururugi);
        Robot r3 = new Robot(Suzaku_Kururugi);  Armata[2] = r3;  r3.addListener(Suzaku_Kururugi);
        Robot r4 = new Robot(Suzaku_Kururugi);  Armata[3] = r4;  r4.addListener(Suzaku_Kururugi);
        Robot r5 = new Robot(Suzaku_Kururugi);  Armata[4] = r5;  r5.addListener(Suzaku_Kururugi);
        Robot r6 = new Robot(Suzaku_Kururugi);  Armata[5] = r6;  r6.addListener(Suzaku_Kururugi);
        Robot r7 = new Robot(Suzaku_Kururugi);  Armata[6] = r7;  r7.addListener(Suzaku_Kururugi);
        Robot r8 = new Robot(Suzaku_Kururugi);  Armata[7] = r8;  r8.addListener(Suzaku_Kururugi);
        Robot r9 = new Robot(Suzaku_Kururugi);  Armata[8] = r9;  r9.addListener(Suzaku_Kururugi);
        Robot r10 = new Robot(Suzaku_Kururugi); Armata[9] = r10; r10.addListener(Suzaku_Kururugi);
        WorldDevouringCalamity wdc = new WorldDevouringCalamity();

        System.out.print("Pos of g : "+Suzaku_Kururugi.X+" "+Suzaku_Kururugi.Y+"\n");

        for (Thread t; (t = allThreads.poll()) != null; ) {
           // System.out.print(t.getName() + "is starting\n");
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
