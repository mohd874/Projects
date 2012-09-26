/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.euler;

/**
 *
 * @author m.alhabshi
 */
public class Problem15Alt {

    static int size = 1;
    static int target = 20;
    public static long[][] grid;
    public static long routes = 0;
    public static long hits = 0;

    public static void main(String[] args) {
        //init
        grid = new long[target + 1][target + 1];

        grid[0][0] = 1;
//        grid[0][1] = 1;
//        grid[1][0] = 1;
//       
        for (int i = 1; i <= target; i++) {
            System.out.println("**********Size: "+size);
            
//            for (int i = 1; i <= size; i++) {
//                for (int j = 1; j <= i; j++) {
//                    sumNaibours(i - j, i);
//                    sumNaibours(i, i - j);
//                    sumNaibours(i, i);
//                }
//            }
//            for (int i = 1; i <= size; i++) {
                for (int j = i; j >= 1; j--) {
                    sumNaibours(i - j, i);
                    sumNaibours(i, i - j);
                    sumNaibours(i, i);
                }
//            }

            System.out.println(grid[size][size]);
            size++;
        }

    }

    private static void sumNaibours(int x, int y) {
//        System.out.println("x: " + x + " y: " + y);
//        System.out.println("1- grid[x][y]: " + grid[x][y]);
        if (x > 0) {
            grid[x][y] += grid[x - 1][y];
//            System.out.println("1- grid[x-1][y]: " + grid[x - 1][y]);
        }

        if (y > 0) {
            grid[x][y] += grid[x][y - 1];
//            System.out.println("1- grid[x][y-1]: " + grid[x][y - 1]);
        }
//        System.out.println("2- grid[x][y]: " + grid[x][y]);
    }
}
