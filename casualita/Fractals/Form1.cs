using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Drawing.Imaging;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;



namespace Fractals
{

    //QUì AVVIENE L' AZIONE
    public partial class Form1 : Form
    {
        private Bitmap myBitmap; // Bitmap declaration
        
        


        //INIZIALIZZAZIONE FORM
        public Form1()
        {
            InitializeComponent();
            

        }


        void SetNewBitmap(Bitmap image)
        {
            if (this.BackgroundImage != null)
                this.BackgroundImage.Dispose();
            this.BackgroundImage = image;
        }

        delegate void SetNewBitmapDelegate(Bitmap image);

        

        void thread_Proc(object args)
        {
            // start from small image to provide instant display for user
            Size size = (Size)args;
            int width = 16;
            while (width * 2 < size.Width)
            {
                int height = width * size.Height / size.Width;
                Bitmap bitmap = new Bitmap(width, height, PixelFormat.Format24bppRgb);
                SetNewBitmap(bitmap);
                this.BeginInvoke(new SetNewBitmapDelegate(SetNewBitmap), bitmap);
                width *= 2;
                Thread.Sleep(200);
            }
            // then generate final image
            Bitmap finalBitmap = new Bitmap(size.Width, size.Height, PixelFormat.Format24bppRgb);
            SetNewBitmap(finalBitmap);
            this.BeginInvoke(new SetNewBitmapDelegate(SetNewBitmap), finalBitmap);
        }// da eseguire con una thread

        //CARICAMENTO FORM, IL CODICE VA SCRITTO QUì PER IL TESTING, PER IL RESTO VEDREMO DOPO
        private void Form1_Load(object sender, EventArgs e)
        {
            
            
            Graphics graphicsObj; //SERVE PER LA PARTE GRAFICA

            myBitmap = new Bitmap(this.ClientRectangle.Width,
            ClientRectangle.Height,
            PixelFormat.Format24bppRgb);  //BITMAP
           




            graphicsObj = Graphics.FromImage(myBitmap); // L' OGGETTO GRAFICO SFRUTTA LA BITMAP


            Pen myPen = new Pen(System.Drawing.Color.White, 3); // LA PENNA SERVE PER FARE I PUNTI, SE CAMBI COLORE RICORDATI CHE LO SFONDO è NERO

            myPen.DashStyle = System.Drawing.Drawing2D.DashStyle.Solid; // QUESTO LASCIALO COSì




            //DA QUì IN POI SI RICHIAMANO LE FUNZIONI PER IL TESTING, Sono TUTTE STATICHE
            
            //.Draw(Form,x0,y0, vertici)

           // T_Square.Draw(this,0,0,0, 0, 0, 900,900,900,900,0);


            //Serp_Triangle.Draw(this, 400,0, 400, 0, 0, 800, 800, 800);



            //Shuriken.Draw(this,0, 0, 0, 0, 0, 800, 800, 800, 800, 0);


            // Forest.Draw(this,0, 0, 0, 0, 0, 800, 800, 800, 800, 0);



            //Quad.Draw(this,0, 0, 0, 0, 0, 800, 800, 800, 800, 0);


            //Pent_Serp.Draw(this,400,0,400, 0, 0, 300, 180, 660, 620, 660, 800, 300);


            //The_Hive.Draw(this,400,0,400, 0, 0, 300, 180, 660, 620, 660, 800, 300);


           // Star.Draw(this,400,0,400, 0, 0, 300, 180, 660, 620, 660, 800, 300);

           //Barnsley_Fern.Draw(this,  0, 0); 

           //Mandelbrot.Draw(myBitmap); //va ma è lento

            //Console.WriteLine(Serp_Triangle.Descrizione());

            graphicsObj.Dispose(); // SERVE A RILASCIARE LE RISORSE
            //gc.Collect();  //commentato per via della sintassi   //our beloved Garbage Collector 
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Graphics graphicsObj = e.Graphics;

            graphicsObj.DrawImage(myBitmap, 0, 0, myBitmap.Width, myBitmap.Height);

            graphicsObj.Dispose();
        } // EVENTO PAINT, NON MODIFICARE
    }
}



//esempio documentazione interna
/// <summary>

/// IL TRIANGOLO DI SERPINSKY SI OTTIENE FACENDO IN MODO CHE, DATI 3 PUNTI INIZIALI (VERTICI DEL TRIANGOLO)
///METTENDO I PUNTI SUCCESSIVI TRA LA METà DELLA DISTANZA FRA IL PUNTO CORRENTE ED UNO DEI VERTICI(SCELTO CASUALMENTE)
///PER FARLA RISULTARE BENE HO MESSO 10 000 PUNTI
///I VERTICI SONO
///              1
/// 
/// 
///
///      2             3
///
/// </summary>


class Serp_Triangle
{
    public static int DISTANCE_FRACTION = 2;
    //private volatile bool Done;

    // LA FUNZIONE CHE VERRà RICHIAMATA FUORI
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y)
    {
        // CHIAMA TRUE DRAW SFRUTTANDO LA THREAD (SENZA THREAD SI VEDE TUTTO ALLA FINE)

        //Lambda
        int i = 0;
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, i);
       

   });
        t.IsBackground = true;
        t.Start();
    }


    //chiama 1000 volte l'helper(totale 10 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int i)
    {
        Random r = new Random();// MOLTO IMPORTANTE: IL RANDOM VA DICHIARATO DENTRO LA FUNZIONE TRUE, ALTRIMENTI NON FUNZIONA NULLA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, i, r);
        }
       
    }

    //100 punti, FA TUTTO IL LAVORO
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int i, Random r)
    {

        int scelta = r.Next(1, 4);
        int nextPx = 0, nextPy = 0; //DEVONO ESSERE INIZIALIZZATI



        // Console.WriteLine(scelta);  DEBUG 

        //PARTE IN CUI SCEGLIE IL PUNTO SUCCESSIVO BASANDOSI SULLA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }
        //Console.WriteLine(nextPx + "; " + nextPy +";\n");  DEBUG


        //LOCK PER GESTIRE IL MULTITHREADING
        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
           
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

            

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, i, r);
            // Console.WriteLine("Done {0}", i);  DEBUG
        }


    }

    public static string Descrizione()
    {
        string ris;
        ris = "Il triangolo di Serpinsky è senza dubbio uno dei frattali più famosi, principalmente per la semplicemente di costruzione.\n" +
              "Per realizzarlo tramite chaos game bisogna seguire il seguente procedimento:\n\t" +
              "1) si prende come punto di partenza uno dei 3 punti iniziali(i vertici del triangolo);\n\t" +
              "2) Il punto successivo sarà scelto a metà tra la distanza del punto corrente e uno dei vertici(scelti casualmente);\n " +
              "Nel caso si voglia realizzarlo in modo non procedurale, occorre inscrivere un triangolo  all' interno di quello iniziale,\n utilizzando i punti medi dei lati come vertici, per poi ripetere il procedimento per ogni livello di ricorsione con i triangoli che si formeranno.\n" +
              "Un triangolo di Serpinsky iterato all' infinito ha un area pari a 0.\n" +
              "L'esempio riportato è stato realizzato con 100 000 punti.\n";

        return ris;

    }
    public static string Description()
    {
        string ris;
        ris = "The Serpinsky triangle undoubtedly is one of the most famous fractals, mainly because it's the easiest one to create\n" +
              "To create it via chaos game you need to proceed in the following way:\n\t" +
              "1) Pick one of the triangle vertex as the starting point;\n\t" +
              "2) The next point will be chosen at half of the distance between the current point and one of the vertex(randomly chosen)\n " +
              "If you want to recreate it in the non-procedural way, you have to inscribe a triangle inside the initial one.\nThis triangle has the midpoints of the initial triangle sides as vertexes.\n You must repeat the procedure with the other triangles, accordingly to th recursion level chosen.\n" +
              "An infinitely iterated Serpinsky triangle has an area of 0.\n" +
              "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "サーピンスキの三角形は世界で一番有名な自己相似図形です。創造は易しいですから。\n" +
              "　混乱遊戯のサーピンスキの使い方は：\n\t" +
              "一つ) 三角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "非ランダムサーピンスキの使い方は：\n" +
              "一つ) さんかっけいの側の半ばは起点；\n" +
              "サーピンスキの三角形の幾何学的領域はゼロ。\n" +

              "このサーピンスキの三角形は十万点。";

        return ris;
    }
} // 100%


/* IL T-SQUARE SI OTTIENE FACENDO IN MODO CHE, DATI 4 PUNTI INIZIALI (VERTICI DEL QUADRATO)
 * METTENDO I PUNTI SUCCESSIVI TRA LA METà DELLA DISTANZA FRA IL PUNTO CORRENTE ED UNO DEI VERTICI(SCELTO CASUALMENTE)
 * IL VERTICE NON PUò ESSERE QUELLO OPPOSTO (DISTANZA = 2)
 * 
 * PER FARLA RISULTARE BENE HO MESSO 10 000 PUNTI
 * 
 * I VERTICI SONO
 *       1           4
 * 
 * 
 * 
 *       2           3
 *       
 *       Es: se scelgo 1 non posso scegliere 3,2 non posso scegliere 4, ecc
 */
class T_Square
{
    public static int DISTANCE_FRACTION = 2;


    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y)
    {
        //Lambda
        int i = 0;
        int buf = 0;
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf);

   });
        t.Start();
    }


    //chiama 100 volte l'helper (totale 10 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }
    }

    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while (((scelta - buf) == 2) || ((scelta - buf) == -2))
        {
            scelta = r.Next(1, 5);
        }// VERIFICA CHE NON SIA IL VERTICE OPPOSTO
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        buf = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }

    }

    public static string Descrizione()
    {
        string ris =
            "Il T-Square è anch'esso uno dei frattali più conosciuti.A partire da questo però, tutti i frattali presi in caso avrenno una condizione da rispettare nella scelt del vertice, pur essendo essa casuale.\nCome per il Triangolo di Serpinski esistono due metodi per ottenerlo:\n" +
            "METODO NON PROCEDURALE:\n\tLa figura di partenza è un quadrato.\n\t" +
            "Successivamente si creano dei quadrati aventi come centro gli angoli del quadrato iniziale e come lato metà della lunghezza del quadrato iniziale.\n\t" +
            "Si ripete il tutto fino al livello di ricorsione desiderato.\n" +
            "CHAOS GAME:\n\tSi hanno come punti di riferimento qgli estremi del quadrato iniziale.\n\tIl punto successivo sarà a metà della distanza del vertice scelto.\n\t" +
            "Condizione: il vertice scelto non deve essere opposto a quello appena scelto. (Non deve avere una distanza pari a 2) "
            ;
        return ris;
    }
    public static string Description()
    {
        string ris;
        ris = "The T-Square is another famous fractal that unlike Serpinsky's triangle has one condition to follow for its creation: the next chosen vertex must not be the opposite of the current chosen one.\n" +
              "To create it via chaos game you need to proceed in the following way:\n\t" +
              "1) Pick one of the square vertex as the starting point;\n\t" +
              "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point' \n" +
              "If you want to recreate it in the non-procedural way you need to:\n\t " +
              "1) Pick a starting square;\n\t" +
              "2) Add a smaller square that has a starting triangle's vertex in the centre and is half the length of the starting triangle." +
              "3) Repeat the procedure (and remember to follow the condition for its creation) until you have obtained the recursion level desired;\n\t" +


              "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "Tの四角もとても有名です。\n" +
              "Tの四角の使い方は：\n\t" +
              "一つ) 四角形の頭頂は起点；\n\t" +
              "二つ) 次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点は向い点ことができません。\n" +

              "このTの四角は十万点。";

        return ris;
    }
} // 100%


/*
 *  IL VERTICE NON PUò ESSERE IL SUCCESSIVO ANTIORARIO 
 * 
 * PER FARLA RISULTARE BENE HO MESSO 100 000 PUNTI
 * 
 * I VERTICI SONO
 *       1           4
 * 
 * 
 * 
 *       2           3
 *       
 *       Es: se scelgo 1 non posso scegliere 2,2 non posso scegliere 3, ecc
 */
class Shuriken 
{
    public static int DISTANCE_FRACTION = 2;


    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y)
    {
        int i = 0, buf = 0;
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf);

   });
        t.Start();
    }
    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }
    }
    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while (((scelta - buf) == 3) || ((scelta - buf) == -1))
        {
            scelta = r.Next(1, 5);
        }// VERIFICA CHE NON SIA IL VERTICE SUCCESSIVO ANTIORARIO
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        buf = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }

    }

    public static string Descrizione()
    {
        string ris = "Questo frattale senza nome, simile ad uno shuriken, può essere ottenuto solo tramite chaos game.\n" +
            "La figura di partenza è un quadrato.\n" +
            "La condizione è che il vertice seguente non sia il successivo antiorario di quello scelto in precedenza.\n " +
            "Il seguente esempio è stato creato con 100 000 punti";
        return ris;
    }

   
    public static string Description()
    {
        string ris;
        ris = "This shuriken-like nameless fractal can only be obtain via chaos game.\n" +
              "To create it you need to proceed in the following way:\n\t" +
              "1) Pick one of the square vertex as the starting point;\n\t" +
              "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point'\n " +
              "3)Condition: the next chosen vertex must not be counter-clockwise of the current chosen one\n " +
              "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "この手裏剣的自己相似図形は混乱遊戯丈作られることができます。\n" +
              "この自己相似図形の使い方は：\n\t" +
              "一つ) 四角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点は反時計回り点ことができません。\n" +

              "この自己相似図形は十万点。";

        return ris;
    }

} // 100%

/*
 *  IL VERTICE NON PUò ESSERE AD UN VALORE ANGOLARE DIFFERENZIALE DI 1
 * 
 * PER FARLA RISULTARE BENE HO MESSO 100 000 PUNTI
 * 
 * I VERTICI SONO
 *       1           4
 * 
 * 
 * 
 *       2           3
 *       
 *       Es: se scelgo 1 non posso scegliere 2, 2 non posso scegliere 3 e 1, 3 non posso scegliere 2 e 4, 4 non posso scegliere 3
 */
class Forest 
{
    public static int DISTANCE_FRACTION = 2;


    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y)
    {
        int i = 0, buf = 0;
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf);

   });
        t.Start();
    }


    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }
    }

    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while (((scelta - buf) == 1) || ((scelta - buf) == -1))
        {
            scelta = r.Next(1, 5);
        }// VERIFICA CHE NON SIA ?
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        buf = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }

    }

    public static string Descrizione()
    {
        string ris = "Questo frattale senza nome può essere ottenuto solo tramite chaos game.\n" +
            "La figura di partenza è un quadrato.\n" +
            "La condizione è che il vertice seguente non sia a distanza di uno dal valore numerico assegnato ai vertici.\n" +
            "I vertici hanno valore:\n" +
            "1        4\n" +
            "\n" +
            "\n" +
            "2        3\n";
        return ris;
    }

    
    public static string Description()
    {
        string ris;
        ris = "This fractal can be obtained only via chaos game.\n" +
              "To create it you need to proceed in the following way:\n\t" +
              "1) Pick one of the square vertex as the starting point;\n\t" +
              "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point'\n " +
              "Condition) The next point's value can't have a difference of one from the current point's value\n" +
              "The values are:\n" +
              "1\t4\n2\t3\n"+
              "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "この森的自己相似図形は混乱遊戯丈作られることができます。\n" +
              "この自己相似図形の使い方は：\n\t" +
              "一つ) 四角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点の値はーことができません。\n" +
              "値は:\n" +
              "ー\t四\n三\t二\n" +

              "この自己相似図形は十万点。";

        return ris;
    }

} // 100%

/*
 *  IL VERTICE NON PUò ESSERE LO STESSO 
 * 
 * PER FARLA RISULTARE BENE HO MESSO 100 000 PUNTI
 * 
 * I VERTICI SONO
 *       1           4
 * 
 * 
 * 
 *       2           3
 *       
 *       Es: se scelgo 1 non posso scegliere 1 ,2 non posso scegliere 2, ecc */
class Quad 
{
    public static int DISTANCE_FRACTION = 2;



    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y)
    {
        int i = 0, buf = 0;
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf);

   });
        t.Start();
    }


    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }
    }

    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int i, int buf, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while (scelta == buf)
        {
            scelta = r.Next(1, 5);
        }// VERIFICA CHE NON SIA IL VERTICE precedente
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        buf = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, i, buf, r);
        }

    }

    public static string Descrizione()
    {
        string ris = "Questo frattale senza nome può essere ottenuto solo tramite chaos game.\n" +
            "La figura di partenza è un quadrato.\n" +
            "La condizione è che il vertice seguente non sia lo stesso appena scelto\n" +
            "La seguene figura è stata realizzata con 100 000 punti\n";

        return ris;
    }
    public static string Description()
    {
        string ris;
        ris = "This fractal can only be obtained via chaos game.\n" +
              "1)the starting shape is a square\n"+
              "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point'\n " +
              "Condition: the chosen point cannot be the same\n" +
              "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "この自己相似図形は混乱遊戯丈作られることができます。\n" +
              "使い方は：\n\t" +
              "一つ) 四角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点は同じ点ことができません。\n" +

              "この自己相似図形は十万点。";

        return ris;
    }

} // 100%

/*
 *  IL VERTICE NON PUò ESSERE AD UN VALORE ANGOLARE DIFFERENZIALE DI 1
 * 
 * PER FARLA RISULTARE BENE HO MESSO 100 000 PUNTI
 * 
 * I VERTICI SONO
 *             1      
 * 
 *       2          5
 * 
 *         3      4
 *       
 *        Es: se scelgo 1 non posso scegliere 2, 2 non posso scegliere 3 e 1, 3 non posso scegliere 2 e 4, 4 non posso scegliere 3 e 5, 5 non posso scegliere 4
 *        
 */
class Pent_Serp 
{
    public static int DISTANCE_FRACTION = 2;


    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y)
    {
        int i = 0, buf = 0;
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf);

   });
        t.Start();
    }


    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y, int i, int buf)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf, r);
        }
    }

    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y, int i, int buf, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while (((scelta - buf) == 1) || ((scelta - buf) == -1))
        {
            scelta = r.Next(1, 6);
        }// VERIFICA CHE NON SIA 
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 5:
                {
                    nextPx = ((p5x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p5y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        buf = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf, r);
        }

    }

    public static string Descrizione()
    {
        string ris = "Questo frattale senza nome può essere ottenuto solo tramite chaos game.\n" +
            "La figura di partenza è un pentagono.\n" +
            "La condizione è che il vertice seguente non sia a distanza di 1 dal valore numerico assegnato ai vertici.\n" +
            "I vertici hanno valore:\n" +
            "    1    \n" +
            "2       5\n" +
            "  3   4\n";
        return ris;
    }


    public static string Description()
    {
        string ris;
        ris = "This fractal can be obtained only via chaos game and for it's successful creation it needs to follow one condition: the difference between the next vertex and the one previously chosen can't be one.\n" +
        "To create it you need to do the following:\n\t" +
        "1) Pick one of the pentagon vertex as the starting point;\n\t" +
        "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point'\n " +
        "The vertices's values are:\n " +
        "    1    \n" +
        "2       5\n" +
        "  3   4\n" +

        "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "この森的自己相似図形は混乱遊戯丈作られることができます。\n" +
              "この自己相似図形の使い方は：\n\t" +
              "一つ) 五角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点の値はーことができません。\n" +
              "値は:\n" +
              "\t一\t\n二\t\t五\n  三   四\n" +

              "この自己相似図形は十万点。";

        return ris;
    }
} // funziona , descrizione jp e ita ok

//PENTAGONO, IL VERTICE NON PUò ESSERE LO STESSO 
class The_Hive 
{
    public static int DISTANCE_FRACTION = 2;


    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y)
    {
        int i = 0, buf = 0;
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf);

   });
        t.Start();
    }

    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y, int i, int buf)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf, r);
        }
    }

    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y, int i, int buf, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while (scelta == buf)
        {
            scelta = r.Next(1, 6);
        }// VERIFICA CHE NON SIA LO STESSO
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 5:
                {
                    nextPx = ((p5x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p5y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        buf = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf, r);
        }

    }

    public static string Descrizione()
    {
        string ris = "Questo frattale senza nome può essere ottenuto solo tramite chaos game.\n" +
            "La figura di partenza è un pentagono.\n" +
            "La condizione è che il vertice seguente non sia lo stesso appena scelto\n";

        return ris;
    }


    public static string Description()
    {
        string ris;
        ris = "This fractal can be obtained only via chaos game and for it's successful creation it needs to follow one condition: the next vertex can't be the previous one.\n" +
        "To create it you need to do the following:\n\t" +
        "1) Pick one of the pentagon vertex as the starting point;\n\t" +
        "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point'\n " +

        "The following example has been created with 100 000 points\n";

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "この自己相似図形は混乱遊戯丈作られることができます。\n" +
              "使い方は：\n\t" +
              "一つ) 五角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点は同じ点ことができません。\n" +

              "この自己相似図形は十万点。";

        return ris;
    }

} // funziona 

//PENTAGONO, IL VERTICE NON PUò ESSERE L' ADIACENTE
class Star
{ 

    public static int DISTANCE_FRACTION = 2;


    //chiama il true con una thread
    public static void Draw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y)
    {
        int i = 0;
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i);

   });
        t.Start();
    }


    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y, int i)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        for (int j = 0; j < 1000; j++)
        {
            DrawHelper(F1,curPx, curPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, 6, 6, false, r);
        }
    }

    //100 punti
    static void DrawHelper(Form F1,int curPx, int curPy, int p1x, int p1y, int p2x, int p2y, int p3x, int p3y, int p4x, int p4y, int p5x, int p5y, int i, int buf1, int buf2, bool volta, Random r)
    {

        int scelta = r.Next(1, 5);
        int nextPx = 0, nextPy = 0;


        while ((buf1 - scelta) == -1 || (buf1 - scelta) == 1 || (buf1 - scelta) == 4 || (buf1 - scelta) == -4 || (buf2 - scelta) == -1 || (buf2 - scelta) == 1 || (buf2 - scelta) == 4 || (buf2 - scelta) == -4)
        {
            scelta = r.Next(1, 6);
        }// VERIFICA CHE NON SIA L'ADIACENTE,TENENDO CONTO DEI DUE PRECEDENTI VERTICI SCELTI
         // Console.WriteLine(scelta);  DEBUG

        //PARTE IN CUI CALCOLA LA DISTANZA
        switch (scelta)
        {
            case 1:
                {
                    nextPx = ((p1x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p1y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 2:
                {
                    nextPx = ((p2x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p2y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 3:
                {
                    nextPx = ((p3x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p3y + curPy) / DISTANCE_FRACTION);
                    break;
                }

            case 4:
                {
                    nextPx = ((p4x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p4y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            case 5:
                {
                    nextPx = ((p5x + curPx) / DISTANCE_FRACTION);
                    nextPy = ((p5y + curPy) / DISTANCE_FRACTION);
                    break;
                }
            default:
                {
                    Console.WriteLine("error");
                    break;
                }

        }

        if (volta)
            buf2 = scelta;//BUFFER PER MEMORIZZARE LA SCELTA PRECEDENTE
        else
            buf1 = scelta;


        lock (F1)
        {
            Graphics g = F1.CreateGraphics();
            g.FillRectangle((Brush)Brushes.Azure, nextPx, nextPy, 2, 2);

        }


        i = i + 1;
        volta = !volta;
        if (i < 100)
        {
            DrawHelper(F1,nextPx, nextPy, p1x, p1y, p2x, p2y, p3x, p3y, p4x, p4y, p5x, p5y, i, buf1, buf2, volta, r);
        }

    }

    public static string Descrizione()
    {
        string ris = "Questo frattale senza nome può essere ottenuto solo tramite chaos game.\n" +
            "La figura di partenza è un pentagono.\n" +
            "La condizione è che il vertice seguente non sia l' adiacente di uno dei due vertici scelti in precedenza\n";

        return ris;
    }


    public static string Description()
    {
        string ris;
        ris = "This fractal can be obtained only via chaos game and for it's successful creation it needs to follow one condition: the next vertex can't be adjacent of one of the two vertices chosen previously.\n" +
        "To create it you need to do the following:\n\t" +
        "1) Pick one of the pentagon vertex as the starting point;\n\t" +
        "2) Randomly choose one vertex and the starting point and put a midpoint between them which will become the next 'starting point'\n " +
        
        "The following example has been created with 100 000 points\n";


        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "この自己相似図形は混乱遊戯丈作られることができます。\n" +
              "使い方は：\n\t" +
              "一つ) 五角形の頭頂は起点；\n\t" +
              "二つ)次の点は先の点と次の点の半ばにあります。（次の点はランダムな起点ですよ）\n " +
              "制約) 次の点は隣接点ことができません。\n" +

              "この自己相似図形は十万点。";

        return ris;
    }
} // funziona 

//---------------------------------------------------------------------------

class Barnsley_Fern
{
    public static void Draw(Form F1, float curPx, float curPy)
    {
        //Lambda
        Thread t = new Thread(
   o =>
   {
       TrueDraw(F1, curPx, curPy);

   });
        t.Start();
    }


    //chiama 1000 volte l'helper (totale 100 000 punti)
    static void TrueDraw(Form F1,  float curPx, float curPy)
    {
        Random r = new Random();//STESSA CONSIDERAZIONE DI PRIMA
        double x = 0;
        double y = 0;
        for (int count = 0; count < 100000; count++)
            {
            lock(F1){
                Graphics g = F1.CreateGraphics();
                g.FillRectangle((Brush)Brushes.ForestGreen, new Rectangle((int)(300 + 100 * x), (int)(100 * y), 1, 1));  // x100 per ingrandire, +300 per spostare (il range della x va da circa -2.9 a +2.9, quello dela y va a 0 a 9.99)
            }
                
                int roll = r.Next(100);
                
                if (roll < 1)
                {
                    x = 0;
                    y = 0.16 * y;
                }
                else if (roll < 86)
                {
                    x = 0.85 * x + 0.04 * y;
                    y = -0.04 * x + 0.85 * y + 1.6;
                }
                else if (roll < 93)
                {
                    x = 0.2 * x - 0.26 * y;
                    y = 0.23 * x + 0.22 * y + 1.6;
                }
                else
                {
                    x = -0.15 * x + 0.28 * y;
                    y = 0.26 * x + 0.24 * y + 0.44;
                }
            }
        
    }

    

   


    public static string Descrizione()
    {
        string ris;
        ris = "La felce di Barnsley è un frattale piuttosto peculiare.\n" +
            "Per essere ottenuto bisogna sfruttare il seguente set di equazioni, con le seguenti probabilità di scelta:\n" +
            "1%:\n\t x = 0\n\t y = 0.16 * y\n" +
            "7%:\n\t x = 0.2 * x - 0.26 * y\n\t y = 0.23 * x + 0.22 * y + 1.6\n " +
            "7%\n\t x = -0.15 * x + 0.28 * y\n\t y = 0.26 * x + 0.24 * y + 0.44\n" +
            "85%:\n\t x = 0.85 * x + 0.04 * y\n\t y = -0.04 * x + 0.85 * y + 1.6\n" +
            "Il risultato sarà, appunto, una felce.\n" +
            "Per cambiarne la forma è necessario cambiare i coefficienti delle varie equazioni.\n" +
            "Il range del risultato varia per la x da 0 a 2.83, per la y da 0 a 9.999,\n" +
            "per riprodurla in scala maggiore occorre quindi operare delle trasformazioni opportune al risultato desiderato.\n";

        return ris;

    }
    public static string Description()
    {
        string ris;
        ris = "";  //TODO   

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "バルネイーのフェーンは独特な自己相似図形です。\n" +
            "使い方は：\n\t" +
            "蓋然性  xの式                      yの式  \n" +
            "1%:     x = 0                     y = 0.16 * y\n" +
            "7%:     x = 0.2 * x - 0.26 * y    y = 0.23 * x + 0.22 * y + 1.6\n " +
            "7%:     x = -0.15 * x + 0.28 * y  y = 0.26 * x + 0.24 * y + 0.44\n" +
            "85%:    x = 0.85 * x + 0.04 * y   y = -0.04 * x + 0.85 * y + 1.6\n" +
            "xの範囲は \n" +  //TODO
            "xの範囲は \n" +  //TODO
              "この羊歯は十万点。。";

        return ris;
    }
}

class Mandelbrot
{
    const double MaxValueExtent = 2.0;
    

    static double CalcMandelbrotSetColor(ComplexNumber c)
    {
        
        const int MaxIterations = 1000;
        const double MaxNorm = MaxValueExtent * MaxValueExtent;

        int iteration = 0;
        ComplexNumber z = new ComplexNumber();
        do
        {
            z = z * z + c;
            iteration++;
        } while (z.Norm() < MaxNorm && iteration < MaxIterations);

        if (iteration < MaxIterations)
            return (double)iteration / MaxIterations;
        else
            return 0; // black
    }

    public static void Draw(Bitmap bitmap)
    {
        
        double scale = 2 * MaxValueExtent / Math.Min(bitmap.Width, bitmap.Height);
        
        for (int i = 0; i < bitmap.Height; i++)
        {
            double y = (bitmap.Height / 2 - i) * scale;
            for (int j = 0; j < bitmap.Width; j++)
            {
                

                lock (bitmap)
                {
                    double x = (j - bitmap.Width / 2) * scale;
                    double color = CalcMandelbrotSetColor(new ComplexNumber(x, y));
                    bitmap.SetPixel(j, i, GetColor(color));
                }
               
            }
        }
    }

    static Color GetColor(double value)
    {
        const double MaxColor = 256;
        const double ContrastValue = 0.2;
        return Color.FromArgb(0, 0,
            (int)(MaxColor * Math.Pow(value, ContrastValue)));
    }

    public static string Descrizione()
    {
        string ris;
        ris = "Il set di Mandelbrot è un frattale piuttosto bizarro.\n" +
            "Per realizzarlo non basta più l'insieme dei numeri reali,\n ma occorre spostarsi nel piano di Gauss.\n" +
            "Per realizzarlo occorre iterare la seguente funzione:\n" +
            "\tz(0) = 0\n\tz(n+1) = [z(n)]^(2)+c\n" +
            "Si colorano di nero i punti in cui la funzione dopo n iterazioni smette di crescere,\n" +
            "si colorano gli altri punti con colori di intensità crescente a seconda della velocità di crescita della funzione.\n" +
            "La particolarità di questa funzione sta in quello che succede nei bordi." +
            " "; //TODO but how?

        return ris;

    }
    public static string Description()
    {
        string ris;
        ris = ""; //TODO

        return ris;
    }
    public static string 叙述()
    {
        string ris;

        ris = "マンダブロットのセットは奇妙な自己相似図形です。創造は優しいですから。\n" +
              "　複素平面に作らなければなりません：\n\t" +
              "xの式は " + //TODO
              "" +  //TODO IF THEN COLORI
              "。\n";

              

        return ris;
    } //TODO


} // funziona, da commentare
struct ComplexNumber
{
    public double Re;
    public double Im;

    public ComplexNumber(double re, double im)
    {
        this.Re = re;
        this.Im = im;
    }

    public static ComplexNumber operator +(ComplexNumber x, ComplexNumber y)
    {
        return new ComplexNumber(x.Re + y.Re, x.Im + y.Im);
    }

    public static ComplexNumber operator *(ComplexNumber x, ComplexNumber y)
    {
        return new ComplexNumber(x.Re * y.Re - x.Im * y.Im,
            x.Re * y.Im + x.Im * y.Re);
    }

    public double Norm()
    {
        return Re * Re + Im * Im;
    }
}
// struct per evitare NullPointerEx