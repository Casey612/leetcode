package exec;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class NextPermutation {

    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }
        if (index >= 0) {
            int swapIndex;
            for (swapIndex = nums.length - 1; swapIndex > index; swapIndex--) {
                if (nums[swapIndex] > nums[index]) {
                    break;
                }
            }
            swap(nums, index, swapIndex);
            Arrays.sort(nums, index + 1, nums.length);
        } else {
            Arrays.sort(nums);
        }

    }

    private static void swap(int[] nums, int targetIndex, int swapIndex) {
        int temp = nums[targetIndex];
        nums[targetIndex] = nums[swapIndex];
        nums[swapIndex] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{5, 1, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{1, 2};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
        nums = new int[]{2, 1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

}
