/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project.euler;

/**
 *
 * @author m.alhabshi
 */
public class Problem15 {
    static int size = 20;
    public static int[][] grid;
    public static long routes = 0;
    public static long hits = 0;
    
    public static void main(String[] args) {
        //init grid
        grid = new int [size][size];
        
        countPaths(0,0, "0.0 ");
        System.out.println("routes: "+routes);
        System.out.println("hits: "+hits);
    }

    private static void countPaths(int x, int y, String path) {
        if(x < grid.length-1){
            routes++;
            countPaths(x+1,y, path+x+"."+y+" ");
        }
        if(y < grid.length-1){
            routes++;
            countPaths(x,y+1, path+x+"."+y+" ");
        }
        if(x == grid.length-1 && y == grid.length-1){
            hits++;
            if(hits % 1000000 == 0){
                System.out.println("hits: "+hits);
            }
//            System.out.println("x: "+x);
//            System.out.println(path);
        }
    }
    
}
