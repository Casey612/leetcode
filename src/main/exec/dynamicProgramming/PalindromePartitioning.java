package exec.dynamicProgramming;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PalindromePartitioning {

    public static void main(String[] args) {
        System.out.println(PalindromePartitioning.partition("aab"));
        System.out.println(PalindromePartitioning.partition("b"));
        System.out.println(PalindromePartitioning.partition("cdd"));
    }

    public static List<List<String>> partition(String s) {
        if (Objects.isNull(s) || s.isEmpty()) {
            return Collections.emptyList();
        }
        boolean[][] dp = getDpMatrix(s);
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        travel(dp, s, 0, list, result);
        return result;
    }

    private static void travel(boolean[][] dp, String s, int start, List<String> list, List<List<String>> result) {
        if (start >= s.length()) {
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (dp[start][i]) {
                List<String> newList = new ArrayList<>(list);
                String sub = s.substring(start, i + 1);
                newList.add(sub);
                if (i + 1 == s.length()) {
                    result.add(newList);
                } else {
                    travel(dp, s, i + 1, newList, result);
                }
            }
        }
    }


    private static boolean[][] getDpMatrix(String s) {
        int length = s.length();
        boolean[][] result = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                result[i][j] = isPalindrome(s, i, j);
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
