//#define debugGLC 
//#define debugFIBLAG
//#define debugRRL
//#define debugBBS
#define debugND
//#define debugGLCFNL


using System;
using Casualita;
using System.Collections.Generic;





namespace Test
{
    class Program
    {
        

        static void Main(string[] args)
        {
            #region test GENERATORE LINEARE CONGRUENZIALE

#if debugGLC
            LinkedList<long> l1 = new LinkedList<long>();
            Generatore_Lineare_Congruenziale g = new Generatore_Lineare_Congruenziale();
            Registro_a_Scorrimento_a_Retroazione_Lineare rsrl = new Registro_a_Scorrimento_a_Retroazione_Lineare();
            for (int i = 0; i < 20; i++)
             {
                 l1.AddLast(g.Genera(0,100));
             }
             Console.WriteLine("Generatore Lineare Congruenziale: (da 0 a 100)\n");
             foreach (long lo in l1)
             {
                 Console.WriteLine(lo);
             }
            Console.WriteLine();
#endif

            #endregion fine test glc

            
            Console.WriteLine();
            Console.ReadKey();

            #region test FIBONACCI RITARDATO

#if debugFIBLAG

            LinkedList<long> l = new LinkedList<long>();
            Lagged_Fibonacci lag = new Lagged_Fibonacci();
            for (int i = 0; i < 20; i++)
            {
                l.AddLast(lag.Genera());
            }
            Console.WriteLine("Lagged Fibonacci:\n(da 0 a 600)");
            foreach (long lo in l)
            {
                Console.WriteLine(lo);
            }
            Console.WriteLine();
            Console.ReadKey();
#endif

            #endregion fine test lagfib



            #region test BLUM BLUM SHUB

#if debugBBS

            LinkedList<long> l = new LinkedList<long>();
            Blum_Blum_Shub bbs = new Blum_Blum_Shub(19,23);
            for (int i = 0; i < 20; i++)
            {
                l.AddLast(bbs.Genera());
            }
            Console.WriteLine("Blum Blum Shub:\n");
            foreach (long lo in l)
            {
                Console.WriteLine(lo);
            }
            Console.WriteLine();
            Console.ReadKey();
#endif

            #endregion fine test bbs



            #region test Registro a retroazione lineare

#if debugRRL

            LinkedList<long> l = new LinkedList<long>();
            Registro_a_Scorrimento_a_Retroazione_Lineare rrl = new Registro_a_Scorrimento_a_Retroazione_Lineare();
            for (int i = 0; i < 20; i++)
            {
                l.AddLast(rrl.Genera());
            }
            Console.WriteLine("Registro a Retroazione Lineare:\n");/*(da 0 a 600)*/
            foreach (long lo in l)
            {
                Console.WriteLine(lo);
            }
            Console.WriteLine();
            Console.ReadKey();
#endif

            #endregion fine test RRL




            #region test Generatore Lineare Congruenziale Da 0 a 1

#if debugGLCFNL
            LinkedList<decimal> l = new LinkedList<decimal>();
            Generatore_Lineare_Congruenziale glc = new Generatore_Lineare_Congruenziale(true);
            for (int i = 0; i < 20; i++)
            {
                l.AddLast(glc.Genera(0,1,true));
            }
            Console.WriteLine("Generatore lineare congruenziale da 0 a 1:\n");/*(da 0 a 1)*/
            foreach (decimal lo in l)
            {
                Console.WriteLine(lo);
            }
            Console.WriteLine();
            Console.ReadKey();

#endif

            #endregion fine test GLCFNL




            #region Debug generatore non distribuito

#if debugND

            LinkedList<decimal> l = new LinkedList<decimal>();
            Esempio1 es = new Esempio1();
            for (int i = 0; i < 20; i++)
            {
                l.AddLast(es.GeneraDecimal());
            }
            Console.WriteLine("Generatore non distribuito da 0 a +infinito:\n");/*(da 0 a 1)*/
            foreach (decimal lo in l)
            {
                Console.WriteLine(lo);
            }
            Console.WriteLine();
            Console.ReadKey();

#endif

            #endregion fine debug nd



        }
    }
}
