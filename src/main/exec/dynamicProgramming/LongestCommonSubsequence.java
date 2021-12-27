package exec.dynamicProgramming;

/**
 * # 1143
 * @author yuki
 * Created on 2021-12-27
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0
                || text2 == null || text2.length() == 0) {
            return 0;
        }
        return helper(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    private int helper(String text1, String text2, int end1, int end2) {
        if (end1 < 0 || end2 < 0) {
            return 0;
        }
        char c1 = text1.charAt(end1);
        char c2 = text2.charAt(end2);
        if (end1 == 0 && end2 == 0) {
            return c1 == c2 ? 1 : 0;
        } else if (c1 == c2){
            return 1 + helper(text1, text2, end1 - 1, end2 - 1);
        } else {
            return Math.max(helper(text1, text2, end1 - 1, end2),
                    helper(text1, text2, end1, end2 - 1));
        }
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        if (text1 == null || text1.length() == 0
                || text2 == null || text2.length() == 0) {
            return 0;
        }
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m][n];
        //首行数据初始化
        char c = text1.charAt(0);
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (text2.charAt(i) == c) {
                flag = true;
            }
            dp[0][i] = flag ? 1 : 0;
        }
        c = text2.charAt(0);
        flag = false;
        for (int i = 0; i < m; i++) {
            if (text1.charAt(i) == c) {
                flag = true;
            }
            dp[i][0] = flag ? 1 : 0;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                char c1 = text1.charAt(i);
                char c2 = text2.charAt(j);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    public static void main(String[] args) {
        LongestCommonSubsequence subsequence = new LongestCommonSubsequence();
        System.out.println(subsequence.longestCommonSubsequence2("abcde", "ace"));
    }

}
