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
public class Cerchio extends Figure{
    
   
    private double raggio;
    
    public Cerchio (double r){
      raggio=r;
      setColor("red");
    }
    public Cerchio(){
       raggio=10.0;
       setColor("blue");
    };
  public double area(){
      return Math.PI*Math.pow(raggio, 2);
  }
  
  public double perimetro(){
      return 2*Math.PI*raggio;
  }
  
  public void print(){
      System.out.println("Sono un cerchio di colore: " + getColor());
  }


    
}
