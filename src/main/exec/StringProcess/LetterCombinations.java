package exec.StringProcess;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/7/19
 */
public class LetterCombinations {
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        List<String> list = getChars(digits.charAt(0));
        for (int i = 1; i < digits.length(); i++) {
            char c = digits.charAt(i);
            List<String> appender = getChars(c);
            list = cartesian(list, appender);
        }
        return list;
    }

    private static List<String> cartesian(List<String> l1, List<String> l2) {
        List<String> result = new ArrayList<>();
        for (String s1 : l1) {
            for (String s2 : l2) {
                StringBuilder sb = new StringBuilder();
                sb.append(s1).append(s2);
                result.add(sb.toString());
            }
        }
        return result;
    }

    private static List<String> getChars(char c) {
        char[][] array = new char[10][];
        array[0] = new char[]{};
        array[1] = new char[]{};
        array[2] = new char[]{'a', 'b', 'c'};
        array[3] = new char[]{'d', 'e', 'f'};
        array[4] = new char[]{'g', 'h', 'i'};
        array[5] = new char[]{'j', 'k', 'l'};
        array[6] = new char[]{'m', 'n', 'o'};
        array[7] = new char[]{'p', 'q', 'r', 's'};
        array[8] = new char[]{'t', 'u', 'v'};
        array[9] = new char[]{'w', 'x', 'y', 'z'};
        List<String> result = new ArrayList<>();
        if (c >= '2' && c <= '9') {
            int index = c - '0';
            for (char cs : array[index]) {
                result.add(String.valueOf(cs));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }

}
