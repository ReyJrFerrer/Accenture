package codility;

/**
 * Given K as the number of rotations to the right, rotate array A based on the K
 * Last value in the index will go back to the first index
 * return the array
 */

public class Rotations {
    public static void main(String[] args) {
        int[] A = {3,8,9,7,6};
        int[] result = solution(A, 3);


        for (int i : result){
            System.out.print(" " + i);
        }

    }
    public static int[] solution(int[] A, int K){
        boolean swapped = false;
        int temp = 0;
        // for loop to keep track on how many rotations
        for (int i = 0 ; i < K; i++){
            // for loop to rotate the elements
            for (int j = A.length - 1 ; j >= 0; j--){
                if (!swapped){
                    temp = A[j];
                    swapped = true;
                }
                if (j == 0){
                    A[j] = temp;
                } else {
                    A[j] = A[j - 1];
                }
            }
            swapped = false;
        }

        return A;

    }
}
