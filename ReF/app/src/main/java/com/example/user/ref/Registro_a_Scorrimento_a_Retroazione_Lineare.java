package com.example.user.ref;

import java.util.Random;

public class Registro_a_Scorrimento_a_Retroazione_Lineare extends Algoritmo_Distribuzione_Uniforme
        {
        Random r = new Random();
        boolean[] bits;

//descrizioni
@Override public String Descrizione()
        {
        return "Il registro a scorrimento a retroazione lineare è una tipologia di registri di traslazione i cui dati in ingresso sono prodotti da una funzione lineare dello stato interno.\n" +
        "Le uniche funzioni lineari di singoli bit sono lo XOR e lo XNOR ;\n" +
        "perciò è un registro di traslazione i cui bit in ingresso sono prodotti dallo xor di alcuni bit memorizzati all'interno dei registri.\n" +
        "La lista di posizioni dei bit che influenza lo stato successivo è detta sequenza di tap.\n" +
        "Le uscite che influenzano l'ingresso sono dette tap.\n" +
        "Un LFSR massimale produce una sequenza-n (cioè passa attraverso tutti i possibili stati del registro di traslazione tranne quello che produce tutti zeri),\n" +
        "a meno che il suo stato iniziale non sia composto solo di zeri, nel qual caso l'uscita resta costante.\n" +
        "La sequenza di tap di un LFSR può essere rappresentata come un polinomio modulo 2. Questo significa che i coefficienti del polinomio devono essere 1 o 0.\n" +
        " Questo è noto come polinomio di retroazione o polinomio caratteristico.\n" +
        "Proprietà:\n" +
        "\t- Se (e solo se) questo polinomio è primitivo, allora l'LFSR è massimale\n" +
        "\t- L'LFSR sarà massimale solo se il numero di tap è pari\n" +
        "\t- I valori dei tap in un LFSR massimale saranno primi tra loro\n" +
        "\t- Ci può essere più di una sequenza di tap che rende massimale un LFSR di lunghezza fissata\n" +
        "\t- Una volta trovata una sequenza di tap massimale, se ne può trovare un'altra con un procedimento automatico: se la sequenza di tap,\n" +
        "\t  in un LFSR a n bit, è [n,A,B,C], la sua sequenza \"speculare\" è [n,n-A,n-B,n-C] (ad esempio la sequenza [32,3,2,1] ha come controparte [32,31,30,29]).\n" +
        "\t  Entrambe producono un LFSR massimale." +
        "Proprietà della sequenza di uscita:\n" +
        "\t- Uni e zeri si susseguono(runs).\n" +
        "\t  La sequenza di uscita 0110100, ad esempio, è composta da cinque corse di lunghezza 1,2,1,1,2, rispettivamente.\n" +
        "\t  In un periodo di un LFSR massimale, appaiono 2^{n-1} corse (ad esempio, un LFSR a 6 bit avrà 32 corse);\n" +
        "\t  esattamente 1/2 di queste corse saranno di un bit, 1/4 di 2 bit, fino ad un'unica corsa di n-1 bit a zero,\n" +
        "\t  ed un'unica corsa di n bit a uno.\n" +
        "\t- Le sequenze di uscita degli LFSR sono deterministiche: se si conosce lo stato attuale, si può prevedere il prossimo. \n" +
        "Gli LFSR possono essere implementati in hardware, e ciò li rende utili in applicazioni che richiedono la generazione molto rapida di numeri pseudo-casuali,\n" +
        "come nella tecnica radio Direct Sequence Spread Spectrum, usata ad esempio nell'UMTS.\n"+
        "Il GPS usa gli LFSR per trasmettere rapidamente una sequenza che indica degli istanti relativi ad alta precisione,\n" +
        "sfruttandone il determinismo: basta infatti trasmettere il seme utilizzato nel trasmettitore e la sequenza generata sarà identica anche sul ricevitore.\n";


        }
@Override public String Description()
        {
        return "The linear-feedback shift register is a type of shift register where the input data is produced from a linear function of the internal state.\n" +
        "The only linear function in bit are XOR and XNOR ;\n" +
        "meaning that it's shift register where the input bits are produced by the xor of some of the memorized bits within the registers.\n" +
        "The bit's position list that influcences the next sequence is called sequence tap.\n" +
        "The outputs that influence the inputs are called tap.\n" +
        "A maximal LFSR produces an n-sequence (aka. it passes through all of the possible shift registers except the one that produces all zeros),\n" +
        "unless the initial state is all zeros, in that case the exit state remains the same.\n" +
        "The tap sequence of a LFSR can be represented as a module 2 polynomial. This means that the polinom's coefficients must be either 1 or 0.\n" +
        "This is known as a retroactive polynom.\n" +
        "Properties:\n" +
        "\t- If (and only if) this is a primitive polynom, then the LFSR is maximal\n" +
        "\t- The LFSR is maximal only if the tap count is even\n" +
        "\t- The tap values of a maximal LFSR are coprime\n" +
        "\t- There can be more than one tap sequence that makes maximal a fixed length LFSR \n" +
        "\t- Once found the maximal tap sequence, another one can be fount using an automatic procedure: if the sequence,\n" +
        "\t  in a n bit LFS, is [n,A,B,C], it's \"specular\" sequence is [n,n-A,n-B,n-C] (for example the sequence [32,3,2,1] has as its counterpart [32,31,30,29]).\n" +
        "\t  Both produce a maximal LFSR." +
        "Properties of the exit sequence:\n" +
        "\t- Ones and zeros follow one another (runs).\n" +
        "\t  The exit sequence 0110100, for example, is made of five runs, each having length 1,2,1,1,2.\n" +
        "\t  In a maximal LFSR period, there are 2^{n-1} runs (e.g., a 6 bit LFSR has 32 runs);\n" +
        "\t  and exactly 1/2 of those runs have one bit, 1/4 have 2 bit, untli the last run of zero n-1 bits,\n" +
        "\t  and one run of une n bits.\n" +
        "\t- LFSR's exit sequences are deterministic: if the current state is known, the next one can be forecasted. \n" +
        "LFSR's can be implemented in hardware, and that makes them useful in applications that reqiure a fast pseudo-rundom number generation,\n" +
        "as in the r Direct Sequence Spread Spectrum radio technique, used in theUMTS.\n"+
        "The GPS used LFSR to quicly send a sequence equal to high-precision relative istances,\n" +
        "taking advantage of it's determinism: that's because you only need to transmit the used seed to the transmitter since the generated sequence will be the same on the receiver .\n";



        }
@Override public String 叙述()
        {

        return "線状視野移動遡及的登記簿のインプットはインターナルステータスのアウトプットです.\n" +
        "式は XOR と XNOR だけです;\n" +
        "インプットのビットは レギスターのビットです。\n" +
        "意義深いビットは「タップ」です。\n" +
        "LFSRに一番大きなの一連はN "+
        "タップの列は二グレードポリノムですから,ポリノムのコエフィチアンツはゼロです,一です\n" +
        " これは言うところの遡及的ポリノムです \n" +
        "ハードウェアのイムプレゼントすることができますから、迚速い擬似乱数生成器にイムプレゼントすることは便利です\n" +
        "\t例:レジオの術,直接拡散方式(DSSS)\n"+
        "GPSも線状視野移動遡及的登記簿を用います" ;

        }




public Registro_a_Scorrimento_a_Retroazione_Lineare()
        {


        StringBuilder sbr = new StringBuilder();

        for(int i=0; i<10;i++)
        {
        sbr.append( r.nextInt(2) );
        }
        String  seed = sbr.toString();
        // System.out.printLine("Il seed è "+seed);  ok


        bits = new boolean[10];

        for (int i = 0; i < 10; i++)
        bits[i] = seed.charAt(i) == '1'; //if seed[i] == 1 true, else false


        }  //nel costruttore di base riempie l' array con una sequenza casuale di 0 e 1

public Registro_a_Scorrimento_a_Retroazione_Lineare( String seed)
{
        bits = new boolean[seed.length()];

        for (int i = 0; i < seed.length(); i++) {
                bits[i] = seed.charAt(i) == '1'?true:false; //if seed[i] == 1 true, else false

        }//quì trascrive la Stringa
}





public String getRegistry()
        {
        char[] t = new char[bits.length];
        for (int i = 0; i < bits.length; i++)
        t[i] = bits[i] ? '1' : '0';

        return new String(t);
        }
         // quando si fa il get del registro, esso prende i valori del bit array e li trasforma in Stringa



public void Shift()
        {

        boolean bnew = !(bits[bits.length - 1] == bits[bits.length - 2]);

        for (int i = bits.length - 1; i > 0; i--)
        {
        bits[i] = bits[i - 1];
        }
        bits[0] = bnew;
        }//come da nome, shifta


                @Override
                public int Genera(int min, int max) {
                        return 0;
                }

                @Override public int Genera(int Seed)
        {



        int period = 0;
        int ris=0;

        do
        {

        Shift();
        period++;

        } while (period <= Seed );

        //   System.out.printLine(Registry);

        for (int j = 0; j < bits.length; j++)
        {
        if (bits[j])
        ris += (int)(Math.pow(2, bits.length - (j + 1)));
        }
        return ris;

        }

@Override public int Genera()
        {
        int i = r.nextInt( 31);
        return Genera(i);


        }




        }
