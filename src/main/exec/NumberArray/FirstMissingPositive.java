package exec.NumberArray;

import java.util.Arrays;

public class FirstMissingPositive {
    
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        Arrays.sort(nums);
        boolean positive = false;
        int result = 1;
        for (int temp : nums) {
            if (temp > 0){
                positive = true;
            }
            if (positive) {
                if (temp == result) {
                    result++;
                } else if (temp > result){
                    break;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        
        FirstMissingPositive positive = new FirstMissingPositive();
        
        System.out.println(positive.firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(positive.firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(positive.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
    }
    
}
