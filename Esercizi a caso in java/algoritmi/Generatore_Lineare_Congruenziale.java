package com.company;

import java.util.Random;

public class Generatore_Lineare_Congruenziale extends Algoritmo_Distribuzione_Uniforme
        {
private int Xi = 1;
private int mSeed;
private int mModulo;
private int mMoltiplicatore;
private int mIncremento ;

//utilizzati per esempio 1
private double mM;
private double mA;
private double mC;
private double mXi = 1;

public int getSeed(){ return mSeed; }
 public void setSeed(int value){
        mSeed = value;
        if (mSeed > mModulo)
        mSeed=(int) Math.round( (double)(mSeed / mModulo));
        if (mSeed == 0)
           mSeed++;
        if (mSeed < 0)
           mSeed = Math.abs(mSeed);

        }

public int getM(){ return mModulo; }

       public void  setM(int value)
        {
        mModulo = value;
        if (mModulo < 0)
        mModulo = Math.abs(mModulo);
        if (mModulo == 0)
        mModulo ++;
        }

public int getA(){ return mMoltiplicatore; }
      public void   setA(int value)
        {
        mMoltiplicatore = value;
        if (mMoltiplicatore > getM())
        mIncremento = (int)Math.round((double)(mMoltiplicatore / mModulo));
        if (mMoltiplicatore < 0)
        mMoltiplicatore = Math.abs(mMoltiplicatore);
        if (mMoltiplicatore == 0)
        mMoltiplicatore++;

        }

public int getC(){ return mIncremento; }
    public void     setC(int value)
        {
        mIncremento = value;
        if (mIncremento > getM())
        mIncremento = (int)Math.round((double)(mIncremento/mModulo));
        if (mIncremento < 0)
        mIncremento = Math.abs(mIncremento);

        }


//costruttori
//base
public Generatore_Lineare_Congruenziale()
        {
        Random r = new Random();
        setM ( r.nextInt());
        setA ( r.nextInt());
        setC ( r.nextInt());
        }
//per esempio 1
public Generatore_Lineare_Congruenziale(boolean Decimal)
        {

        Random r = new Random();
        if(Decimal)
        {
        mM = (double)r.nextInt(1000)/1000 ;
        mA =  (double)r.nextInt(1000)/1000 ;
        mC = (double)r.nextInt(1000)/1000 ;

           /*      System.out.print("M =");
            System.out.print(mM.toString("N3"));
            System.out.print("\n");
            System.out.print("A =");
            System.out.print(mA.toString("N3"));
            System.out.print("\n");
            System.out.print("C =");
            System.out.print(mC.toString("N3"));
            System.out.print("\n");*/
        }
        else
        {
       setM ( r.nextInt());
       setA ( r.nextInt());
       setC ( r.nextInt());
        }
        }
public Generatore_Lineare_Congruenziale(int modulo,int moltiplicatore,int incremento)
        {
        Random r = new Random();
       setM (modulo);Max = modulo;
       setA (moltiplicatore);
       setC (incremento);
        }

//metodi utili
public void setMax(int MAX)
        {
       setM( MAX);Max = MAX;
        }
//il boolean serve perchè altrimenti non lascia generare un double, avendo la stessa intestazione
public double Genera(int min,int max,boolean Decimal)
        {
        mM = max;
        Max = max;
        Min = min;


            /*System.out.printLine("mXi = {0};\n", mXi);
            System.out.printLine("mA = {0};\n", mXi);
            System.out.printLine("mC = {0};\n", mXi);*/



        do
        {
        mXi = ((((mXi* mA)+ mC)* 1000)% (mM* 1000))/ 1000;
        }while(mXi < min);

        //System.out.printLine("mXi = {0};\n",mXi);

        return mXi;
        }

//metodi di cui fare l' override
@Override public int Genera(int Seed)
        {
        Xi = (Seed * getA() + getC()) %getM() ;
        return Xi;
        }
@Override public int Genera()
        {
        Xi = (Xi *getA() + getC()) % getM();
        return Xi;
        }
//al momento sembra non esserci una formula per assicurarsi direttamente che sia superiore al minimo, quindi lo ricalcola finche non lo è
@Override public int Genera(int min,int max)
        {
       setM(max);
        Max = max;
        Min = min;
        //da modificare
        do{
        Xi = (Xi *getA() + getC()) %getM() ;
        }while(Xi < min);

        return Xi;
        }

//descrizioni
@Override public String Descrizione()
        {
        String ris = "Il Generatore Lineare Congruenziale è un algoritmo piuttosto vecchio, semplice e computazionalmente leggero.\n " +
        "La formula ricorsiva è extends X(n+1) = [ a * X(n) + c] mod m\n" +
        "Dove:\n" +
        "\tm è il modulo ed è compreso fra 0 ed +infinito (in questo caso m = "+ getM()+"); \n" +
        "\ta è il moltiplicatore ed è compreso fra 0 ed m (in questo caso a = "+getA()+");\n" +
        "\tc è l'incrementatore ed è compreso fra 0 ed m (in questo caso c = "+getC()+");\n" +
        "\tX(0) è il seed ed è compreso fra 0 ed m (in questo caso X(0) = "+Xi+");\n" +
        "Il periodo dell' algoritmo è di m, ed è pieno solo se si verificano le seguenti condizioni:\n" +
        "\tc ed m sono coprimi (hanno come MCD 1);\n" +
        "\ta-1 è divisibile per tutti i fattori primi di m;\n" +
        "Il Generatore Lineare Congruenziale è stato usato da GCC con la funzione rand e dalla Random class di Java.\n" +
        "L'algoritmo non è efficiente e non deve essere usato dove viene richiesto un alto grado di casualità o in crittografia.\n";
        return ris;
        }
@Override public String Description()
        {
        String ris = "The linear congruential generator is an old algorithm, that is easy to reproduce and computationally light.\n " +
        "Its recursive formula is: X(n+1) = [ a * X(n) + c] mod m\n" +
        "Where:\n" +
        "\tm it's the module between 0 and +infinite (in this case m = "+ getM()+"); \n" +
        "\ta it's the multiplier between 0 and m (in this case a = "+getA()+");\n" +
        "\tc it's the increment between 0 and m (in this case c = "+getC()+");\n" +
        "\tX(0) it's the seed between 0 and m (in this case X(0) = "+Xi+");\n" +
        "The period of the algorythm is m, and it's full only if:\n" +
        "\tc and m are coprime (they have as MCD 1);\n" +
        "\ta-1 is divisible for all of m's prime factors;\n" +
        "The linear congruential generator was used by GCC with the rand function and Java's Random class.\n" +
        "It's not efficent and it shouldn't be used where a high degree of casuality is required or for cryptography.\n";
        return ris;
        }
@Override public String 叙述()
        {
        return "合目的的線状擬似乱数生成器は古くて、易しいです.\n " +
        "再帰の式は extends X(n+1) = [ a * X(n) + c] mod m\n" +

        "\tm は加群、m大なり0、m小なり無限大(今のmは "+ getM()+"です); \n" +
        "\ta は追補 、a大なり0、a小なりm(今のaは "+ getA()+"です); \n" +
        "\tc は乗数、c大なり0、c小なりm(今のcは "+ getC()+"です); \n" +
        "\tX0 は種、X0大なり0、X0小なり無限大(今のX0は "+ getSeed()+"です); \n" +
        "演算手順の周期はm。\n" +

        "合目的的線状擬似乱数生成器は GCC の rand とJavaの Random class にあります .\n" +
        "この演算手順を暗号化手法に用いないでください！\n";

        }

        } //all ok
