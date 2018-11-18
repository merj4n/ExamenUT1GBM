/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidad;

import javax.swing.JTextField;

/**
 *
 * @author JRL
 */
public class UtilidadGBM {
    
     public static boolean JFEsvacio(JTextField tf){
         
         /**
          * Devuelve true si el JTexField es vacio
         
         */ 
        
        if ((tf.getText().equals("")) || (tf.getText()==null)){
            return true;
        }
        else{
            return false;
        }
    
    }
     
    public static double pasarDouble(String s){
    /**
     Devuelve -1 sino se ha introducido un número y sino pasa el string a double
    */
    double resultado=0;
     try{
             resultado=Double.parseDouble(s);
        }catch(NumberFormatException e){
           resultado=-1;
        }
      return resultado;
    }

    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
   
}
