package codewars;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * Write a function that takes an array of numbers (integers for the tests) and a target number.
 * It should find two different items in the array that, when added together, give the target value. The indexes of these items should then be returned in a tuple / list (depending on your language) like so: (index1, index2).
 *
 *
 * For the purposes of this kata, some tests may have multiple answers; any valid solutions will be accepted.
 *
 * The input will always be valid (numbers will be an array of length 2 or greater,
 * and all of the items will be numbers; target will always be the sum of two different items from that array).
 *
 * Based on: https://leetcode.com/problems/two-sum/
 *
 * two_sum([1, 2, 3], 4) == {0, 2}
 * two_sum([3, 2, 4], 6) == {1, 2}
 */

public class twosum {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        List<Integer> list = Arrays.stream(twoSum(nums, target)).boxed().toList();

        System.out.println(list);

    }

    public static int[] twoSum(int[]nums, int target){
        // brute-force solution
//        for (int i = 0 ; i < nums.length; i++){
//            for (int j = i + 1; j < nums.length; j++){
//                if (nums[i] + nums[j] == target){
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return new int[0];

        return IntStream.range(0, nums.length)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, nums.length)
                        .filter(j -> nums[i] + nums[j] == target)
                        .mapToObj(j -> new int[]{i,j}))
                .findFirst()
                .orElse(new int[0]);
    }
}
