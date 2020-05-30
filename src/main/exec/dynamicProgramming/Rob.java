package exec.dynamicProgramming;

public class Rob {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (i >= 2) {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            } else {
                dp[i] = Math.max(dp[i - 1], nums[i]);
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

}
