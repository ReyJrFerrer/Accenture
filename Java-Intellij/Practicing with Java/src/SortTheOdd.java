/**
 * You will be given an array of numbers. You have to sort the odd numbers in ascending order while leaving the even numbers at their original positions.
 *
 * Examples
 * [7, 1]  =>  [1, 7]
 * [5, 8, 6, 3, 4]  =>  [3, 8, 6, 5, 4]
 * [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]  =>  [1, 8, 3, 6, 5, 4, 7, 2, 9, 0]
 */
public class SortTheOdd {
    public static void main(String[] args) {
        int[] l = {1,7};
        int[] j = {5, 8, 6, 3, 4};
        int[] x = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int [] array = sortArray(j);
        for (int i = 0 ; i < array.length; i++){
            System.out.printf(array[i] + ", ");
        }

    }

    static int[] sortArray(int[] array ){
        int[] sortedArray = new int[array.length];
        for (int i = 0 ; i < array.length; i++){
            for (int j = i + 1 ; j < array.length; j++){
                if (array[i] % 2 == 1 ){
                    if (array[i] > array[j]){
                        sortedArray[i] = array[j];
                    }
                }
                sortedArray[i] = sortedArray[j];
            }
        }

        return sortedArray;
    }
}
