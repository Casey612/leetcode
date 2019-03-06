package exec;

/**
 * @author yuzhe
 * @since 3/5/19
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLength = strs[0].length();
        String result = strs[0];
        for (int i = 0; i < strs.length; i++) {
            if (minLength >= strs[i].length()) {
                minLength = strs[i].length();
                result = strs[i];
            }
        }
        int i;
        while (minLength > 0) {
            result = result.substring(0, minLength);
            minLength--;
            for (i = 0; i < strs.length; i++) {
                if(!strs[i].startsWith(result)){
                    break;
                }
            }
            if(i == strs.length){
                return result;
            }
        }
        return "";
    }

    public static void main(String[] args) {

    }

}
