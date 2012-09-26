/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.shuffle;

import java.util.ArrayList;
import java.util.List;

/**
 * This algorithm was named after Donald Knuth. It is known as Fisher–Yates shuffle (named after Ronald Fisher and Frank Yates)
 * Source Info: http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 * @author m.alhabshi
 */
public class KnuthShuffle {
    public static void main(String[] args) {
        int size = 10;
        int [] nums = new int [size];
        List<Integer> nums2 = new ArrayList<Integer>();
        
        for (int i = 0; i < size; i++) {
            nums[i] = i;
            nums2.add(i);
        }
        
//        shuffle(nums);
        shuffle(nums2);
        
        for (int i = 0; i < size; i++) {
            System.out.println("nums: "+nums[i]);
        }
        
        for (int i = 0; i < size; i++) {
            System.out.println("nums2: "+nums2.get(i));
        }
    }
    
    private static List shuffle(List list){
        if(list.size() == 1){
            return list;
        }else{
            int rand = (int)(Math.random()*list.size());
            Object o = list.get(rand);
            list.remove(rand);
            list = shuffle(list);
            list.add(o);
            return list;
        }
    }
}
