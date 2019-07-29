package exec.StringProcess;

/**
 * @author yuzhe
 * @since 10/1/18
 * <p>
 * exec5. 最长回文子串
 */
public class LongestPalindrome {

    public static String longestPalindrome1(String s) {
        for (int length = s.length(); length > 0; length--) {
            for(int start = 0; start + length <= s.length(); start++){
                if(isPalindrome(s, start, start + length - 1)){
                    return s.substring(start, start + length);
                }
            }
        }
        return s;
    }

    private static boolean isPalindrome(String s, int start, int end) {
        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        for (int length = s.length(); length > 0; length--) {
            for(int start = 0; start + length <= s.length(); start++){
                if(isPalindrome(s, start, start + length - 1)){
                    return s.substring(start, start + length);
                }
            }
        }
        return s;
    }



    public static void main(String[] args) {
//        String r1 =longestPalindrome("babad");
//        System.out.println(r1);
//        String r2 =longestPalindrome("ac");
//        System.out.println(r2);
        String r3 =longestPalindrome1("bb");
        System.out.println(r3);
    }
}
