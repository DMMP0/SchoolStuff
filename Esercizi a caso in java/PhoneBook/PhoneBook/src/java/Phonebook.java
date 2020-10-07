/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;

public class Phonebook implements Serializable {
    private HashMap<String, Entry> book;
    
    // costruttore
    public Phonebook() {
        book = new HashMap<>();
    }

    // aggiunta di una voce alla rubrica
    public void addEntry(Entry entry) throws PhonebookException {
                
        if (book.containsKey(entry.getName()))
          throw new PhonebookException();
        book.put(entry.getName(), entry);
    }

    // ricerca di un numero nella rubrica a partire dal nome
    public String getNumber(String name) throws PhonebookException {
        Entry entry = book.get(name);
        
        if (entry == null)
          throw new PhonebookException();
        else
            return entry.getNumber();
    }

    // modifica di un numero nella rubrica a partire dal nome
    public void setNumber (String name, String number) throws PhonebookException {
        Entry entry = new Entry(name, number);
        
        if (!book.containsKey(name))
          throw new PhonebookException();
        book.put(name, entry);
    }
    
    // eliminazione di una voce della rubrica a partire dal nome
    public void delEntry(String name) throws PhonebookException {
        if (!book.containsKey(name))
          throw new PhonebookException();
        book.remove(name);
    }

    // salvataggio della rubrica su file predefinito
    public void saveBook() throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("phonebook.bin"))) {
            stream.writeObject(this.book);
        }
    }

    // caricamento della rubrica da file predefinito
    public void loadBook() throws IOException {
    /*   try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("phonebook.bin"))) {
            try {
                this.book = (HashMap<String, Entry>)stream.readObject();
            }
            catch (ClassNotFoundException exception) {
            }
        }*/
    }

    public int getEntries() {
        return book.size();
    }
    
    public static void  main(String args[]) {
        Phonebook phonebook = new Phonebook();
        Entry entry1 = new Entry("Pippo", "0123456789");
        Entry entry2 = new Entry("Pluto", "1234567890");
        Entry entry3 = new Entry("Paperino", "9876543210");
        int n;
        
        try {
            phonebook.loadBook();
        } catch (IOException e) {
            System.out.println("Errore caricamento file.");
        }
        try {
            phonebook.addEntry(entry1);
        } catch (PhonebookException e) {
            System.out.println("Errore aggiunta voce.");
        }
        try {
            phonebook.addEntry(entry2);
        } catch (PhonebookException e) {
            System.out.println("Errore aggiunta voce.");
        }
        try {
            phonebook.addEntry(entry3);
        } catch (PhonebookException e) {
            System.out.println("Errore aggiunta voce.");
        }
        n = phonebook.getEntries();
        System.out.println("Nella rubrica ci sono "+ n + " voci.");
        try {
            phonebook.saveBook();
        } catch (IOException e) {
            System.out.println("Errore salvataggio file.");
        }
        try {
            String number = phonebook.getNumber("Pluto");
            System.out.println("Il numero di Pluto Ã¨ " + number +".");
        } catch (PhonebookException e) {
            System.out.println("Errore ricerca voce per nome.");
        }
        try {
            phonebook.delEntry("Paperino");
        } catch (PhonebookException e) {
            System.out.println("Errore eliminazione voce.");
        }
        n = phonebook.getEntries();
        System.out.println("Nella rubrica ci sono "+ n + " voci.");
    }
}
