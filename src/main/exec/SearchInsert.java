package exec;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class SearchInsert {

    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                low++;
            } else {
                break;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

}
