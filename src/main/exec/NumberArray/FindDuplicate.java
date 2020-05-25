package exec.NumberArray;

public class FindDuplicate {

    public int findDuplicate(int[] nums) {
        return findDuplicate(nums, 0, nums.length - 1);
    }

    private int findDuplicate(int[] nums, int start, int end) {
        if (nums == null || start >= end) {
            return 0;
        }
        int temp = nums[start];
        int low = start, high = end, index = low + 1;
        while (index <= high) {
            if (nums[index] < temp) {
                swap(nums, index, low);
                index++;
                low++;
            } else if (nums[index] > temp) {
                swap(nums, index, high);
                high--;
            } else {
                return temp;
            }
        }
        if ((high - low + 1) > 1) {
            return temp;
        } else {
            int left = findDuplicate(nums, start, low - 1);
            return left == 0 ? findDuplicate(nums, high + 1, end) : left;
        }
    }

    public static void main(String[] args) {
        FindDuplicate finder = new FindDuplicate();
//        System.out.println(finder.findDuplicate(new int[]{1, 3, 4, 2, 2}));
//        System.out.println(finder.findDuplicate(new int[]{1, 3, 4, 2, 1}));
        System.out.println(finder.findDuplicate(new int[]{2,5,9,6,9,3,8,9,7,1}));
    }

    private static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
