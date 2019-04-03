package exec;

/**
 * @author yuzhe
 * @since 3/27/19
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return numDecodings(s, 0);
    }

    private int numDecodings(String s, int cur) {
        char c = s.charAt(cur);
        int step1 = 0, step2 = 0;
        if (c >= '1' && c <= '2' && (cur + 1) < s.length()) {
            String sub = s.substring(cur, cur + 2);
            if ("10".compareTo(sub) <= 0 && "26".compareTo(sub) >= 0) {
                if (cur + 2 < s.length()) {
                    step2 = numDecodings(s, cur + 2);
                } else {
                    step2 = 1;
                }
            }
        }
        if (c >= '1' && c <= '9') {
            if (cur == s.length() - 1) {
                return 1;
            } else if (cur + 1 < s.length()) {
                step1 = numDecodings(s, cur + 1);
            }

        }
        return step1 + step2;
    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings("17"));
//        System.out.println(decodeWays.numDecodings("226"));
//        System.out.println(decodeWays.numDecodings("27"));
    }

}
