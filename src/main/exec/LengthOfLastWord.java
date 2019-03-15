package exec;

/**
 * @author yuzhe
 * @since 3/15/19
 */
public class LengthOfLastWord {

    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] array = s.split(" ");
        return array.length > 0 ? array[array.length - 1].length() : 0;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("      "));
    }

}
