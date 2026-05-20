package codingbat;


/**
 *
 Given an array of ints length 3, return a new array with the elements in reverse order, so {1, 2, 3} becomes {3, 2, 1}.
 reverse3([1, 2, 3]) → [3, 2, 1]
 reverse3([5, 11, 9]) → [9, 11, 5]
 reverse3([7, 0, 0]) → [0, 0, 7]
 */
public class Reverse3 {
    public static void main(String[] args) {
        int [] test = reverse(new int[]{1, 2, 3});
        for (int i : test){
            System.out.print( i + ", ");
        }

    }

    public static int[] reverse(int[] array){
        int[] reverseArray = new int[array.length];
        int length = array.length - 1;
        for (int i = 0 ; i < array.length; i++){
            reverseArray[i] = array[length - i];
        }
        return reverseArray;
    }
}
