/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms.search;

/**
 *
 * @author m.alhabshi
 */
public class InterpolationSearchExample {

    public static void main(String[] args) {
        int[] list = {1,2,3,4,5,6,7};
        int toFind = 5;
        
        System.out.println(interpolationSearch(list, toFind));
    }
    
    public static int interpolationSearch(int[] sortedArray, int toFind) {
        // Returns index of toFind in sortedArray, or -1 if not found
        int low = 0;
        int high = sortedArray.length - 1;
        int mid;
        
        while (sortedArray[low] <= toFind && sortedArray[high] >= toFind) {
            mid = low
                    + ((toFind - sortedArray[low]) * (high - low))
                    / (sortedArray[high] - sortedArray[low]);  //out of range is possible  here

            System.out.println("mid: "+mid+" low: "+low+" high: "+high);
            if (sortedArray[mid] < toFind) {
                low = mid + 1;
            } else if (sortedArray[mid] > toFind) // Repetition of the comparison code is forced by syntax limitations.
            {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        if (sortedArray[low] == toFind) {
            return low;
        } else {
            return -1; // Not found
        }
    }
}
