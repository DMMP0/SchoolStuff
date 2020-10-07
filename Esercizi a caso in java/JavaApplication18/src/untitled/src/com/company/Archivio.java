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
public class Archivio {
    private static final int maxCD = 20;
    private CD[] Contenuto;
    //metodi
    public void InsertCd( CD a)
    {
        for(int i=0; i<maxCD;i++)
            if(Contenuto[i] == null)
            {
                Contenuto[i]= a;
                break;
            }
            else
            
                i++;
            
    }   
    //get set
    public CD[] getContenuto() {
        return Contenuto;
    }
    public CD getContenuto(int a) {
        return Contenuto[a];
    }
    public void setContenuto(CD[] Contenuto) {
        this.Contenuto = Contenuto;
    }
    //costruttori
    public Archivio()
    {
     Contenuto = new CD[maxCD];
    }
    public Archivio(Archivio a)
    {
        Contenuto = a.getContenuto();
    }
    
    
    
}
