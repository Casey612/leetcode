package exec.StringProcess;

import java.util.Stack;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class LongestValidParentheses {


    public int longestValid(String s) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(i);
                    break;
                case ')':
                    stack.pop();
                    if (stack.isEmpty()) {
                        stack.push(i);
                    } else {
                        result = Integer.max(result, i - stack.peek());
                    }
                    break;
                default:
            }
        }
        return result;
    }

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int targetLength = s.length();
        targetLength = (targetLength % 2 == 0) ? targetLength : targetLength - 1;
        for (int len = targetLength; len >= 0; len -= 2) {
            for (int startIndex = 0; startIndex <= (s.length() - len); startIndex++) {
                if (isParentheses(s, startIndex, len)) {
                    return len;
                }
            }
        }
        return 0;
    }

    private static boolean isParentheses(String s, int startIndex, int length) {
        if (s.length() < length || length == 0) {
            return false;
        }
        int endIndex = startIndex + length - 1;
        if (s.charAt(startIndex) != '(') {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = startIndex; i <= endIndex; i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                    stack.push(c);
                    if (stack.size() > (length / 2)) {
                        return false;
                    }
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    } else {
                        stack.pop();
                    }
                default:
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
//        String s1 = "(()";
//        System.out.println(longestValidParentheses(s1));
        String s2 = ")()())";
        System.out.println(longestValidParentheses(s2));
        String s3 = "";
        System.out.println(longestValidParentheses(s3));
        String s4 = ")";
        System.out.println(longestValidParentheses(s4));
    }

}
