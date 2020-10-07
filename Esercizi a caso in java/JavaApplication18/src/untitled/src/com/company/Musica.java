package untitled.src.com.company;


import jaco.mp3.player.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class Musica
{
    public static String[] PercorsoCanzoni = {"C:\\Users\\utente\\Desktop\\untitled\\untitled\\216-fefe-vpc_(vilain_petit_canard).mp3","C:\\Users\\utente\\Desktop\\untitled\\untitled\\Fever_The_Ghost.mp3","C:\\Users\\utente\\Desktop\\untitled\\untitled\\Let_Me_Hear.mp3","C:\\Users\\utente\\Desktop\\untitled\\untitled\\PINK_SEASON-THE PROPHECY.mp3","C:\\Users\\utente\\Documents\\NetBeansProjects\\JavaApplication18\\src\\untitled\\【v flower】人間発電所【オリジナル曲】.mp3","C:\\Users\\utente\\Desktop\\untitled\\untitled\\Secret_God_Matara.mp3"};
    //[fefe:3.52,fever the ghost:4.4,let me hear:3.54, The Prophecy:12.26,?????:6.07,secret god matara:6.6]
    private static final PlayerThread player_thread = new PlayerThread();
    //property
    private String Titolo;
    private String Sottotitolo;
    private int Classificazione; //rating in stelle
    private String Commenti;
    private String[] Artisti_Partecipanti;
    private String Artista_Album;
    private String Album;
    private int Anno;
    private int Numero;//ordine nell' album
    private String Genere;
    private long Durata;// in secondi
    private int Velocita_In_Bit;
    private String Editore;
    private String Autore_Codifica;
    private String URL_Autore;
    private boolean hasCopyright;
    private boolean isR18;
    private String Motivo_R18;
    private String[] Compositori;
    private String[] Conduttori;
    private String Descrizione_Gruppo;
    private String Punto;
    private String Umore;
    private String Serie;
    private String Chiave_Iniziale;
    private boolean isParte_Di_Un_Album;
    private File FileMusicale;
    private JButton playButton;
    private JButton STOPButton;

    //get e set
    public String getTitolo() {
        return Titolo;
    }
    public void setTitolo(String titolo) {
        Titolo = titolo;
    }
    public String getSottotitolo() {
        return Sottotitolo;
    }
    public void setSottotitolo(String sottotitolo) {
        Sottotitolo = sottotitolo;
    }
    public int getClassificazione() {
        return Classificazione;
    }
    public void setClassificazione(int classificazione) {
        if(classificazione<=5 && classificazione>= 0)
           Classificazione = classificazione;
        else
            Classificazione = 0;
    }
    public String getCommenti() {
        return Commenti;
    }
    public void setCommenti(String commenti) {
        Commenti = commenti;
    }
    public String[] getArtisti_Partecipanti() {
        return Artisti_Partecipanti;
    }
    public void setArtisti_Partecipanti(String[] artisti_Partecipanti) {
        Artisti_Partecipanti = artisti_Partecipanti;
    }
    public String getArtista_Album() {
        return Artista_Album;
    }
    public void setArtista_Album(String artista_Album) {
        Artista_Album = artista_Album;
    }
    public String getAlbum() {
        return Album;
    }
    public void setAlbum(String album) {
        Album = album;
    }
    public int getAnno() {
        return Anno;
    }
    public void setAnno(int anno) {
        Anno = anno;
    }
    public int getNumero() {
        return Numero;
    }
    public void setNumero(int numero) {
        if(numero >= 0)
            Numero = numero;
        else
            Numero = 0;
    }
    public String getGenere() {
        return Genere;
    }
    public void setGenere(String genere) {
        Genere = genere;
    }
    public long getDurata() {
        return Durata;
    }
    public void setDurata(long durata) {
        Durata = durata;
    }
    public int getVelocita_In_Bit() {
        return Velocita_In_Bit;
    }
    public void setVelocita_In_Bit(int velocita_In_Bit) {
        Velocita_In_Bit = velocita_In_Bit;
    }
    public String getEditore() {
        return Editore;
    }
    public void setEditore(String editore) {
        Editore = editore;
    }
    public String getAutore_Codifica() {
        return Autore_Codifica;
    }
    public void setAutore_Codifica(String autore_Codifica) {
        Autore_Codifica = autore_Codifica;
    }
    public String getURL_Autore() {
        return URL_Autore;
    }
    public void setURL_Autore(String URL_Autore) {
        this.URL_Autore = URL_Autore;
    }
    public boolean hasCopyright() {
        return hasCopyright;
    }
    public void setCopyright(boolean copyright) {
        hasCopyright = copyright;
    }
    public boolean isR18() {
        return isR18;
    }
    public void setR18(boolean r18) {
        isR18 = r18;
    }
    public String getMotivo_R18() {
        return Motivo_R18;
    }
    public void setMotivo_R18(String motivo_R18) {
        Motivo_R18 = motivo_R18;
    }
    public String[] getCompositori() {
        return Compositori;
    }
    public void setCompositori(String[] compositori) {
        Compositori = compositori;
    }
    public String[] getConduttori() {
        return Conduttori;
    }
    public void setConduttori(String[] conduttori) {
        Conduttori = conduttori;
    }
    public String getDescrizione_Gruppo() {
        return Descrizione_Gruppo;
    }
    public void setDescrizione_Gruppo(String descrizione_Gruppo) {
        Descrizione_Gruppo = descrizione_Gruppo;
    }
    public String getPunto() {
        return Punto;
    }
    public void setPunto(String punto) {
        Punto = punto;
    }
    public String getUmore() {
        return Umore;
    }
    public void setUmore(String umore) {
        Umore = umore;
    }
    public String getSerie() {
        return Serie;
    }
    public void setSerie(String serie) {
        Serie = serie;
    }
    public String getChiave_Iniziale() {
        return Chiave_Iniziale;
    }
    public void setChiave_Iniziale(String chiave_Iniziale) {
        Chiave_Iniziale = chiave_Iniziale;
    }
    public boolean getParte_Di_Un_Album() {
        return isParte_Di_Un_Album;
    }
    public void setParte_Di_Un_Album(boolean parte_Di_Un_Album) {
        isParte_Di_Un_Album = parte_Di_Un_Album;
    }
    public File getFileMusicale() {
        return FileMusicale;
    }
    public void setFileMusicale(File fileMusicale) {
        FileMusicale = fileMusicale;
    }

    //costruttori
    public Musica() //base
    {
        Titolo = "Senza Titolo";
        Sottotitolo = "";
        Classificazione = 0;
        Commenti = "";
        Artisti_Partecipanti = new String[2];
        Artista_Album = "";
        Album = "";
        Calendar now = Calendar.getInstance();//
        Anno = now.get(Calendar.YEAR);  //
        Numero = 0;
        Genere = "";
        Durata = 0;
      
        hasCopyright = false;
        isParte_Di_Un_Album = false;
        isR18 = false;
        FileMusicale = null;



    }
    public Musica(String titolo,String[] artisti_Partecipanti,long durata,boolean hascopyright) //semplice senza file
    {
        Titolo = titolo;
        Sottotitolo = "";
        Classificazione = 0;
        Commenti = "";
        Artisti_Partecipanti = artisti_Partecipanti;
        Artista_Album = "";
        Album = "";
        Calendar now = Calendar.getInstance();
        Anno = now.get(Calendar.YEAR);
        Numero = 0;
        Genere = "";
        Durata = durata;
      
        hasCopyright = hascopyright;
        isParte_Di_Un_Album = false;
        isR18 = false;
        FileMusicale = null;

    }
    public Musica(File musicale,long durata)
    {
        FileMusicale = musicale;
        Durata = durata;
        player_thread.run(5);  //qui per selezionare la musica

            
        
     /*   STOPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player_thread.stop();
            }
        });*/
    } //quì dentro
    public Musica(String titolo,String[] artisti_Partecipanti,long durata,boolean hascopyright,File musicale) //semplice
    {
        Titolo = titolo;
        Sottotitolo = "";
        Classificazione = 0;
        Commenti = "";
        Artisti_Partecipanti = artisti_Partecipanti;
        Artista_Album = "";
        Album = "";
        Calendar now = Calendar.getInstance();//
        Anno = now.get(Calendar.YEAR);  //
        Numero = 0;
        Genere = "";
        Durata = durata;
     
        hasCopyright = hascopyright;
        isParte_Di_Un_Album = false;
        isR18 = false;
        FileMusicale = musicale;

    }
    
    public Musica(Musica m) //copia
    {
        Titolo = m.Titolo;
        Sottotitolo=m.Sottotitolo;
        Classificazione=m.Classificazione; //rating in stelle
        Commenti=m.Commenti;
        Artisti_Partecipanti=m.Artisti_Partecipanti;
        Artista_Album=m.Artista_Album;
        Album=m.Album;
        Anno=m.Anno;
        Numero=m.Numero;//ordine nell' album
        Genere=m.Genere;
        Durata=m.Durata;// in minuti
        Velocita_In_Bit=m.Velocita_In_Bit;
        Editore=m.Editore;
        Autore_Codifica=m.Autore_Codifica;
        URL_Autore=m.URL_Autore;
        hasCopyright=m.hasCopyright;
        isR18=m.isR18;
        Motivo_R18=m.Motivo_R18;
        Compositori=m.Compositori;
        Conduttori=m.Conduttori;
        Descrizione_Gruppo=m.Descrizione_Gruppo;
        Punto=m.Punto;
        Umore=m.Umore;
        Serie=m.Serie;
        Chiave_Iniziale=m.Chiave_Iniziale;
        isParte_Di_Un_Album=m.isParte_Di_Un_Album;
        FileMusicale=m.FileMusicale;

    }

    //metodi
    public void Play()
    {
      new MP3Player( FileMusicale).play();
    }
    public static void Play(Musica m)
    {
        new MP3Player(m.FileMusicale).play();
    }
    public void Stampa()
    {
        System.out.print("Titolo: "+Titolo+"\n");
        System.out.print("Autore "+Artisti_Partecipanti[0]+"\n");
        System.out.print("Durata: "+Durata+" secondi"+"\n");
        System.out.print("Cantante: "+Artisti_Partecipanti[1]+"\n");
    }

    public static void main(String[] args) throws InterruptedException {



        File m = new File(PercorsoCanzoni[5]); //selezionare percorso dall' array sopra
        String[] artisti = {"ghdf","loloilldo"};
        String[] artisti2 = {"Fear and loathing in las vegas"};
    	Musica a,b,c,d;
	    a = new Musica();
	    b= new Musica("Filthy Frank",artisti,(12*60+34),false);
	    c= new Musica(m,(6*60+6));   //impostare durata musica
	 //   c.playButton.doClick();
         System.out.print("Musica 1:\n");
         a.Stampa();
         System.out.print("Musica 2:\n");
         b.Stampa();
         CD lol = new CD();
         lol.InserisciTraccia(a);
         lol.InserisciTraccia(b);
         lol.InserisciTraccia(c);
         lol.RimuoviTraccia(3);
         lol.SostituisciTraccia(3, new Musica());
         Archivio Ar = new Archivio();
         Ar.InsertCd(lol);
         System.out.print(Ar.getContenuto(0).getNome());
         TimeUnit.SECONDS.sleep((c.Durata));
        




    }

}


class PlayerThread extends Thread{
    public void run(int n){
        try{
            Musica a = new Musica();
           MP3Player mp3_player = new MP3Player(new File(a.PercorsoCanzoni[n]));
            mp3_player.play();
        }catch(Exception e){ System.err.println(e);}
    }
}

