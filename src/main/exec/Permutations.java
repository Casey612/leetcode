package exec;


import java.util.*;

/**
 * @author yuzhe
 * @since 3/13/19
 */
public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        } else if (nums.length == 1) {
            List<Integer> array = new ArrayList<>();
            array.add(nums[0]);
            result.add(array);
        } else {
            for (int i = 0; i < nums.length; i++) {
                int[] subNums = copyExpectTarget(nums, nums[i]);
                List<List<Integer>> subResults = permute(subNums);
                for (List<Integer> subResult : subResults) {
                    subResult.add(0, nums[i]);
                    result.add(subResult);
                }
            }
        }
        return result;
    }

    private static int[] copyExpectTarget(int[] nums, int target) {
        int length = nums.length - 1;
        int[] result = new int[length];
        int index = 0;
        for (int num : nums) {
            if (num != target) {
                result[index] = num;
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
    }

}
