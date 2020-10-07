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
public class Rettangolo extends Figure{
    private double lato1;
    private double lato2;
    public Rettangolo (double l1, double l2){
      lato1=l1;
      lato2=l2;
      setColor("yellow");
    }
    public Rettangolo(){
       lato1=1.0;
       lato2=2.0;
       setColor("blue");
    };
  public double area(){
      return lato1*lato2;
  }
  
  public double perimetro(){
      return 2*(lato1+lato2);
  }
  
  public void print(){
      System.out.println("Sono un rettangolo di colore: " + getColor());
  }
}

