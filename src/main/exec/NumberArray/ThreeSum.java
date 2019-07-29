package exec.NumberArray;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/6/19
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        int left = 0, right = nums.length - 1;
        if (nums[left] > 0 || nums[right] < 0) {
            return result;
        }
        while (left < right && nums[left] <= 0) {
            System.out.println("left: " + left + ", right:" + right);
            int sum = nums[left] + nums[right];
            int target = 0 - sum;
            List<Integer> targetIndexes = findTargetIndex(nums, target);
            //能够凑到0
            for (int index : targetIndexes) {
                //检查是否重复元素
                if (index > left && index < right) {
                    //找到解
                    List<Integer> answer = new ArrayList<>();
                    answer.add(nums[left]);
                    answer.add(target);
                    answer.add(nums[right]);
                    result.add(answer);
                    break;
                }
            }
            if (nums[right] >= 0) {
                int cur = nums[right];
                while (nums[right] == cur && left < right) {
                    right--;
                }
            }
            if (nums[right] < 0) {
                right = nums.length - 1;
                int cur = nums[left];
                while (nums[left] == cur && left < right) {
                    left++;
                }
            }
        }
        return result;
    }

    private static List<Integer> findTargetIndex(int[] nums, int target) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(array));
        System.out.println(threeSum(new int[]{-2, -4, -2}));
        System.out.println(threeSum(new int[]{0, 0, 0}));
        System.out.println(threeSum(new int[]{-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}));
    }

}
