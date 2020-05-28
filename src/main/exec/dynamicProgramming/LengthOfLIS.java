package exec.dynamicProgramming;

/**
 * #300
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < dp.length; i++) {
            int cur = nums[i];
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < cur) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public static void main(String[] args) {

    }

}
