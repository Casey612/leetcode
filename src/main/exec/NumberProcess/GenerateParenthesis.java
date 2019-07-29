package exec.NumberProcess;

import java.util.*;

/**
 * @author yuzhe
 * @since 3/7/19
 */
public class GenerateParenthesis {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList();
        Set<String> resultSet = new HashSet<>();
        if (n == 0) {
            result.add("");
        } else {
            for (int i = n - 1; i >= n / 2; i--) {
                List<String> parts = generateParenthesis(i);
                List<String> tails = generateParenthesis(n - 1 - i);
                for (String part : parts) {
                    for (String tail : tails) {
                        resultSet.add("(" + part + ")" + tail);
                        resultSet.add("(" + tail + ")" + part);
                    }
                }
            }
        }
        result.addAll(resultSet);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

}
