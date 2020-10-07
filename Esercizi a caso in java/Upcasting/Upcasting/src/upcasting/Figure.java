/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upcasting;

/**
 *
 * @author dpana
 */
abstract public class Figure {
    private String color="green";

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    abstract public double area();
    abstract public double perimetro();
    public void print(){
        System.out.println("Figura di colore "+getColor());
    }
    
}


