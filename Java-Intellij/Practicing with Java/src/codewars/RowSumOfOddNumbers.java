/**
 *
 * Given the triangle of consecutive odd numbers:
 *
 *              1
 *           3     5
 *        7     9    11
 *    13    15    17    19
 * 21    23    25    27    29
 * ...
 * Calculate the sum of the numbers in the nth row of this triangle (starting at index 1) e.g.: (Input --> Output)
 *
 * 1 -->  1
 * 2 --> 3 + 5 = 8
 */

package codewars;

public class RowSumOfOddNumbers {
    public static void main(String[] args) {
      System.out.println(rowSumOfOddNum(2));

    }
    public static int rowSumOfOddNum(int n){
//        return n * n * n;
        return (int) Math.pow(n, 3);
    }
}
