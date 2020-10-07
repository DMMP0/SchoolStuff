/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazzino37;

/**
 *
 * @author dpana
 */
public class Prodotto {
    public enum cat{Audio, Video, Telefonia, Informatica};
    private cat categoria;
    private String codice;
    private String marca;
    private String modello;
    private String descrizione;
    private float prezzo;
    private int quantità;
    
    public Prodotto(){
        super();
    }
    public Prodotto(String categoria, String cod, String marca, String modello, String descr, float prezzo, int qta){
        super();
        this.categoria = cat.valueOf(categoria);
        this.codice=cod;
        this.marca=marca;
        this.modello=modello;
        this.descrizione=descr;
        this.prezzo=prezzo;
        this.quantità=qta;
        
    }

    public cat getCategoria() {
        return categoria;
    }

    public void setCategoria(cat categoria) {
        this.categoria = categoria;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantità() {
        return quantità;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }
    
    
    
}
