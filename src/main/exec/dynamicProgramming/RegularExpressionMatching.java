package exec.dynamicProgramming;

import java.util.Arrays;

/**
 * @author cuiyuzhe <cuiyuzhe@kuaishou.com>
 * Created on 2020-08-19
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {

        if ((s == null || s.trim().length() == 0) && (p == null || p.trim().length() == 0)) {
            return true;
        }
        if (p == null || p.trim().length() == 0) {
            return false;
        }
        if (s == null || s.trim().length() == 0) {
            return matchEmpty(p, 0, p.length() - 1);
        }
        //dp[j][i] j为p的索引， i为s的索引
        boolean[][] dp = new boolean[p.length()][s.length()];
        //初始化第一行
        for (int k = 0; k < s.length(); k++) {
            if (k == 0) {
                if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(k)) {
                    dp[0][k] = true;
                } else {
                    dp[0][k] = false;
                }
            } else {
                dp[0][k] = false;
            }
        }

        for (int j = 1; j < p.length(); j++) {
            char pj = p.charAt(j);
            for (int i = 0; i < s.length(); i++) {
                char si = s.charAt(i);
                if (isLetter(pj)) {
                    dp[j][i] = pj == si && ((i > 0 && dp[j - 1][i - 1]) || (i == 0 && matchEmpty(p, 0, j - 1)));
                } else if (pj == '.') {
                    dp[j][i] = i > 0 ? dp[j - 1][i - 1] : matchEmpty(p, 0, j - 1);
                } else {
                    //pj == '*'
                    char pc = p.charAt(j - 1);
                    if (isLetter(pc)) {
                        //pc is letter
                        /**
                         * 当前字母和可重复的首字母相同时， 有3种情况
                         *  1-当前字母即为首字母， *可以忽略
                         *  2-当前字母为第二个字母，与*对应
                         *  3-当前字母为第N个重复字母，长于*所在的位置
                         * 无论相同与否： 忽略x*
                         */
                        if (si == pc) {
                            dp[j][i] = dp[j - 1][i] || (i > 0 && dp[j - 1][i - 1]) || (i > 0 && dp[j][i - 1]);
                        }
                        dp[j][i] |= (j > 1 && dp[j - 2][i]);
                    } else {
                        //pc == '.'
                        /**
                         * 当前字母为第一个字母，*可忽略
                         * 当前字母为第二个字母，与*对应
                         * 当前字母为重复的.，长于
                         * 与.*不匹配，可以忽略.*
                         */
                        dp[j][i] = dp[j - 1][i] || (i > 0 && dp[j - 1][i - 1]) || (i > 0 && dp[j][i - 1]);
                        dp[j][i] |= (j > 1 && dp[j - 2][i]);
                    }
                }
            }
        }
//        print(dp);
        return dp[p.length() - 1][s.length() - 1];

    }

    public void print(boolean dp[][]) {
        for (int i = 0; i < dp.length; i++) {
            System.out.print(i + ":");
            System.out.println(Arrays.toString(dp[i]));
        }
    }

    private boolean matchEmpty(String p, int start, int end) {
        int temp = end;
        while(temp >= start) {
            if (p.charAt(temp) == '*') {
                temp -= 2;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static void main(String[] args) {
        RegularExpressionMatching regular = new RegularExpressionMatching();
        System.out.println(regular.isMatch("aab", "c*a*b"));                //true
        System.out.println(regular.isMatch("aaaaaaaa", "a*"));              //true
        System.out.println(regular.isMatch("mississippi", "mis*isp*"));     //false
        System.out.println(regular.isMatch("mississippi", "mis*isp*."));    //false
        System.out.println(regular.isMatch("huihuihjk", ".*"));             //true
        System.out.println(regular.isMatch("aaa", "ab*ac*a"));              //true
        System.out.println(regular.isMatch("a", ".*..a*"));                 //false
        System.out.println(regular.isMatch("aaa", "aaaa"));                 //false
        System.out.println(regular.isMatch("aaa", "ab*a*c*a"));             //true
        System.out.println(regular.isMatch("b", "a.*"));                    //false
        System.out.println(regular.isMatch("baabbbaccbccacacc", "c*..b*a*a.*a..*c"));   //true
        System.out.println(regular.isMatch("cbbbaccbcacbcca", "b*.*b*a*.a*b*.a*"));   //true
    }

}
