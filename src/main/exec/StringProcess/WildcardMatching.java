package exec.StringProcess;

public class WildcardMatching {
    
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return s == p;
        }
        if (p.equals(s)) {
            return true;
        }
        
        if (s.contains(p) && s.length() > p.length()) {
            return false;
        }
        
        int oneChar = 0;
        boolean noChar = true;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '?') {
                oneChar++;
                sb.append(c);
            } else if (c == '*'){
                //nothing
                if (sb.length() < 1 || sb.charAt(sb.length() - 1) != '*') {
                    sb.append(c);
                }
            } else {
                noChar = false;
                sb.append(c);
            }
        }
        
        p = sb.toString();
        
        if (noChar) {
            if (oneChar == 0) {
                //only *
                return true;
            } else  if (oneChar == p.length()) {
                //only ?
                return s.length() == p.length();
            } else {
                return s.length() >= oneChar;
               
            }
        }
        
        String[] partternArray = p.split("\\*");
        
        int startIndex = 0;
        for (int i = 0; i < partternArray.length; i++) {
            boolean startForce = i == 0;
            int temp = matches(partternArray[i], s, startIndex, startForce);
            
            if (temp >= startIndex + partternArray[i].length() && temp <= s.length()) {
                startIndex = temp;
            } else {
                return false;
            }
        }
        
        if (startIndex == s.length() || p.endsWith("*")) {
            return true;
        } else {
            String temp = partternArray[partternArray.length - 1];
            if (!temp.contains("?")) {
                int tempStart = s.length() - temp.length();
                String sub = s.substring(tempStart);
                return temp.endsWith(sub) || sub.endsWith(temp);
            } else if (partternArray.length - 1 != 0){
                int tempStart = s.length() - temp.length();
                startIndex = matches(temp, s, tempStart, false) ;
                return startIndex == s.length();
            } else {
                return false;
            }
        }
    }
    
    private int matches(String parttern, String str, int startIndex, boolean startForce) {
        if ("".equals(parttern)) {
            return startIndex;
        }
        
        int index = startIndex;
        
        if (startForce) {
            //开头对齐
            for (int i = 0; i < parttern.length(); i++) {
                char c = parttern.charAt(i);
                if (c == '?') {
                    index++;
                } else {
                    if (index < str.length()) {
                        char s = str.charAt(index);
                        if (s == c) {
                            index++;
                        } else {
                            return -1;
                        }
                    } else {
                        return -1;
                    }
                }
            }
        } else {
            //开头可以任意开始
            char[] s = str.toCharArray();
            char[] p = parttern.toCharArray();
            
            int pIndex = 0;
            while (index < s.length && pIndex < p.length) {
                if (s[index] == p[pIndex] || p[pIndex] == '?') {
                    index++;
                    pIndex++;
                } else {
                    index = index - pIndex + 1;
                    pIndex = 0;
                }
            }
            if (pIndex == p.length) {
                return index;
            } else {
                return -1;
            }
        }
        return index;
    }
    
    public static void main(String[] args) {
        
        WildcardMatching matching = new WildcardMatching();
        
        System.out.println("abc" + " matches " + "***" + " = " + matching.isMatch("abc", "***"));
        System.out.println("aa" + " matches " + "a" + " = " + matching.isMatch("aa", "a"));
        System.out.println("aa" + " matches " + "a*" + " = " + matching.isMatch("aa", "a*"));
        System.out.println("ab" + " matches " + "*a" + " = " + matching.isMatch("ab", "*a"));
        System.out.println("a" + " matches " + "aa" + " = " + matching.isMatch("a", "aa"));
        System.out.println("bcd" + " matches " + "??" + " = " + matching.isMatch("bcd", "??"));
        System.out.println("aa" + " matches " + "*" + " = " + matching.isMatch("aa", "*"));
        System.out.println("cb" + " matches " + "?a" + " = " + matching.isMatch("cb", "?a"));
        System.out.println("adceb" + " matches " + "*a*b" + " = " + matching.isMatch("adceb", "*a*b"));
        System.out.println("acdcb" + " matches " + "a*c?b" + " = " + matching.isMatch("acdcb", "a*c?b"));
        System.out.println("aa" + " matches " + "*a" + " = " + matching.isMatch("aa", "*a"));
        System.out.println("hi" + " matches " + "*?" + " = " + matching.isMatch("hi", "*?"));
        System.out.println("aac" + " matches " + "*ac" + " = " + matching.isMatch("aac", "*ac"));
        System.out.println("bbabb" + " matches " + "??**b*" + " = " + matching.isMatch("bbabb", "??**b*"));
        System.out.println("bababb" + " matches " + "?a*?b" + " = " + matching.isMatch("bababb", "?a*?b"));
        System.out.println("bbb" + " matches " + "?b" + " = " + matching.isMatch("bbb", "?b"));
        System.out.println("baaaa" + " matches " + "*aaa" + " = " + matching.isMatch("baaaa", "*aaa"));
        System.out.println("baa" + " matches " + "**ba" + " = " + matching.isMatch("baa", "**ba"));
    }
    
}
