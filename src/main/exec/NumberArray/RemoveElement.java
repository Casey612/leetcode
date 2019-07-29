package exec.NumberArray;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = 0;
        for (int num : nums) {
            if(num != val){
                nums[result] = num;
                result++;
            }
        }
        return result;
    }

}
