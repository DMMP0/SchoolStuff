/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dpana
 */
public class Entry implements java.io.Serializable {
    private String name;
    private String number;

    public Entry(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Entry(Entry entry) {
        this.name = entry.name;
        this.number = entry.number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
}
