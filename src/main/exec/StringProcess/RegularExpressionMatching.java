package exec.StringProcess;

/**
 * todo @yuki
 * case 427/447 failed
 * s: "baabbbaccbccacacc"
 * p: "c*..b*a*a.*a..*c"
 */
public class RegularExpressionMatching {
    
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return s == p;
        }
        if (s.equals(p)) {
            return true;
        }
        
        int oneChar = 0;
        boolean noChar = true;
        
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '.') {
                oneChar++;
            } else if (c == '*') {
                //nothing
            } else {
                noChar = false;
            }
        }
        // 模式无字母
        if (noChar) {
            if (oneChar == 0) {
                //only *  "*"
                return false;
            } else if (oneChar == p.length()) {
                //only .  "...."
                return s.length() == p.length();
            } else {
                //".*"
                p = p.replace(".*", "");
                return s.length() >= p.length();
            }
        }
        
        String[] patterns = p.split("\\.\\*");
        
        int endIndex = s.length() - 1;
        for (int i = patterns.length - 1; i >= 0; i--) {
            boolean startForce = i == (patterns.length - 1);
            int temp = matches(s, endIndex, patterns[i], startForce);
            if (temp != Integer.MIN_VALUE) {
                endIndex = temp;
            } else {
                return false;
            }
        }
        
        if (endIndex < 0) {
            return true;
        } else {
            boolean free = true;
            int end = p.indexOf(".*");
            if (end == -1) {
                free = false;
            }
            int start = 0;
            while (start >= 0 && start < end) {
                char sec = p.charAt(start);
                if (sec == '*' || sec == '.') {
                    start++;
                } else {
                    //char
                    if (p.charAt(start + 1) == '*') {
                        start += 2;
                    } else {
                        free = false;
                        break;
                    }
                    
                }
            }
            return free;
        }
        
    }
    
    public int matches(String s, int endIndex, String p, boolean startForce) {
        if ("".equals(p)) {
            return endIndex;
        }
        int sIndex = endIndex;
        int pIndex = p.length() - 1;
        
        while (sIndex >= 0 && pIndex >= 0) {
            char pc = p.charAt(pIndex);
            char sc = s.charAt(sIndex);
            if (pc == '.') {
                pIndex--;
                sIndex--;
            } else if (pc == '*') {
                char last = p.charAt(pIndex - 1);
                while (sIndex >= 0 && sc == last) {
                    sIndex--;
                    if (sIndex >= 0) {
                        sc = s.charAt(sIndex);
                    } else {
                        break;
                    }
                }
                pIndex -= 2;
            } else {
                if (pc == sc) {
                    sIndex--;
                    pIndex--;
                } else {
                    if (startForce) {
                        return Integer.MIN_VALUE;
                    } else {
                        sIndex = sIndex - (p.length() - pIndex);
                        pIndex = p.length() - 1;
                    }
                }
            }
        }
        
        if (sIndex >= 0) {
            return sIndex;
        }
        if (pIndex >= 0) {
            while (pIndex >= 0) {
                char pc = p.charAt(pIndex);
                if (pc == '*') {
                    pIndex -= 2;
                } else {
                    // 可消减
                    boolean flag = false;
                    int index = pIndex, step = 1;
                    while ((index + step * 2) < p.length()) {
                        int charIndex = index + step * 2;
                        if (p.charAt(charIndex) == '*' && p.charAt(charIndex - 1) == pc) {
                            flag = true;
                            break;
                        } else {
                            step++;
                        }
                    }
                    if (flag) {
                        pIndex--;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                }
            }
        }
        return sIndex;
        
    }
    
    public static void main(String[] args) {
        RegularExpressionMatching matching = new RegularExpressionMatching();
        
        //System.out.println("aa" + " matches " + "a" + " = " + matching.isMatch("aa", "a"));
        //
        //System.out.println("ab" + " matches " + ".*" + " = " + matching.isMatch("ab", ".*"));
        //System.out.println("aab" + " matches " + "c*a*b" + " = " + matching.isMatch("aab", "c*a*b"));
        //System.out.println(
        //    "mississippi" + " matches " + "mis*is*p*" + " = " + matching.isMatch("mississippi", "mis*is*p*"));
        //System.out.println("aaa" + " matches " + "ab*a*c*a" + " = " + matching.isMatch("aaa", "ab*a*c*a"));
        //System.out.println("bbbba" + " matches " + ".*a*a" + " = " + matching.isMatch("bbbba", ".*a*a"));
        //System.out.println("a" + " matches " + ".*..a*" + " = " + matching.isMatch("a", ".*..a*"));
        //System.out.println("a" + " matches " + "ab*a" + " = " + matching.isMatch("a", "ab*a"));
        //System.out.println("ab" + " matches " + ".*.." + " = " + matching.isMatch("ab", ".*.."));
        //System.out.println("aasdfasdfasdfasdfas" + " matches " + "aasdf.*asdf.*asdf.*asdf.*s" + " = " + matching
        // .isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
        //System.out.println("abcdede" + " matches " + "ab.*de" + " = " + matching.isMatch("abcdede", "ab.*de"));
        //System.out.println("b" + " matches " + "a.*" + " = " + matching.isMatch("b", "a.*"));
        //System.out.println("aabcbcbcaccbcaabc" + " matches " + ".*a*aa*.*b*.c*.*a*" + " = " + matching.isMatch
        // ("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*"));
        //System.out.println("abbabaaaaaaacaa" + " matches " + "a*.*b.a.*c*b*a*c*" + " = " + matching.isMatch
        // ("abbabaaaaaaacaa", "a*.*b.a.*c*b*a*c*"));
        //
        //System.out.println("baba" + " matches " + "b*.*" + " = " + matching.isMatch("baba", "b*.*"));
        //System.out.println("aab" + " matches " + "b.*" + " = " + matching.isMatch("aab", "b.*"));
        //
        //System.out.println("bcaccbbacbcbcab" + " matches " + "b*.c*..*.b*b*.*c*" + " = " + matching.isMatch("bcaccbbacbcbcab", "b*.c*..*.b*b*.*c*"));
        
        System.out.println("baabbbaccbccacacc" + " matches " + "c*..b*a*a.*a..*c" + " = " + matching.isMatch("baabbbaccbccacacc", "c*..b*a*a.*a..*c"));
    
        
    }
    
}
