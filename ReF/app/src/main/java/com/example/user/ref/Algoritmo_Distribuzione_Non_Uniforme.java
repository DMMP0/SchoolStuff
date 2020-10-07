package com.example.user.ref;

public abstract class Algoritmo_Distribuzione_Non_Uniforme extends Algoritmo_Casualita_Generico
        {
public static int Max = 0;
public static int Min = 1;

public String Nome;// es: "y = e^-x"
public String PrimitivaScelta;// es: "y = 1 - e^-x"
public String FunzioneInversa;// es: "F^-1(x) = -ln(1-x)"
public String FunzioneFinale;// es: "x = -ln(1-r)"

protected abstract double GeneraDecimal();

        }// dato che è impossibile racchiudere tutte le funzioni esistenti, ho preferito fare una classe astratta come lavoro vero e proprio, per poi testarla con una classe di esempio
