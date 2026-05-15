package codewars;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Given an array of ones and zeroes, convert the equivalent binary value to an integer.
 *
 * Eg: [0, 0, 0, 1] is treated as 0001 which is the binary representation of 1.
 *
 * Examples:
 *
 * Testing: [0, 0, 0, 1] ==> 1
 * Testing: [0, 0, 1, 0] ==> 2
 * Testing: [0, 1, 0, 1] ==> 5
 * Testing: [1, 0, 0, 1] ==> 9
 * Testing: [0, 0, 1, 0] ==> 2
 * Testing: [0, 1, 1, 0] ==> 6
 * Testing: [1, 1, 1, 1] ==> 15
 * Testing: [1, 0, 1, 1] ==> 11
 */

public class OnesAndZeroes {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(0);
        list.add(0);
        list.add(0);
        int[] array = {1, 0, 1, 1};

        System.out.println(list);
        System.out.println(ConvertBinaryArrayToInt(array));

    }
    public static int ConvertBinaryArrayToIntList(List<Integer> array){
        int[] decimalArray = new int[array.size()];
        int n = 1;
        for (int i = array.size() - 1 ; i >= 0; i--) {
            if (array.get(i) == 1){
                    decimalArray[i] = n;
            }
            n = n * 2;
        }
        return Arrays.stream(decimalArray).sum();
    }
    public static int ConvertBinaryArrayToInt(int[] array){
        int[] decimalArray = new int[array.length];
        int n = 1;
        for (int i = array.length - 1 ; i >= 0; i--) {
            if (array[i] == 1){
                decimalArray[i] = n;
            }
            n = n * 2;
            System.out.println("N incrementer "+ n);
            System.out.println("Inside the array at index  " + i + " = " + decimalArray[i]);
        }
        return Arrays.stream(decimalArray).sum();
    }
}
