package codewars;


import java.util.HashMap;
import java.util.Map;

/**
 * There is an array with some numbers. All numbers are equal except for one. Try to find it!
 *
 * Kata.findUniq(new double[]{ 1, 1, 1, 2, 1, 1 }); // => 2
 * Kata.findUniq(new double[]{ 0, 0, 0.55, 0, 0 }); // => 0.55
 * It’s guaranteed that array contains at least 3 numbers.
 *
 * The tests contain some very huge arrays, so think about performance
 *
 */


public class FindUniqueNumber {

    public static void main(String[] args) {
        System.out.println(findUniq(new double[]{3.0, 3.0,  3.0, 4.0}));
    }

    public static double findUniq(double[] arr){
        double resultIfNotFound = 0;
       Map<Double, Integer> counts = new HashMap<>();
       for (double num : arr){
           counts.put(num, counts.getOrDefault(num,0) + 1);
       }
       for (Map.Entry<Double, Integer> entry : counts.entrySet()){
           if (entry.getValue() == 1) {
               return entry.getKey();
           }
       }
       return resultIfNotFound;

    }
}
