/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

/**
 *
 * @author De Marchi Maurizio
 */
abstract class Scuola
{
    public static final int ValStudEl = 125;       //Valore per ogni studente delle elementari
    public static final int ValSedEl = 9000;       //Valore per ogni sede aggiuntiva delle elementari
    public static final int ValStudMed = 150;      //Valore per ogni studente delle medie
    public static final int ValLabMed = 1100;      //Valore per ogni laboratorio delle medie
    public static final int ValSedMed = 9000;      //Valore per ogni sede aggiuntiva delle medie
    public static final int ValClassSupTec = 3500; //Valore per ogni Classe delle Superiori-Tecnici
    public static final int ValLabSupTec = 6000;   //Valore per ogni Laboratorio delle Superiori-Tecnici
    public static final int ValClassSupPro = 2400; //Valore per ogni Classe delle Superiori-Professionali
    public static final int ValLabSupPro = 3000;   //Valore per ogni Laboratorio delle Superiori-Professionali
    
    public String Codice;
    public String Nome;
    public String Indirizzo;
    public String Città;
    public int Studenti;
    public int Classi;
    public int NSediAggiuntive;
    public int NLabs; //Numero Laboratori

    public String getCodice() {
        return Codice;
    }
    public void setCodice(String codice) {
        Codice = codice;
    }
    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }
    public String getIndirizzo() {
        return Indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        Indirizzo = indirizzo;
    }
    public String getCittà() {
        return Città;
    }
    public void setCittà(String città) {
        Città = città;
    }
    public int getStudenti() {
        return Studenti;
    }
    public void setStudenti(int studenti) {
        Studenti = studenti;
    }
    public int getClassi() {
        return Classi;
    }
    public void setClassi(int classi) {
        Classi = classi;
    }
    public int getNSediAggiuntive() {
        return NSediAggiuntive;
    }
    public void setNSediAggiuntive(int NSediAggiuntive) {
        this.NSediAggiuntive = NSediAggiuntive;
    }
    public int getNLabs() {
        return NLabs;
    }
    public void setNLabs(int NLabs) {
        this.NLabs = NLabs;
    }

    public Scuola() //base
    {
        Codice = "0";
        Nome = "Scuola";
        Indirizzo = "Nowhere";
        Città = "Redacted";
        Studenti = 0;
        Classi = 0;
        NSediAggiuntive = 0;
        NLabs = 0;
        
    }
    public Scuola(String c,String n,String i,String cit,int s,int cl,int ns,int nl) //con parametri
    {
        Codice = c;
        Nome = n;
        Indirizzo = i;
        Città = cit;
        Studenti = s;
        Classi = cl;
        NSediAggiuntive = ns;
        NLabs = nl;
        
    }

    public Scuola(Scuola S) //base
    {
        Codice = S.getCodice();
        Nome = S.getNome();
        Indirizzo = S.getIndirizzo();
        Città= S.getCittà();
        Studenti = S.getStudenti();
        Classi = 0;
        NSediAggiuntive = 0;
        NLabs = 0;

    }

    public void print()
    {
        System.out.print("Nome: "+ Nome + " Codice: "+ Codice +" Indirizzo: " + Indirizzo +" Città: " + Città +" Numero Studenti: " + Studenti + " Numero Classi: " + Classi + " Sedi Aggiuntive: " + NSediAggiuntive + " Numero Laboratori: " + NLabs + "\n" );
    }
    
    public abstract int ContributoAnnuale(); //in euro
    
    
    
}

class Elementare extends Scuola
{
    public Elementare()
    {
        super();
        Nome = "Elementare";
    }
    public Elementare(String c,String n,String i,String cit,int s,int cl,int ns,int nl)
    {
        super(c,n,i,cit,s,cl,ns,nl);
    }
    public  Elementare(Elementare e)
    {
      super(e);
    }

    @Override
    public int ContributoAnnuale()
    {
        return ((Studenti*Scuola.ValStudEl)+(NSediAggiuntive*Scuola.ValSedEl));
    }
}

class Media extends Scuola
{
    public Media()
    {
        super();
        Nome = "Media";
    }
    public Media(String c,String n,String i,String cit,int s,int cl,int ns,int nl)
    {
        super(c,n,i,cit,s,cl,ns,nl);
    }
    public  Media(Media e)
    {
        super(e);
    }

    @Override
    public int ContributoAnnuale()
    {
        return ((Studenti*Scuola.ValStudMed)+(NSediAggiuntive*Scuola.ValSedMed)+(Scuola.ValLabMed * NLabs));
    }
}

abstract class Superiori extends Scuola
{
    public Superiori()
    {
        super();
        Nome = "Superiore";
    }
    public Superiori(String c,String n,String i,String cit,int s,int cl,int ns,int nl)
    {
        super(c,n,i,cit,s,cl,ns,nl);
    }
    public  Superiori(Superiori e)
    {
        super(e);
    }
}

class Liceo extends Superiori
{
    public Liceo()
    {
        super();
        Nome = "Liceo";
    }
    public Liceo(String c,String n,String i,String cit,int s,int cl,int ns,int nl)
    {
        super(c,n,i,cit,s,cl,ns,nl);
    }
    public  Liceo(Liceo e)
    {
        super(e);
    }
    @Override
    public int ContributoAnnuale()
    {
        return ((Studenti*Scuola.ValStudMed)+(Scuola.ValLabMed * NLabs));
    }
}

class Tecnico extends Superiori
{
    public Tecnico()
    {
        super();
        Nome = "Liceo";
    }
    public Tecnico(String c,String n,String i,String cit,int s,int cl,int ns,int nl)
    {
        super(c,n,i,cit,s,cl,ns,nl);
    }
    public Tecnico(Tecnico e)
    {
        super(e);
    }

    @Override
    public int ContributoAnnuale()
    {
        return ((Classi*Scuola.ValClassSupTec)+(Scuola.ValLabSupTec * NLabs));
    }
}

class Professionale extends Superiori
{
    public Professionale()
    {
        super();
        Nome = "Professionale";
    }
    public Professionale(String c,String n,String i,String cit,int s,int cl,int ns,int nl)
    {
        super(c,n,i,cit,s,cl,ns,nl);
    }
    public  Professionale(Professionale e)
    {
        super(e);
    }
    @Override
    public int ContributoAnnuale()
    {
        return ((Classi*Scuola.ValClassSupPro)+(Scuola.ValLabSupPro * NLabs));
    }
}        

public class Test
{
   public static void main(String[] args)
   {
       int dim = 10;
      Scuola[] a = new Scuola[dim];
       Elementare a1 = new Elementare();
       Elementare a2 = new Elementare("lll","2","3","2",2,3,4,5);
       Elementare a3 = new Elementare(a2);
       a[1] = a1;
       a[2] = a2;
       a[3]=  a3;
       Media b1 = new Media();
       Media b2 = new Media("lll","2","3","2",2,3,4,5);
       Media b3 = new Media(b1);
       a[4] = b1;
       a[5] = b2;
       a[6]=  b3;
       Superiori c1;
       Superiori c2;
       Superiori c3;
       c1 = new Liceo();
       c2 = new Tecnico();
       c3 = new Professionale();
       a[0] = c1;
       a[7] = c2;
       a[8] = c3;
       a[9] = null;

       for(int j = 0; j < dim;j++)
       {
           if(a[j] != null)
           { System.out.print("["+j+"] "); a[j].print(); System.out.print("   Contributo Annuale: " + a[j].ContributoAnnuale()+"\n");
           }
           else
           {
               System.out.print("["+j+"]"+" Null");
           }

       }
   }
    
}

 