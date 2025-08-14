package exec.dynamicProgramming;


import java.util.Objects;

public class PalindromePartitioning2 {

    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning2().minCut("aab"));
        System.out.println(new PalindromePartitioning2().minCut("b"));
        System.out.println(new PalindromePartitioning2().minCut("cdd"));
        System.out.println(new PalindromePartitioning2().minCut("cd"));
        System.out.println(new PalindromePartitioning2().minCut("aaabaa"));
        System.out.println(new PalindromePartitioning2().minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp"));
    }

    public int minCut(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return 0;
        }
        boolean[][] dp = getDpMatrix(s);
        int[] dp2 = new int[s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp2[i] = getMinCut(i, dp, dp2);
        }
        return dp2[0];
    }

    private int getMinCut(int idx, boolean[][] dp, int[] dp2) {
        int length = dp.length;
        if (idx == length - 1) {
            return 0;
        }
        if (dp[idx][length - 1]) {
            return 0;
        }
        int result = length - 1 - idx;
        for (int i = length - 2; i >= idx; i--) {
            if(dp[idx][i]) {
                result = Math.min(result, 1 + dp2[i + 1]);
            }
        }
        return result;
    }

    private static boolean[][] getDpMatrix(String s) {
        int length = s.length();
        boolean[][] result = new boolean[length][length];
        for (int i = length - 1; i >= 0; i--) {
            for (int j = length - 1; j >= i; j--) {
                if (i == j) {
                    result[i][j] = true;
                } else if (i == 0) {
                    result[i][j] = isPalindrome(s, i, j);
                } else if(s.charAt(i) == s.charAt(j)) {
                    if (i == j - 1 || result[i + 1][j - 1]) {
                        result[i][j] = true;
                    }
                }
            }
        }
        return result;
    }


    public static boolean isPalindrome(String s, int pre, int last) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return false;
        }
        if (pre == last) {
            return true;
        }
        while (pre < last) {
            char left = s.charAt(pre);
            char right = s.charAt(last);
            if (left != right) {
                return false;
            }
            last--;
            pre++;
        }
        return true;
    }
}
