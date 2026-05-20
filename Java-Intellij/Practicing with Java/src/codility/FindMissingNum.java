package codility;

import java.util.Arrays;

/**
 * Array of n consisting of different integers is given. The array contains the integers in the range of [1, (n + 1)], which means exactly 1
 * element is missing. Your goal is to find the missing element.
 */
public class FindMissingNum {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1,2,3,5}));
    }
    public static int solution(int[] numbers){
        int n = numbers.length;
        int result = n + 1;

        for (int i = 1; i <= numbers.length ; i++){
            result ^= i ^ numbers[i - 1];
        }
        return result;


    }
}
