package exec;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class RotateSortedArray {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high + low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[low] <= nums[mid]) {
                // left part sorted
                if (nums[mid] > target && nums[low] <= target) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //right part sorted
                if (nums[mid] < target && nums[high] >= target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }


//            if (nums[mid] > target) {
//                if (nums[low] <= nums[mid]) {
//                    //left part sorted
//                    if (nums[low] <= target) {
//                        high = mid - 1;
//                    } else {
//                        low = mid + 1;
//                    }
//                } else {
//                    //right part sorted
//                    high = mid - 1;
//                }
//            } else if (nums[mid] < target) {
//                if (nums[low] <= nums[mid]) {
//                    //left part sorted
//                    low = mid + 1;
//                } else {
//                    //right part sort
//                    if (nums[high] >= target) {
//                        low = mid + 1;
//                    } else {
//                        high = mid - 1;
//                    }
//                }
//            } else {
//                return mid;
//            }
        }
        return nums[low] == target ? low : -1;
    }

    public static void main(String[] args) {
//        int[] array = new int[]{4, 5, 6, 7, 8, 1, 2, 3};
//        System.out.println(search(array, 4));
//        System.out.println(search(array, 8));
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

}
