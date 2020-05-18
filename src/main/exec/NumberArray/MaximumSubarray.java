package exec.NumberArray;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 3/14/19
 */
public class MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE, length = nums.length;
        for (int stepLength = 1; stepLength <= length; stepLength++) {
            for (int start = 0; start <= length - stepLength; start++) {
                int sum = sum(nums, start, start + stepLength - 1);
                if (sum > result) {
                    result = sum;
                }
            }
        }
        return result;
    }

    public static int maxSUBArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            sum = Math.max(sum, nums[i]);
            result = Math.max(sum, result);
        }
        return result;
    }

    public static int maxSubArray3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if(sum < nums[i]){
                sum = nums[i];
            }
            result = result > sum ? result : sum;
        }
        return result;
    }

    public static int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        return maxSubArray2(nums, 0, nums.length - 1);
    }

    public static int maxSubArray2(int[] nums, int start, int end) {
        if (nums == null || nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        }
        if (start == end) {
            return nums[start];
        }
        int result = Integer.MIN_VALUE;
        int low = start, high = end;
        while (low < high) {
            while (low < nums.length && nums[low] <= 0) {
                low++;
            }
            while (high >= 0 && nums[high] <= 0) {
                high--;
            }
            if (low < high) {
                int sum = sum(nums, low, high);
                if (sum > result) {
                    result = sum;
                }
                int r1 = maxSubArray2(nums, low + 1, end);
                int r2 = maxSubArray2(nums, low, end - 1);
                int temp = Integer.max(r1, r2);
                return Integer.max(result, temp);
            } else if (low == high) {
                result = result > nums[low] ? result : nums[low];
            } else {
                //全负数导致错位
                Arrays.sort(nums);
                return nums[nums.length - 1];
            }
        }
        return result;
    }

    private static int sum(int[] nums, int start, int end) {
        int result = 0;
        while (start <= end) {
            result += nums[start];
            start++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{8, -19, 5, -4, 20}));
        System.out.println(maxSubArray(new int[]{0, 0, -3, 1}));
        System.out.println(maxSubArray(new int[]{1}));
        System.out.println(maxSubArray(new int[]{-2, -1}));
        System.out.println(maxSubArray(new int[]{-2, 1}));
        System.out.println("----------------------------");
        System.out.println(maxSubArray2(new int[]{8, -19, 5, -4, 20}));
        System.out.println(maxSubArray2(new int[]{0, 0, -3, 1}));
        System.out.println(maxSubArray2(new int[]{1}));
        System.out.println(maxSubArray2(new int[]{-2, -1}));
        System.out.println(maxSubArray2(new int[]{-2, 1}));
    }

}
