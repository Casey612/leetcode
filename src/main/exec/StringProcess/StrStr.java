package exec.StringProcess;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class StrStr {

    public static int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        } else if (haystack.length() == needle.length()) {
            if (haystack.equals(needle)) {
                return 0;
            } else {
                return -1;
            }
        } else {

            for (int start = 0; start <= (haystack.length() - needle.length()); start++) {
                int i;
                for (i = 0; i < needle.length(); i++) {
                    if(haystack.charAt(start + i) != needle.charAt(i)){
                        break;
                    }
                }
                if(i == needle.length()){
                    return start;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
    }

}
