package exec.dynamicProgramming;

/**
 * # 10
 * @author yuki
 * Created on 2021-12-29
 */
public class WildcardIsMatch {

    public boolean isMatch(String s, String p) {
        return matchHelper(s, 0, p, 0);
    }

    private boolean matchHelper(String s, int i, String p, int j) {
        if (i == s.length() && j == p.length()) {
            return true;
        } else if (i == s.length()) {
            //s已经完成 p还没有结束 x*重复情况
            if ((p.length() - j) % 2 == 1) {
                return false;
            }
            while (j < p.length()) {
                if (p.charAt(j + 1) == '*') {
                    j += 2;
                } else {
                    return false;
                }
            }
            return true;
        } else if (j == p.length()) {
            return false;
        }
        //i/j都没有结束
        char c1 = s.charAt(i), c2 = p.charAt(j);
        if (c1 == c2 || c2 == '.') {
            //p下一个字母为*
            if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                //匹配多个
                return matchHelper(s, i + 1, p, j)
                //匹配0个
                        || matchHelper(s, i, p, j + 2);
            }
            return matchHelper(s, i + 1, p, j + 1);
        } else {
            if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                return matchHelper(s, i, p, j + 2);
            }
            return false;
        }
    }

    public static void main(String[] args) {
        WildcardIsMatch match = new WildcardIsMatch();
        System.out.println(match.isMatch("aab", "c*a*b"));
    }

}
