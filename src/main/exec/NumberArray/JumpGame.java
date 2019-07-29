package exec.NumberArray;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class JumpGame {

    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        if (nums.length == 1) {
            return true;
        }
        if (nums[0] >= (nums.length - 1)) {
            return true;
        } else {
            for (int i = nums[0]; i > 0; i--) {
                int[] subArray = Arrays.copyOfRange(nums, i, nums.length);
                if (canJump(subArray)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        if (nums.length == 1) {
            return true;
        }
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && i != (nums.length - 1)) {
                int j;
                for(j = 0; j < i; j++){
                    if(nums[j] > (i - j)){
                        break;
                    }
                }
                if(j >= i){
                    return false;
                }
            }
        }
        return i == nums.length;
    }

    public static void main(String[] args) {
        System.out.println(canJump2(new int[]{2,0, 0, 0}));
        System.out.println(canJump2(new int[]{3,0, 0, 0}));
//        System.out.println(canJump2(new int[]{3,2,1,0,4}));
    }

}
