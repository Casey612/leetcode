package exec;

public class RotateSortedArray2 {

    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high + low) / 2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[low] < nums[mid]) {
                // left part sorted
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (nums[low] > nums[mid]) {
                //right part sorted
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                low++;
            }
        }
        return nums[low] == target;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 3, 1, 1, 1}, 3));
    }

}
