package exec.dynamicProgramming;

public class PannyAndChange {

    public int waysToChange(int n) {
        int[] penny = {1, 5, 10, 25};
        int[][] dp = new int[penny.length][n + 1];
        for (int i = 0; i < penny.length; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = 1;
                } else {
                    //i > 0, j > 0
                    if (j - penny[i] >= 0) {
                        dp[i][j] = (dp[i - 1][j] + dp[i][j - penny[i]]) % 1000000007;
                    } else {
                        //cannot contain penny[i]
                        dp[i][j] = dp[i - 1][j] % 1000000007;
                    }
                }
            }
        }
        return dp[penny.length - 1][n];
    }

}
