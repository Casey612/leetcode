package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/13/19
 */
public class PermuteUnique {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        } else if (nums.length == 1) {
            List<Integer> array = new ArrayList<>();
            array.add(nums[0]);
            result.add(array);
        } else {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], i);
                }
            }
            for (int i = 0; i < nums.length; i++) {
                int target = nums[i];
                if (map.get(target) == i) {
                    int[] subNums = copyExpectTargetIndex(nums, i);
                    List<List<Integer>> subResults = permuteUnique(subNums);
                    for (List<Integer> subResult : subResults) {
                        subResult.add(0, nums[i]);
                        result.add(subResult);
                    }
                }
            }
        }
        return result;
    }

    private static int firstIndex(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }


    private static int[] copyExpectTargetIndex(int[] nums, int targetIndex) {
        int length = nums.length - 1, index = 0;
        int[] result = new int[length];
        for (int i = 0; i < nums.length; i++) {
            if (i != targetIndex) {
                result[index] = nums[i];
                index++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
    }

}
