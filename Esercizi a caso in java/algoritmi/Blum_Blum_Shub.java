package com.company;

import java.util.Random;

public class Blum_Blum_Shub extends Algoritmo_Distribuzione_Uniforme
{
    private int mP;
    private int mQ;
    private int xi;
    private int mSeed;
    private final int i = 10;
    private int[] zi;

    public int getP(){ return mP; }
    public void setP(int value)
    {
        mP = value;
        if (mP < 0)
            mP = Math.abs(mP);
        while (mP % 4 != 3 && !IsPrime(mP))
            mP++;

    }
    //verificare se p e q sono primi!
    public int getQ(){ return mQ; }
    public void    setQ(int value)
    {
        mQ = value;
        if (mQ < 0)
            mQ = Math.abs(mP);
        while ((mQ % 4 != 3) && (!IsPrime(mQ)))
            mQ++;

    }

    public int getSeed (){ return mSeed; }
    public void setSeed(int value) throws Exception {
        mSeed = value;
        int n = getP() * getQ();
        while (mSeed > (n - 1))
        {
            // System.out.printLine("Il seed è maggiore di n, ricalcolo");
            mSeed--;

        }

        if (!Coprimo(mSeed, n))
        {
            // System.out.printLine("Il seed non è coprimo, ricalcolo");
            mSeed = n-2;
            while(!Coprimo(mSeed, n))
            {
                mSeed--;
                if(mSeed == 0)
                    throw new Exception("Non esiste un possibile seed per questi valori di p = "+getP()+" e q = "+getQ());
            }

        }

        // System.out.printLine("Il seed è coprimo, ok: {0}",mSeed);
    }


    public Blum_Blum_Shub() throws Exception {
        Random r = new Random();
        setP (r.nextInt(1000));
        setQ (r.nextInt(1000));
        setSeed (r.nextInt(getP() * getQ()));
        xi =0;
        zi = new int[i];
    }
    public Blum_Blum_Shub(int P, int Q) throws Exception {
        Random r = new Random();
        setP (P);
        setQ (Q);
        setSeed  (r.nextInt(getP() * getQ()));
        xi =0;
        zi = new int[i];
    }
    public Blum_Blum_Shub(int P, int Q, int seed) throws Exception {
        setP(P);
        setQ(Q);
        setSeed(seed);
        xi =0;
        zi = new int[i];
    }

    @Override public String Descrizione()
    {
        return "L'algoritmo del Blum Blum Shub utilizza 4 variabili: q, p, n ed un seed.\n"+
                "\tp e q sono due numeri primi,\n\tn è il prodotto di p e q,\n\til seed è un numero coprimo e minore di n.\n"+
                "il valore inziale è dato dal seed elevato alla seconda modulo n (X0 = seed ^ 2 mod n),\n"+
                "per trovare gli X successivi si applica la formula precedente con l' x corrente(X(n) = X(n-1)^2 mod n\n)"+
                "per ogni X(n) si prende il bit meno significativo e lo inserisce in un vettore di bit.\n"+
                "Il risultato sarà dato dalla conversione in intero del numero binario contenuto nell array\n"+
                "Es: con n=5, numero binario risultante = 10001, risultato = 17";
    }
    @Override public String Description()
    {
        //TODO
        return "L'algoritmo del Blum Blum Shub utilizza 4 variabili: q, p, n ed un seed.\n"+
                "\tp e q sono due numeri primi,\n\tn è il prodotto di p e q,\n\til seed è un numero coprimo e minore di n.\n"+
                "il valore inziale è dato dal seed elevato alla seconda modulo n (X0 = seed ^ 2 mod n),\n"+
                "per trovare gli X successivi si applica la formula precedente con l' x corrente(X(n) = X(n-1)^2 mod n\n)"+
                "per ogni X(n) si prende il bit meno significativo e lo inserisce in un vettore di bit.\n"+
                "Il risultato sarà dato dalla conversione in intero del numero binario contenuto nell array\n"+
                "Es: con n=5, numero binario risultante = 10001, risultato = 17";
    }

    @Override public String 叙述()
    {
        return "ブラムブラムシャブの演算手順のインプットは q, p, n ,seed.\n"+
                "\tpとqは素数,\n\tnはpかけるq,\n\tseedは nの互いに素,seed小なりn\n"+
                "X0は seed の 2 乗 mod n です,\n"+
                "X(n) は X(n-1)の 2 乗 mod nです\n)"+
                "そして,X(n)の最小有効ビットをビットの同位列にインサート\n"+
                "アウトプットはビットの同位列の整数値です\n"+
                "例\n\tnは5です,ビットの同位列は10001,アウトプットは17";


    }

    @Override public int Genera() {
        try {
            return Genera(mSeed,mP,mQ);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override public int Genera(int P, int Q)
    {
        Random r = new Random();

        //p e q sono due numeri primi

        setP(P);
        setQ(Q);

        // n = p x q
        int n = getP() * getQ();

        // il seed è un numero casuale coprimo e minore di n

        int ris= 0;
        try {
            ris = Genera(getSeed(),getP(),getQ());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return ris;


    }

    @Override
    public int Genera(int Seed) {
        return 0;
    }

    public int Genera(int seed, int P, int Q) throws Exception {


        //p e q sono due numeri primi

        setP(P);
        setQ(Q);

        // n = p x q
        int n = getP() * getQ();

        // il seed è un numero casuale coprimo e minore di n
        setSeed(seed);


        // x0 è seed^2 mod n


       // System.out.print("X0 = "+ xi+"\n");

        //per j che va da 1 a i
        for (int j = 0; j < i; j++)
        {
            // xi = x(i-1) ^ 2 mod n
               /* System.out.print("xi^2 = {0}", Math.pow(xi, 2)+"\n");
                System.out.print("xi^2 mod n = {0}", Math.pow(xi, 2) % n+"\n");*/
            if(xi < 0)
                System.out.print("Xi è negativo: "+ xi);
            if(xi == 0)
                xi = (int)((Math.pow(getSeed(), 2) )% n);
            else
            {
                xi = (int)(Math.pow(xi,2)% n);
                // System.out.print("Dopo aver svolto l'operazione xi ={0}", xi+"\n");
            }
                /*if(xi < 0)
                    System.out.print("C'è un problema nella potenza: {0}",xi+"\n");*/

            //si prende il bit meno significativo di xi
            zi[j] = xi %2;
               /* if(zi[j] < 0)
                    System.out.print("C'è un problema nel modulo");*/


           // System.out.print(zi[j]+" ");

        }

        //si converte il bit array in int  (per comodità è stato realizzato un array di int in cui può esser messo solo 0 ed 1)

        int ris = 0;

        for (int j = 0; j < i; j++)
        {
            if (zi[j] == 1)
                ris += (int)(Math.pow(2, i-(j+1)));
        }

        return ris;
    }



    // utili
    private static int MCD(int value1, int value2)
    {
        while (value1 != 0 && value2 != 0)
        {
            if (value1 > value2)
                value1 %= value2;
            else
                value2 %= value1;
        }
        return Math.max(value1, value2);
    }
    private static boolean Coprimo(int value1, int value2)
    {
        return MCD(value1, value2) == 1;
    }
    private static boolean IsPrime(int number)
    {
        if (number < 2) return false; //se è minore di 2 (1,0 o negativo) non è primo
        if (number % 2 == 0) return false; //se è divisibile per 2 non è primo
        int root = (int)Math.sqrt((double)number);
        for (int i = 3; i <= root; i += 2)
        {
            if (number % i == 0) return false; //ha trovato un numero per cui è divisibile
        }//calcolo effettivo per vedere se è primo: parte da i = 3 e passa tutti i numeri dispari fino a superare la radice.
        return true;
    }


}