package exec.dynamicProgramming;

/**
 * @author cuiyuzhe <cuiyuzhe@kuaishou.com>
 * Created on 2020-08-18
 */
public class DistinctSubsequence {

    public int numDistinct(String s, String t) {
        if (s == null || s.trim().length() == 0 || t == null || t.trim().length() == 0) {
            return 0;
        }
        return numDistinct(s, 0, t, 0);
    }

    private int numDistinct(String s, int sStart, String t, int tStart) {
        if (sStart >= s.length() || tStart >= t.length()) {
            return 0;
        }
        char sc = s.charAt(sStart);
        char tc = t.charAt(tStart);
        int result = 0;
        if (sc == tc) {
            if (tStart == (t.length() - 1)) {
                result++;
            } else {
                result += numDistinct(s, sStart + 1, t, tStart + 1);
            }
        }
        result += numDistinct(s, sStart + 1, t, tStart);
        return result;
    }


    public int numDistinct2(String s, String t) {
        if (s == null || s.trim().length() == 0 || t == null || t.trim().length() == 0 || t.length() > s.length()) {
            return 0;
        }
        int[][] dp = new int[t.length()][s.length()];
        //初始化第一行
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(0)) {
                count++;
            }
            dp[0][i] = count;
        }
        for (int i = 1; i < t.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i > j) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1];
                    if (t.charAt(i) == s.charAt(j)) {
                        dp[i][j] += dp[i - 1][j - 1];
                    }
                }
            }
        }


        return dp[t.length() - 1][s.length() - 1];
    }

    public static void main(String[] args) {
        DistinctSubsequence holder = new DistinctSubsequence();
        System.out.println(holder.numDistinct2("rabbbit", "rabbit"));
        System.out.println(holder.numDistinct2("babgbag", "bag"));
    }

}
