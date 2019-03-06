package exec;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/6/19
 */
public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0 || nums[0] >= 0) {
            return result;
        }
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (nums[left] <= 0) {
            int sum = nums[left] + nums[right];
            int target = 0 - sum;
            int targetIndex = Arrays.binarySearch(nums, target);
            if (targetIndex != -1 && targetIndex != left && targetIndex != right) {
                //找到解
                List<Integer> answer = new ArrayList<>();
                answer.add(nums[left]);
                answer.add(nums[targetIndex]);
                answer.add(nums[right]);
                result.add(answer);
            } else if (sum > 0) {
                right--;
                if(right <= left || nums[right] < 0){

                }

            } else {
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(array));
    }

}
