package exec;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 3/6/19
 */
public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int result = 0, lastGap = Integer.MAX_VALUE;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            int thirdTarget = target - twoSum;
            int closestIndex;
            if (target > twoSum) {
                closestIndex = left + 1;
            } else {
                closestIndex = right - 1;
            }
            while (closestIndex < right && closestIndex > left) {
                if (nums[closestIndex] == thirdTarget) {
                    return target;
                } else {
                    //nums[closestIndex] > thirdTarget
                    int sum = twoSum + nums[closestIndex];
                    int gap = target - sum;
                    gap = gap < 0 ? -gap : gap;
                    if (gap < lastGap) {
                        lastGap = gap;
                        result = sum;
                    }
                    if (target > twoSum) {
                        if (nums[closestIndex] > thirdTarget) {
                            break;
                        } else {
                            closestIndex++;
                        }
                    } else {
                        if (nums[closestIndex] < thirdTarget) {
                            break;
                        } else {
                            closestIndex--;
                        }
                    }
                }
            }
            //find all the num between left and right
            right--;
            if (right == (left + 1)) {
                left++;
                right = nums.length - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{25, -12, 2, 66, -8, 67, 67, -93, -97, -68, -49, -24, 49, 90, 31, 87, -1, 14, 50, -25, 69, -91, -23, 82, -43, 96, 80, 43, 4, -87, 40, 24, -71, -10, -16, 78, -60, -20, -84, 69, 84, 84, 16, -23, -25, 88, 15, 72, -82, -72, -16, 49, 50, 26, 3, 26, -92, 82, -69, 60, -81, 24, -25, -47, -77, -37, -33, 69, 21, -50, 56, -43, 18, -87, 96, 47, 59, 37, 100, 23, -34, -69, -12, -83, -65, 91, 75, 100, 24, 80, 64, -27, -31, 39, -46, -73, 88, -13, -10, 67, 95, 27, 91, -61, -44, 67, 0, -29, -57, -61, -62, 83, -6, 82, -58, -58, -5, -87, -44, 9, -20, -28, 3, 17, 57, -63, 78, 34, 7, -68, 30, -49, 77, -97, 15, -42, -49, -22, -60, 78, 84, 35, 19};
        System.out.println(threeSumClosest(array, 250));
        System.out.println(threeSumClosest(new int[]{0, 2, 1, -3}, 1));
    }
}
