package codility;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Find the smallest positive integer
 */

public class FindSmallestPositiveNum {
    public static void main(String[] args) {
        int[] num = {1, 3, 6, 4, 1, 2};
        System.out.println(solution(num));

    }

    public static int solution(int[] A){
        Set<Integer> hashset = new HashSet<>();
        for (int i : A){
            if (i > 0){
                hashset.add(i);
            }

        }
        int smallest = 1;
        while(hashset.contains(smallest)){
            smallest++;
        }
        return smallest;
    }
}
