package codility;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an odd number of arrays, find the an element that does not have a pair.
 * - Each element of the array can be paired with another value with the same element, except for one element that is left unpaired
 *
 */
public class FindNoPair {
    public static void main(String[] args) {
        int[] A = {9, 3, 9, 3, 9, 7, 9};
        System.out.println(solution(A));

    }
    public static int solution(int[] A){

        // XOR Solution
       // int result = 0 ;
//        for (int num : A){
//            result ^= num;
//        }
//        return result;

        Set<Integer> hashset = new HashSet<>();
        for (int num : A){
            if (hashset.contains(num)){
                hashset.remove(num);
            } else {
                hashset.add(num);
            }
        }
        return hashset.iterator().next();

    }
}
