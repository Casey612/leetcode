package exec.StringProcess;

import java.util.Stack;

/**
 * @author yuzhe
 * @since 3/7/19
 */
public class ValidParentheses {

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Character temp;
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stack.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    temp = stack.pop();
                    if ((c == ')' && temp == '(') || (c == ']' && temp == '[') || (c == '}' && temp == '{')) {
                        continue;
                    } else {
                        return false;
                    }
                default:
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
    }

}
