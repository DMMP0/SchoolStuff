package com.company;

import java.util.Random;

public class Lagged_Fibonacci extends Algoritmo_Distribuzione_Uniforme
        {
private Random rand ;
private int[] array;
private int j ;
private static int k = 258;
private int m ;
private int n ;
private double randomNumber;

//costruttori
public Lagged_Fibonacci()
        {
        rand = new Random();
        array = new int[6000];
        j = 83;

        m = 2;
        n = k;
        randomNumber = 0;

        }
public Lagged_Fibonacci(int J,int M)
        {
        rand = new Random();
        array = new int[6000];
        j =J;

        m = M;
        n = k;
        randomNumber = 0;

        }

//metodi di cui fare l'override
@Override public int Genera(int min, int maxValue)
        {
        for (int i = 0; i < array.length; i++)
        {
        array[i] = rand.nextInt(maxValue);
        }

        int firstElement = array[n - j];
        int secondElement = array[n - k];

        randomNumber = (firstElement + secondElement) % Math.pow(m, 32);

        array[n] = (int)randomNumber;
        int ris = (int)randomNumber % (maxValue + 1);
        return (int)randomNumber % (maxValue + 1);



        }
@Override public int Genera()
        {
        return Genera(0,600);
        }

                @Override public int Genera(int max)
                {
                        return Genera(0,max);
                }

//descrizioni
@Override public String Descrizione()
        {
        return "Il generatore di Fibonacci ritardato è un algoritmo per la generazione di numeri pseudo-casuali basato su una generalizzazione della Successione di Fibonacci.\n" +
        " Dalla definizione della successione di Fibonacci:\n" +
        "il generatore è definito come F(n) = [ F(n-j) @ F(n-k) ] mod m, con 0 < j < k <= n \n" +
        "Dove:\n" +
        "\tF(n) è l' ennesimo termine della successione\n" +
        "\tF(n-j) ed F(n-k) sono due termini qualsiasi della successione precedenti ad F(n)\n" +
        "\t@ è una qualsiasi operazione binaria(+,-,*,/,XOR,AND,ecc.)\n" +
        "In questo caso, i valori utilizzati sono:\n" +
        "\tj = "+j+"\n" +
        "\tk = "+k+"\n" +
        "\tn = "+n+"\n" +
        "Proprietà:\n" +
        "\t- Come tutti i generatori di numeri pseudo-casuali, il generatore di Fibonacci ritardato è una funzione periodica.\n" +
        "\t- Il periodo massimo varia a seconda dell'operatore usato. Nel caso di somma o sottrazione, il generatore ha periodo p tale che\n" +
        "\t  2^{k-1}  *  2^{m-1}\n" +
        "\t  In caso di moltiplicazione invece\n" +
        "\t  2^{k-1}  *  2^{m-3}\n" +
        "\t  Il periodo della moltiplicazione è un quarto di quello della somma.\n";

        //vedere in seguito se è il caso di aggiungere le proprietà
        }
@Override public String Description()
        {

        return "The lagged Fibonacci generator is an algorithm for pseudo-random number generation based on a generalisation of the Fibonacci sequence.\n" +
        "From the definition of the Fibonacci sequence:\n" +
        "the generator is defined as F(n) = [ F(n-j) @ F(n-k) ] mod m, with 0 < j < k <= n \n" +
        "Where:\n" +
        "\tF(n) is the last term of the succession\n" +
        "\tF(n-j) and F(n-k) are any of the two previous terms of the succession\n" +
        "\t@ is a binary operator(+,-,*,/,XOR,AND,ecc.)\n" +
        "In this case, it uses:\n" +
        "\tj = "+j+"\n" +
        "\tk = "+k+"\n" +
        "\tn = "+n+"\n" +
        "Properties:\n" +
        "\t- As all pseudo-random number generators, the lagged Fibonacci generator is a periodic function.\n" +
        "\t- The maximum period varies depending on the operator used. In case of a sum or subtraction, the generator has the period equal to p such that\n" +
        "\t  2^{k-1}  *  2^{m-1}\n" +
        "\t  If it's a multiplication instead\n" +
        "\t  2^{k-1}  *  2^{m-3}\n" +
        "\t  The period of the multiplication is a quarter of the one of the sum.\n";
        }
@Override public String 叙述()
        {
        return "これは遅れフィボナッチの探索の擬似乱数生成器.\n" +
        "擬似乱数生成器の式は F(n) = [ F(n-j) @ F(n-k) ] mod m,、j大なり0、j大なりk、n大なりk\n" +

        "\tF(n) は最後の値\n" +
        "\tF(n-j) と F(n-k)はこの遅れフィボナッチの探索の上値\n" +
        "\t@ は二項演算子(+,-,*,/,XOR,AND,ecc.)\n" +
        "今のjは "+ j+"です\n"+
        "今のkは "+ k+"です\n"+
        "今のnは "+ n+"です\n"+
        "遅れフィボナッチの探索は定期的式です\n"+
        "＋の周期の範囲は 2^{k-1}  *  2^{m-1}です\n"+
        "×の周期の範囲は 2^{k-1}  *  2^{m-3}です\n"+
        "×の周期の範囲は「＋の周期の範囲」分の4です";

        }


        } //descrizione ita ok, jp ok, eng circa
