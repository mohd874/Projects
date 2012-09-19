/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.euler;

import java.math.BigDecimal;

/**
 *
 * @author m.alhabshi
 */
public class Problem16 {
    public static void main(String[] args) {
        String num = new BigDecimal(Math.pow(2, 1000)).toBigInteger().toString();
        long res = 0;
        for (int i = 0; i < num.length(); i++) {
            res += Integer.parseInt(num.charAt(i)+"");
        }
        System.out.println("res: "+res);
    }
}
