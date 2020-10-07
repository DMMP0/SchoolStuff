package com.example.user.ref;

public abstract class Algoritmo_Distribuzione_Uniforme extends Algoritmo_Casualita_Generico
        {
// private boolean Real = true;
public int Max;
public int Min;

                public abstract int Genera(int min, int max);
                public abstract int Genera(int Seed) ;

@Override
public  String Descrizione()
        {
        String ris = "Esistono diversi tipi di algoritmi di generazione distribuita di numeri pseudo casuali.\n " +
        "Quelli implementati in questa applicazione al momento sono:\n" +
        "\tGeneratore Lineare Congruenziale;\n" +
        "\tGeneratore di Fibonacci Ritardato(Lagged Fibonacci);\n" +
        "\tRegistro a scorrimento a retroazione lineare;\n" +
        "\nBlum Blum Shub;\t" +
        "";

        return ris;
        }
        @Override
public  String Description()
        {
        String ris = "There are different types of distributed generation algorithms for pseudo-random numbers.\n " +
        "The ones implemented in this application are:\n" +
        "\tLinear congruential generator;\n" +
        "\tLagged Fibonacci generator;\n" +
        "\tLinear-feedback shift register;\n" +
        "\nBlum Blum Shub;\t" +
        "";
        return ris;
        }
        @Override
public String 叙述()
        {
        String ris = "今日の世界にたくさん分散配置擬似乱数生成器があります.\n " +
        "このアップに四があります:\n" +
        "\t合目的的線状擬似乱数生成器;\n" +
        "\t遅れフィボナッチの探索擬似乱数生成器\n" +
        "\t線状視野移動遡及的登記簿\n" +
        "\nブラムブラムシャブ;\t" +
        "";

        return ris;
        }
        }//all ok
