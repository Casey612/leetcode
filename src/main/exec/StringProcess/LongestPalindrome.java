package exec.StringProcess;

import java.util.Arrays;

/**
 * @author yuzhe
 * @since 10/1/18
 * <p>
 * exec5. 最长回文子串
 */
public class LongestPalindrome {

    public static String longestPalindrome1(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        int length = s.length();
        char[] chars = s.toCharArray();
        boolean[][] flags = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    flags[i][j] = true;
                } else if (j - i == 1) {
                    flags[i][j] = chars[i] == chars[j];
                }
            }
        }

        int left = 0, right = 1, subLength = 1;
        String result = s.substring(0, 1);
        while (right < length) {
            while (left < right) {
                if (chars[left] != chars[right]) {
                    flags[left][right] = false;
                } else {
                    if (right - left < 2) {
                        flags[left][right] = true;
                    } else {
                        flags[left][right] = flags[left + 1][right - 1];
                    }
                    if (flags[left][right]) {
                        int curLength = right - left + 1;
                        result = subLength < curLength ? s.substring(left, right + 1) : result;
                        subLength = Math.max(subLength, curLength);
                    }
                }
                left++;
            }
            right++;
            left = 0;
        }
        return result;
    }

    private static boolean isPalindrome(String s, int left, int right, boolean[][] flags) {
        if (!flags[left][right]) {
            int sum = left + right;
            int l = (int) Math.floor(sum / 2d);
            int r = (int) Math.ceil(sum / 2d);
            while (l >= left && r <= right) {
                if (l == r) {
                    //already
                } else if ((r - 1) == l) {
                    flags[l][r] = s.charAt(l) == s.charAt(r);
                } else {
                    flags[l][r] = flags[l + 1][r - 1] && s.charAt(l) == s.charAt(r);
                }
                l--;
                r++;
            }
        }
        return flags[left][right];

    }

    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for (int start = 0; start + length <= s.length(); start++) {
                if (isPalindrome(s, start, start + length - 1)) {
                    return s.substring(start, start + length);
                }
            }
        }
        return s;
    }


    public static void main(String[] args) {
//        String r1 = longestPalindrome1("babad");
//        System.out.println(r1);
//        String r2 = longestPalindrome1("ac");
//        System.out.println(r2);
        String r3 = longestPalindrome1("bb");
        System.out.println(r3);
    }
}
