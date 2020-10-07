/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package untitled.src.com.company;

/**
 *
 * @author utente
 */
public class CD {
    //property
    private Musica[] Volume;
    private static final int DIM_MAX = 80;
    private String Nome;
    //metodi
    public Musica getTraccia(int n)
    {
        return Volume[n];
    }
    public void SostituisciTraccia(int posizione,Musica m)
    {
        Volume[posizione] = m;
    }
    public void RimuoviTraccia(int n)
    {
        Volume[n]= null;
    }
    public void InserisciTraccia(Musica m)
    {
        for(int i=0;i<DIM_MAX;i++)
        {
            if(Volume[i] == null)
            {
                Volume[i] = m;
                break;
            }
            else
                i++;
        }
    }
    public void CalcolaDurata()
    {
        long durataFin = 0;
        for(int i=0;i<DIM_MAX;i++)
        {
            if(Volume[i] == null)
                i++;
            else
               durataFin += Volume[i].getDurata();
        }
        System.out.print(durataFin);
        
    }
    //costruttori
    public CD()    //base
    {
        Volume = new Musica[DIM_MAX];
        Nome = "CD senza Nome";
    }
    public CD(String m) 
    {
        Volume = new Musica[DIM_MAX];
        Nome = m;
    }    
    public CD(CD a) //copia
    {
        Volume = a.getVolume();
        Nome = a.getNome();
    }

    //get set
    public String getNome() {
        return Nome;
    }   
    public void setNome(String Nome) {
        this.Nome = Nome;
    }
    public Musica[] getVolume() {
        return Volume;
        
    }
    public void setVolume(Musica[] Volume) {
        this.Volume = Volume;
    }
}
