package exec.NumberArray;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 1, cur = nums[0];
        for (int num : nums) {
            if(num > cur){
                cur = num;
                result++;
                nums[result - 1] = cur;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(removeDuplicates(nums));
    }

}
