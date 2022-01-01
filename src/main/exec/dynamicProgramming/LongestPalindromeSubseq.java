package exec.dynamicProgramming;

import java.util.Arrays;

/**
 * # 516
 * @author yuki
 * Created on 2021-12-28
 */
public class LongestPalindromeSubseq {

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int[][] dp = new int[length][length];
        //极端case初始化
        for (int col = 0; col < length; col++) {
            dp[col][col] = 1;
            if (col != 0) {
                dp[col - 1][col] = s.charAt(col) == s.charAt(col - 1) ? 2 : 1;
            }
        }
        for (int col = 2; col < length; col++) {
            for (int row = col - 2; row >= 0; row--) {
                if (s.charAt(col) == s.charAt(row)) {
                    dp[row][col] = dp[row + 1][col - 1] + 2;
                } else {
                    dp[row][col] = Math.max(dp[row][col - 1], dp[row + 1][col]);
                }
            }
        }
        return dp[0][length - 1];
    }

    public static void main(String[] args) {
        LongestPalindromeSubseq subseq = new LongestPalindromeSubseq();
        System.out.println(subseq.longestPalindromeSubseq2("bbbab"));
    }

    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int[] dp = new int[length];
        //极端case初始化
        for (int col = 0; col < length; col++) {
            dp[col] = 1;
        }
        for (int start = length - 2; start >= 0; start--) {
            int pre = 0;
            for (int end = start + 1; end < length; end++) {
                int temp = dp[end];
                if (s.charAt(start) == s.charAt(end)) {
                    dp[end] = pre + 2;
                } else {
                    dp[end] = Math.max(dp[end], dp[end - 1]);
                }
                System.out.println(Arrays.toString(dp) + ", pre: " + pre + ", temp: " + temp);
                pre = temp;
            }
        }
        return dp[length - 1];
    }

}
