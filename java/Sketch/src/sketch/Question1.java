/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.alhabshi
 */
public class Question1 {
 
     //don't mind this...delete it if you use the ConsoleIO
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     //don't mind this...delete it if you use the ConsoleIO
    public static String readString(String msg){
        System.out.println(msg);
        try {
            return br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     //don't mind this...delete it if you use the ConsoleIO
    public static int readInteger(String msg){
        try {
            return Integer.parseInt(readString(msg));
        } catch (Exception ex) {
            Logger.getLogger(Question2.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    public static void main(String[] args) {
        double price = 0;
        
        int month = readInteger("enter month number: ");
        int nAd = readInteger("how many adults: ");
        int nCh1417 = readInteger("How many children 14-17: ");
        int nCh0813 = readInteger("How many children 8-13: ");
        int nCh8 = readInteger("How many children < 8: ");
        int nights = readInteger("how many nights: ");
        
        
        //Room types...put the correct room type and correct messages
        int rType = readInteger("Select room type: ");
        if(rType == 1){
                price = nights*(1000*nAd+(1000*0.75*nCh1417)+(1000*0.5*nCh0813)+(1000*0.25*nCh8));
        }
        else
            if(rType == 2){
                price = nights*(500*nAd+(500*0.75*nCh1417)+(500*0.5*nCh0813)+(500*0.25*nCh8));
        }
        
        if(month == 1){
                price = price *0.85;
        }
        else
            if(month == 2){
                price = price * 0.7;
        }
        
        
        //Meals...put the right meals prices and messages
        int meals = readInteger("meals: ");
        
        if(meals == 1){
                price += nights*(50*nAd+(50*0.75*nCh1417)+(50*0.5*nCh0813)+(50*0.25*nCh8));
        }
        else
            if(meals == 2){
                price += nights*(20*nAd+(20*0.75*nCh1417)+(20*0.5*nCh0813)+(20*0.25*nCh8));
        }
        
        //Movies...put the right prices and messages
        int movies = readInteger("movies: "); //make this boolean
        if(movies == 1){
                price += nights*20;
        }
        
        System.out.println("price: "+price);
        
        
        //don't mind this...delete it if you use the ConsoleIO
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Question1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
