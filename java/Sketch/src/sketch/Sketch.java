/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sketch;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;



/**
 *
 * @author m.alhabshi
 */
public class Sketch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        HashMap hm = new HashMap();
        CountDownLatch cdl = new CountDownLatch(10);
        Deque<Integer> d1 = new ArrayDeque();
        d1.add(Integer.valueOf(1));
        d1.add(Integer.valueOf(2));
        d1.add(Integer.valueOf(3));
        d1.add(Integer.valueOf(4));
        
        Deque<Integer> d2 = new ArrayDeque();
        Deque<Integer> d3 = new ArrayDeque();
        
        int lastMoved = 0;
        
        for (Iterator<Integer> it = d1.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
        System.out.println("------");
        while(d3.size() < 4){
            if(d2.isEmpty() || isBigger(d2.peek(), d1.peek()) && lastMoved != 1){
                d2.addFirst(d1.pop());
                lastMoved = 1;
            }else if(d3.isEmpty() || isBigger(d3.peek(), d1.peek()) && lastMoved != 1){
                d3.addFirst(d1.pop());
                lastMoved = 1;
            }else if(d3.isEmpty() || isBigger(d3.peek(), d2.peek()) && lastMoved != 2){
                d3.addFirst(d2.pop());
                lastMoved = 2;
            }else if(d1.isEmpty() || isBigger(d1.peek(), d2.peek()) && lastMoved != 2){
                d1.addFirst(d2.pop());
                lastMoved = 2;
            }else if(d1.isEmpty() || isBigger(d1.peek(), d3.peek()) && lastMoved != 3){
                d1.addFirst(d3.pop());
                lastMoved = 3;
            }
            
        }
        System.out.println("finish");
        for (Iterator<Integer> it = d3.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    
    }
    
    public static boolean isBigger(Integer is, Integer than){
        if(is == null || than == null){
            return false;
        }
        return is > than;
    }
    
    
}
