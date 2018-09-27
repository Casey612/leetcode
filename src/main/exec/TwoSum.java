package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 9/27/18
 */
public class TwoSum {

    public static int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                result[1] = i;
                result[0] = map.get(another);
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] result = twoSum2(nums, target);
        System.out.println(Arrays.toString(result));
    }

}
