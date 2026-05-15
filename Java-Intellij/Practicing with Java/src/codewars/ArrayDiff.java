package codewars;

/***
 * Implement a function that computes the difference between two lists.
 * The function should remove all occurrences of elements from the first list (a) that are present in the second list (b).
 * The order of elements in the first list should be preserved in the result.
 *
 * Examples
 * If a = [1, 2] and b = [1], the result should be [2].
 *
 * If a = [1, 2, 2, 2, 3] and b = [2], the result should be [1, 3].
 */

import java.util.ArrayList;

public class ArrayDiff {
    public static void main(String[] args) {
        int [] a = {1, 2, 2, 2, 3};
        int [] b = {2};
        int[] result = arrayDiff(a,b);
        System.out.println("Array Result");
        for (int i : result){
            System.out.println(i);
        }

    }
    // Intuition without using a distinct method is to use a nested for loop to individually check the occurrence
    // int b
    public static int[] arrayDiff(int[] a, int[] b)
    {
        ArrayList<Integer> distinctArray = new ArrayList<>();
        for (int i = 0 ; i < a.length; i++){
            boolean shouldAdd = true;
            for (int j = 0 ; j < b.length; j++){
                if(a[i] == b[j]){
                    shouldAdd = false;
                    break;
                 }
            }
            if (shouldAdd){
                distinctArray.add(a[i]);
            }
        }
        return arrayListToIntegerArray(distinctArray);

    }
    private static int[] arrayListToIntegerArray(ArrayList<Integer> array) {
        int[] result = new int[array.size()];
        for (int i = 0 ; i < array.size(); i++){
            result[i] = array.get(i);
        }
        return result;
    }
}
