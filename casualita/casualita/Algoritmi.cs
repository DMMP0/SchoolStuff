using System;
using System.Collections;
using System.Text;

namespace Casualita
{
    public abstract class Algoritmo_Casualita_Generico
    {

        public virtual int Genera(int min, int max) { throw new NotImplementedException(); }
        public virtual int Genera(int Seed) { throw new NotImplementedException(); }
        public abstract int Genera(); 
        public abstract string Descrizione();
        public virtual string Description(){return ""};
        public virtual string 叙述(){return "";}
    }

    public abstract class Algoritmo_Distribuzione_Uniforme :Algoritmo_Casualita_Generico
    {
       // private bool Real = true;
        public int Max;
        public int Min;

        public override string Descrizione()
        {
            string ris = "Esistono diversi tipi di algoritmi di generazione distribuita di numeri pseudo casuali.\n " +
                "Quelli implementati in questa applicazione al momento sono:\n" +
                "\tGeneratore Lineare Congruenziale;\n" +
                "\tGeneratore di Fibonacci Ritardato(Lagged Fibonacci);\n" +
                "\tRegistro a scorrimento a retroazione lineare;\n" +
                "\nBlum Blum Shub;\t" +
                "";

            return ris;
        }
        public override string Description()
        {
            string ris = "There are different types of distributed generation algorithms for pseudo-random numbers.\n " +
                "The ones implemented in this application are:\n" +
                "\Linear congruential generator;\n" +
                "\tLagged Fibonacci generator;\n" +
                "\tLinear-feedback shift register;\n" +
                "\nBlum Blum Shub;\t" +
                "";
            return ris;
        }
        public override string 叙述()
        {
            string ris = "今日の世界にたくさん分散配置擬似乱数生成器があります.\n " +
                "このアップに四があります:\n" +
                "\t合目的的線状擬似乱数生成器;\n" +
                "\t遅れフィボナッチの探索擬似乱数生成器\n" +
                "\t線状視野移動遡及的登記簿\n" +
                "\nブラムブラムシャブ;\t" +
                "";

            return ris;
        }
    }//all ok

    public abstract class Algoritmo_Distribuzione_Non_Uniforme : Algoritmo_Casualita_Generico
    {
        public static int Max = 0;
        public static int Min = 1;

        public string Nome;// es: "y = e^-x"
        public string PrimitivaScelta;// es: "y = 1 - e^-x"
        public string FunzioneInversa;// es: "F^-1(x) = -ln(1-x)"
        public string FunzioneFinale;// es: "x = -ln(1-r)"
        
       protected abstract decimal GeneraDecimal();
        
    }// dato che è impossibile recchiudere tutte le funzioni esistenti, ho preferito fare una classe astratta come lavoro vero e proprio, per poi testarla con una classe di esempio

    public class Esempio1 : Algoritmo_Distribuzione_Non_Uniforme
    {

        Generatore_Lineare_Congruenziale glc = new Generatore_Lineare_Congruenziale(true);

        //costruttore
        public Esempio1()
        {
            Max = 1000;
            Nome = "y = e^-x";
            PrimitivaScelta = "y = 1 - e^-x";
            FunzioneInversa = "F^-1(x) = -ln(1-x)";
            FunzioneFinale = " x = -ln(1-r)";

        }

        //metodi ci cui fare l' override
        public override string Descrizione()
        {
            return "Questo è un esempio di algoritmo di distribuzione non uniforme.\n"+
            "La funzione iniziale è "+Nome+";\n"+
            "La primitiva scelta è "+PrimitivaScelta+";\n"+
            "La funzione inversa della primitiva scelta è "+FunzioneInversa+";\"+
            "La funzione finale esplicitata in x è quindi "+FunzioneFinale+";\n"+
            "Prendendo come input un numero decimale generato dal GLC,\nper restituire il numero finale si prendono in considerazione le cifre meno significative" ;
        }
        public override string Description()
        {
            return "This is an example of a non-uniform distributed algorithm .\n"+
            "The initial function is "+Nome+";\n"+
            "The primitive is "+PrimitivaScelta+";\n"+
            "The inverted function of the primitive is "+FunzioneInversa+";\"+
            "The final function explicated in x is "+FunzioneFinale+";\n"+
            "Taking as input a decimal number generated by GLC,\nthe less significant digits are taken into consideration to get the final number" ;
        }
        public override string 叙述()
        {
            return "これは偏在の擬似乱数生成器の例\n"+
            "初めの式は "+Nome+";\n"+
            "インテグラルは "+PrimitivaScelta+";\n"+
            "インテグラルのf,xの逆関数は"+FunzioneInversa+";\"+
            "終わりのxの式は "+FunzioneFinale+";\n"+
            "インプットは合目的的線状擬似乱数生成器の十進数アウトプットです。\n終わりのアウトプットは十進数アウトプットの最下位値" ;

        }

        //altri metodi
        protected override decimal GeneraDecimal()
        {
            
            decimal r = glc.Genera(0, 1, true);
            return decimal.Negate((decimal)(Math.Log((1 -(double) r))));
        }
        public override int Genera()
        {
            int ris;
            do{
            ris =  ((int)Math.Round(((GeneraDecimal()*100000000000000)%10000)))%Max;
            }while(ris < Min);
            return ris;
        }
    } //all ok
    

    public class Generatore_Lineare_Congruenziale : Algoritmo_Distribuzione_Uniforme
    {
        private int Xi = 1;
        private int mSeed;
        private int mModulo;
        private int mMoltiplicatore;
        private int mIncremento ;
        
        //utilizzati per esempio 1
        private decimal mM;
        private decimal mA;
        private decimal mC;
        private decimal mXi = 1;

        public int Seed
        {
            get { return mSeed; }
            set {
                mSeed = value;
                if (mSeed > mModulo)
                  mSeed=(int) Math.Round( (decimal)(mSeed / mModulo));
                if (mSeed == 0)
                    Seed++;
                if (mSeed < 0)
                    mSeed = Math.Abs(Seed);
                
                }
        }
        public int M//modulo
        {
            get { return mModulo; }
            set
            {
                mModulo = value;
                if (mModulo < 0)
                    mModulo = Math.Abs(mModulo);
                if (mModulo == 0)
                    mModulo ++;
            }
        }
        public int A//moltiplicatore
        {
            get { return mMoltiplicatore; }
            set
            {
                mMoltiplicatore = value;
                if (mMoltiplicatore > M)
                    mIncremento = (int)Math.Round((decimal)(mMoltiplicatore / mModulo));
                if (mMoltiplicatore < 0)
                    mMoltiplicatore = Math.Abs(mMoltiplicatore);
                if (mMoltiplicatore == 0)
                    mMoltiplicatore++;
                
            }
        }
        public int C//incremento
        {
            get { return mIncremento; }
            set
            {
                mIncremento = value;
                if (mIncremento > M)
                    mIncremento = (int)Math.Round((decimal)(mIncremento/mModulo));
                if (mIncremento < 0)
                    mIncremento = Math.Abs(mIncremento);
                
            }
        }
        
        //costruttori
        //base
        public Generatore_Lineare_Congruenziale()
        {
            Random r = new Random();
            M = r.Next();
            A = r.Next();
            C = r.Next();
        }
        //per esempio 1
        public Generatore_Lineare_Congruenziale(bool Decimal)
        {
            
            Random r = new Random();
            if(Decimal)
            {
                mM = decimal.Divide((decimal)r.Next(1,1000),1000) ;
                mA =  decimal.Divide((decimal)r.Next(1,1000),1000) ;
                mC = decimal.Divide((decimal)r.Next(1,1000),1000) ;
                
           /*      Console.Write("M =");
            Console.Write(mM.ToString("N3"));
            Console.Write("\n");
            Console.Write("A =");
            Console.Write(mA.ToString("N3"));
            Console.Write("\n");
            Console.Write("C =");
            Console.Write(mC.ToString("N3"));
            Console.Write("\n");*/
            }
            else
            {
                M = r.Next();
                A = r.Next();
                C = r.Next();
            }
        }
       public Generatore_Lineare_Congruenziale(int modulo,int moltiplicatore,int incremento)
        {
            Random r = new Random();
            M = modulo;Max = modulo;
            A = moltiplicatore;
            C = incremento;
        }

        //metodi utili
        public void setMax(int MAX)
        {
            M = MAX;
        }
        //il bool serve perchè altrimenti non lascia generare un decimal, avendo la stessa intestazione
        public decimal Genera(int min,int max,bool Decimal)
        {
            mM = max;
            Max = max;
            Min = min;
            

            /*Console.WriteLine("mXi = {0};\n", mXi);
            Console.WriteLine("mA = {0};\n", mXi);
            Console.WriteLine("mC = {0};\n", mXi);*/



            do
            {
                mXi = decimal.Divide(decimal.Remainder(decimal.Multiply(decimal.Add(decimal.Multiply(mXi, mA), mC), 1000), decimal.Multiply(mM, 1000)), 1000);  
                }while(mXi < min);

            //Console.WriteLine("mXi = {0};\n",mXi);

            return mXi;
        }

        //metodi di cui fare l' override
        public override int Genera(int Seed)
        {
            Xi = (Seed * A + C) % M ;
            return Xi;
        }
        public override int Genera()
        {
            Xi = (Xi * A + C) % M;
            return Xi;
        }
        //al momento sembra non esserci una formula per assicurarsi direttamente che sia superiore al minimo, quindi lo ricalcola finche non lo è
        public override int Genera(int min,int max)
        {
            M = max;
            Max = max;
            Min = min;
            //da modificare
            do{
            Xi = (Xi * A + C) % M ;
                }while(Xi < min);
            
            return Xi;
        }
        
        //descrizioni
        public override string Descrizione()
        {
            string ris = "Il Generatore Lineare Congruenziale è un algoritmo piuttosto vecchio, semplice e computazionalmente leggero.\n " +
                "La formula ricorsiva è : X(n+1) = [ a * X(n) + c] mod m\n" +
                "Dove:\n" +
                "\tm è il modulo ed è compreso fra 0 ed +infinito (in questo caso m = "+ M+"); \n" + 
                "\ta è il moltiplicatore ed è compreso fra 0 ed m (in questo caso a = "+A+");\n" +
                "\tc è l'incrementatore ed è compreso fra 0 ed m (in questo caso c = "+C+");\n" +
                "\tX(0) è il seed ed è compreso fra 0 ed m (in questo caso X(0) = "+Xi+");\n" +
                "Il periodo dell' algoritmo è di m, ed è pieno solo se si verificano le seguenti condizioni:\n" +
                "\tc ed m sono coprimi (hanno come MCD 1);\n" +
                "\ta-1 è divisibile per tutti i fattori primi di m;\n" +
                "Il Generatore Lineare Congruenziale è stato usato da GCC con la funzione rand e dalla Random class di Java.\n" +
                "L'algoritmo non è efficiente e non deve essere usato dove viene richiesto un alto grado di casualità o in crittografia.\n";
            return ris;
        }
        public override string Description()
        {
            string ris = "The linear congruential generator is an old algorithm, that is easy to reproduce and computationally light.\n " +
                "Its recursive formula is: X(n+1) = [ a * X(n) + c] mod m\n" +
                "Where:\n" +
                "\tm it's the module between 0 and +infinite (in this case m = "+ M+"); \n" +  
                "\ta it's the multiplier between 0 and m (in this case a = "+A+");\n" +
                "\tc it's the increment between 0 and m (in this case c = "+C+");\n" +
                "\tX(0) it's the seed between 0 and m (in this case X(0) = "+Xi+");\n" +
                "The period of the algorythm is m, and it's full only if:\n" +
                "\tc and m are coprime (they have as MCD 1);\n" +
                "\ta-1 is divisible for all of m's prime factors;\n" +
                "The linear congruential generator was used by GCC with the rand function and Java's Random class.\n" +
                "It's not efficent and it shouldn't be used where a high degree of casuality is required or for cryptography.\n";
            return ris;
        }
         public override string 叙述()
        {
            return "合目的的線状擬似乱数生成器は古くて、易しいです.\n " +
                "再帰の式は : X(n+1) = [ a * X(n) + c] mod m\n" +
                
                "\tm は加群、m大なり0、m小なり無限大(今のmは "+ M+"です); \n" +
                "\ta は追補 、a大なり0、a小なりm(今のaは "+ A+"です); \n" +
                "\tc は乗数、c大なり0、c小なりm(今のcは "+ C+"です); \n" +
                "\tX0 は種、X0大なり0、X0小なり無限大(今のX0は "+ Seed+"です); \n" +
                "演算手順の周期はm。\n" +
                
                "合目的的線状擬似乱数生成器は GCC の rand とJavaの Random class にあります .\n" +
                "この演算手順を暗号化手法に用いないでください！\n";

        }

    } //all ok

    
    public class Lagged_Fibonacci : Algoritmo_Distribuzione_Uniforme
    {
        private Random rand = new Random();
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
        public override int Genera(int min, int maxValue)
        {
            for (int i = 0; i < array.Length; i++)
            {
                array[i] = rand.Next(min, maxValue);
            }

            int firstElement = array[n - j];
            int secondElement = array[n - k];

            randomNumber = (firstElement + secondElement) % Math.Pow(m, 32);

            array[n] = (int)randomNumber;
            int ris = (int)randomNumber % (maxValue + 1);
            return (int)randomNumber % (maxValue + 1);
            
            

        }
        public override int Genera()
        {
           return Genera(0,600);
        }
        
        //descrizioni
        public override string Descrizione()
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
        public override string Description()
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
        public override string 叙述()
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

    
    public class Registro_a_Scorrimento_a_Retroazione_Lineare : Algoritmo_Distribuzione_Uniforme
    {
        Random r = new Random();
        bool[] bits;
        
        //descrizioni
        public override string Descrizione()
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
        public override string Description()
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
                "as in the r Direct Sequence Spread Spectrum radio technique, used in theUMTS.\n"+s
                "The GPS used LFSR to quicly send a sequence equal to high-precision relative istances,\n" +
                "taking advantage of it's determinism: that's because you only need to transmit the used seed to the transmitter since the generated sequence will be the same on the receiver .\n";



        }
        public override string 叙述()
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
                sbr.Append( r.Next(0, 2) );
            }
          string  seed = sbr.ToString();
           // Console.WriteLine("Il seed è "+seed);  ok


            bits = new bool[10];

            for (int i = 0; i < 10; i++)
                bits[i] = seed[i] == '1' ? true : false; //if seed[i] == 1 true, else false


        }  //nel costruttore di base riempie l' array con una sequenza casuale di 0 e 1

        public Registro_a_Scorrimento_a_Retroazione_Lineare(int bitCount, string seed)
        {
            bits = new bool[bitCount];

            for (int i = 0; i < bitCount; i++)
                bits[i] = seed[i] == '1' ? true : false; //if seed[i] == 1 true, else false

        }//quì trascrive la stringa



        


        public string Registry
        {
            get
            {
                char[] t = new char[bits.Length];
                for (int i = 0; i < bits.Length; i++)
                    t[i] = bits[i] ? '1' : '0';

                return new string(t);
            }
        } // quando si fa il get del registro, esso prende i valori del bit array e li trasforma in stringa

      

        public void Shift()
        {
            
            bool bnew = !(bits[bits.Length - 1] == bits[bits.Length - 2]);

            for (int i = bits.Length - 1; i > 0; i--)
            {
                bits[i] = bits[i - 1];
            }
            bits[0] = bnew;
        }//come da nome, shifta



        public override int Genera(int Seed)
        {

              
           
            var period = 0;
            int ris=0;

            do
            {
                 
                Shift();
                period++;
                
            } while (period <= Seed );

         //   Console.WriteLine(Registry);

            for (int j = 0; j < bits.Length; j++)
            {
                if (bits[j])
                    ris += Convert.ToInt32(Math.Pow(2, bits.Length - (j + 1)));
            }
            return ris;

        }

        public override int Genera()
        {
            int i = r.Next(1, 31);
            return Genera(i);

            
        }

        

        
    }



    
   public class Blum_Blum_Shub : Algoritmo_Distribuzione_Uniforme
    {
        private int mP;
        private int mQ;
        private int xi;
        private int mSeed;
        private const int i = 10;
        private int[] zi; 

        public int p
        {
            get { return mP; }
            set
            {
                mP = value;
                if (mP < 0)
                    mP = Math.Abs(mP);
                while (mP % 4 != 3 && !IsPrime(mP))
                    mP++;
                
            }
        }//verificare se p e q sono primi!
        public int q
        {
            get { return mQ; }
            set
            {
                mQ = value;
                if (mQ < 0)
                    mQ = Math.Abs(mP);
                while ((mQ % 4 != 3) && (!IsPrime(mQ)))
                    mQ++;

            }
        }
        public int Seed  //verificare se è coprimo di n
        {
            get { return mSeed; }
            set
            {
                mSeed = value;
                int n = q * p;
                while (mSeed > (n - 1))
                {
                   // Console.WriteLine("Il seed è maggiore di n, ricalcolo");
                     mSeed--;
                    
                }
                   
                if (!Coprimo(mSeed, n))
                {
                   // Console.WriteLine("Il seed non è coprimo, ricalcolo");
                    mSeed = n-2;
                    while(!Coprimo(mSeed, n))
                    {
                        mSeed--;
                        if(mSeed == 0)
                           throw new Exception("Non esiste un possibile seed per questi valori di p = "+p+" e q = "+q);
                    }
                    
                }
                
               // Console.WriteLine("Il seed è coprimo, ok: {0}",mSeed);  
            }
        }

        public Blum_Blum_Shub()
        {
            Random r = new Random();
            p = r.Next(0,1000);
            q = r.Next(0,1000);
            Seed = r.Next(p * q);
            xi =0;
            zi = new int[i];
        } 
        public Blum_Blum_Shub(int P, int Q)
        {
            Random r = new Random();
            p = P;
            q = Q;
            Seed = r.Next(p * q);
            xi =0;
            zi = new int[i];
        } 
        public Blum_Blum_Shub(int P, int Q, int seed)
        {
            p = P;
            q = Q;
            Seed = seed;
            xi =0;
            zi = new int[i];
        }

        public override string Descrizione()
        {
            return "L'algoritmo utilizza 4 variabili: q, p, n ed un seed.\n"+
            "\tp e q sono due numeri primi,\n\tn è il prodotto di p e q,\n\til seed è un numero coprimo e minore di n.\n"+
            "il valore inziale è dato dal seed elevato alla seconda modulo n (X0 = seed ^ 2 mod n),\n"+
            "per trovare gli X successivi si applica la formula precedente con l' x corrente(X(n) = X(n-1)^2 mod n\n)"+
            "per ogni X(n) si prende il bit meno significativo e lo inserisce in un vettore di bit.\n"+
            "Il risultato sarà dato dalla conversione in intero del numero binario contenuto nell array\n"+
            "Es: con n=5, numero binario risultante = 10001, risultato = 17";
        }
        public override string Description()
        {
            //TODO
            return "L'algoritmo utilizza 4 variabili: q, p, n ed un seed.\n"+
            "\tp e q sono due numeri primi,\n\tn è il prodotto di p e q,\n\til seed è un numero coprimo e minore di n.\n"+
            "il valore inziale è dato dal seed elevato alla seconda modulo n (X0 = seed ^ 2 mod n),\n"+
            "per trovare gli X successivi si applica la formula precedente con l' x corrente(X(n) = X(n-1)^2 mod n\n)"+
            "per ogni X(n) si prende il bit meno significativo e lo inserisce in un vettore di bit.\n"+
            "Il risultato sarà dato dalla conversione in intero del numero binario contenuto nell array\n"+
            "Es: con n=5, numero binario risultante = 10001, risultato = 17";
        }
        
        public override string 叙述()
        {//ok dio cane
            return "演算手順のインプットは q, p, n ,seed.\n"+
            "\tpとqは素数,\n\tnはpかけるq,\n\tseedは nの互いに素,seed小なりn\n"+
            "X0は seed の 2 乗 mod n です,\n"+
            "X(n) は X(n-1)の 2 乗 mod nです\n)"+
            "そして,X(n)の最小有効ビットをビットの同位列にインサート\n"+
            "アウトプットはビットの同位列の整数値です\n"+
            "例\n\tnは5です,ビットの同位列は10001,アウトプットは17";
           

        }
        
        public override int Genera()
        {
            return Genera(Seed,p,q);
        }
        public override int Genera(int P, int Q)
        {
            Random r = new Random();
            
            //p e q sono due numeri primi

            p = P;
            q = Q;

            // n = p x q
            int n = p * q;

            // il seed è un numero casuale coprimo e minore di n
  
            int ris= Genera(Seed,p,q);


            return ris;


        }
        public int Genera(int seed, int P, int Q)
        {
            

            //p e q sono due numeri primi

            p = P;
            q = Q;

            // n = p x q
            int n = p * q;

            // il seed è un numero casuale coprimo e minore di n
            Seed = seed;
            

            // x0 è seed^2 mod n
            
           
            Console.Write("X0 = {0}", xi+"\n");

            //per j che va da 1 a i
            for (int j = 0; j < i; j++)
            {
                // xi = x(i-1) ^ 2 mod n
               /* Console.Write("xi^2 = {0}", Math.Pow(xi, 2)+"\n");
                Console.Write("xi^2 mod n = {0}", Math.Pow(xi, 2) % n+"\n");*/
                if(xi < 0)
                    Console.Write("Xi è negativo: {0}", xi);
                if(xi == 0)
                   xi = (int)((Math.Pow(Seed, 2) )% n);
                else
                {
                   xi = (int)(Math.Pow(xi,2)% n);
                  // Console.Write("Dopo aver svolto l'operazione xi ={0}", xi+"\n");
                }
                /*if(xi < 0)
                    Console.Write("C'è un problema nella potenza: {0}",xi+"\n");*/

                //si prende il bit meno significativo di xi
                zi[j] = xi %2;
               /* if(zi[j] < 0)
                    Console.Write("C'è un problema nel modulo");*/
               
                
                Console.Write(zi[j]+" ");
               
            }

            //si converte il bit array in int  (per comodità è stato realizzato un array di int in cui può esser messo solo 0 ed 1)

            int ris = 0;

            for (int j = 0; j < i; j++)
            {
                if (zi[j] == 1)
                    ris += Convert.ToInt32(Math.Pow(2, i-(j+1)));
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
            return Math.Max(value1, value2);
        }
        private static bool Coprimo(int value1, int value2)
        {
            return MCD(value1, value2) == 1;
        }
        private static bool IsPrime(int number)
        {
            if (number < 2) return false; //se è minore di 2 (1,0 o negativo) non è primo
            if (number % 2 == 0) return false; //se è divisibile per 2 non è primo
            int root = (int)Math.Sqrt((double)number);
            for (int i = 3; i <= root; i += 2)
            {
                if (number % i == 0) return false; //ha trovato un numero per cui è divisibile
            }//calcolo effettivo per vedere se è primo: parte da i = 3 e passa tutti i numeri dispari fino a superare la radice. 
            return true;
        }


    }


}
