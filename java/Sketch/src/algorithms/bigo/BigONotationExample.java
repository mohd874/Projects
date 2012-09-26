/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.bigo;

import java.util.Random;

/**
 *
 * @author m.alhabshi
 */
public class BigONotationExample {

    private static int[] generateNumbers(int i) {
        Random ran = new Random(i);
        int[] list = new int[i];
        for (int j = 0; j < list.length; j++) {
            list[j] = ran.nextInt();
        }
        return list;
    }
    static int[] list;
    static int x, y;

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        float time;
        for (int i = 1000; i <= 100000; i = i * 10) {
            sb.append("i: "+i);
            list = generateNumbers(i);
            time = System.nanoTime();
            CompareSmallestNumber(list);
            time = (System.nanoTime() - time)/1000000;
            sb.append("\nCompareSmallestNumber: "+time);
            
            time = System.nanoTime();
            CompareToAllNumbers(list);
            time = (System.nanoTime() - time)/1000000;
            sb.append("\nCompareToAllNumbers: "+time);
            
            sb.append("\n------------------------------\n");
        }
        System.out.println(sb);
    }

    static int CompareToAllNumbers(int array[]) {
        boolean isMin;

        int x, y;

        // iterate through each

        for (x = 0; x < array.length; x++) {
            isMin = true;

            for (y = 0; y < array.length; y++) {

                /* compare the value in array[x] to the other values
                if we find that array[x] is greater than any of the values
                in array[y] then we know that the value in array[x] is not the
                minimum
                
                remember that the 2 arrays are exactly the same, we are just 
                taking out one value with index 'x' and comparing to the other
                values in the array with index 'y'
                 */

                if (array[x] > array[y]) {
                    isMin = false;
                }

            }

            if (isMin) {
                break;
            }
        }

        return array[x];
    }

    static int CompareSmallestNumber(int array[]) {
        int x, curMin;

        // set smallest value to first item in array
        curMin = array[0];

        // iterate through array to find smallest value
        for (x = 1; x < array.length; x++) {
            if (array[x] < curMin) {
                curMin = array[x];
            }
        }

        // return smallest value in the array
        return curMin;
    }
}
