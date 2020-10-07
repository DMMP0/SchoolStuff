package com.company;

import java.util.LinkedList;

public class Main{
    public static void main(String Args[]) throws Exception
    {
        LinkedList<Integer> l = new LinkedList<Integer>();
        Algoritmo_Casualita_Generico[] v = new Algoritmo_Casualita_Generico[5];
        v[0] = new Generatore_Lineare_Congruenziale();
        v[1] = new Lagged_Fibonacci();
        v[2] = new Blum_Blum_Shub();
        v[3] = new Registro_a_Scorrimento_a_Retroazione_Lineare();
        v[4] = new Esempio1();

        for (Algoritmo_Casualita_Generico a: v)
        {
            for(int i = 0; i<10;i++) {
                l.add(a.Genera());
            }

                System.out.print("\n"+a.叙述()+"\n");

                System.out.print("Numeri generati: ");


                    System.out.print(" "+l+"\n");


                l.clear();

                System.in.read();
            }

        }


    }





