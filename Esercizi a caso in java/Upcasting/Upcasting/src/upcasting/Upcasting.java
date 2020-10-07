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
public class Upcasting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Figure     f ;   //classe astratta non istanziabile
      Rettangolo r = new Rettangolo(20.0,40.0);
      Cerchio    c = new Cerchio(15.0);
        
      System.out.println();
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("+  BINDING STATICO - metodo da invocare determinato compile time            +");
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println();
        
             
      r.print();
      System.out.println(r.area());
      System.out.println();
      System.out.println();
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println("+Polimorfismo - BINDING DINAMICO - metodo da invocare determinato a run time+");
      System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      System.out.println();
      
      f=r;                          //binding dinamico run time
      f.print();                    
      System.out.println(f.area()); //richiama il metodo dell'oggetto r
      System.out.println();
      f=c;
      f.print();
      System.out.println(f.area());
       
    }
    
}
