package exec.dynamicProgramming;

/**
 * # 1312
 * @author yuki
 * Created on 2021-12-29
 */
public class MinInsertions {

    public int minInsertions(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int[][] dp = new int[length][length];
        //极端case初始化
        for (int col = 0; col < length; col++) {
            dp[col][col] = 0;
            if (col != 0) {
                dp[col - 1][col] = s.charAt(col) == s.charAt(col - 1) ? 0 : 1;
            }
        }
        for (int col = 2; col < length; col++) {
            for (int row = col - 2; row >= 0; row--) {
                if (s.charAt(col) == s.charAt(row)) {
                    dp[row][col] = dp[row + 1][col - 1];
                } else {
                    dp[row][col] = Math.min(dp[row][col - 1], dp[row + 1][col]) + 1;
                }
            }
        }
        return dp[0][length - 1];
    }

}
