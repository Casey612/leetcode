package exec.NumberArray;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class SeachRange {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                //find target
                int l = mid, h = mid;
                while (l >= 0 && nums[l] == target) {
                    l--;
                }
                while (h < nums.length && nums[h] == target) {
                    h++;
                }
                result = new int[]{l + 1, h - 1};
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(array, 8)));
//
        int[] array1 = new int[]{1, 3};
        System.out.println(Arrays.toString(searchRange(array1, 1)));

        int[] array2 = new int[]{1};
        System.out.println(Arrays.toString(searchRange(array2, 1)));
    }
}
