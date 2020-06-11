package exec.NumberArray;

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        return findPeakElement(nums, 0, nums.length - 1);
    }

    private int findPeakElement(int[] nums, int low, int high) {

        int mid = (high - low) / 2 + low;
        if (low == high) {
            return mid;
        }
        if (mid - 1 >= low && mid + 1 <= high) {
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] > nums[mid]) {
                return findPeakElement(nums, low, mid - 1);
            } else {
                return findPeakElement(nums, mid + 1, high);
            }
        } else if (mid - 1 >= low) {
            //右侧边界元素
            if (nums[mid] > nums[mid - 1]) {
                return mid;
            } else {
                return findPeakElement(nums, low, mid - 1);
            }
        } else {
            //左侧边界原色
            if (nums[mid] > nums[mid + 1]) {
                return mid;
            } else {
                return findPeakElement(nums, mid + 1, high);
            }
        }

    }

    public static void main(String[] args) {
        FindPeakElement finder = new FindPeakElement();
        System.out.println(finder.findPeakElement(new int[]{1, 2, 3, 1}));
        System.out.println(finder.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4}));
    }

}
