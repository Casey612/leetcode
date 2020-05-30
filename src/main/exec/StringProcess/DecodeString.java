package exec.StringProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * #394 decodeString
 */
public class DecodeString {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        StringBuilder expression = new StringBuilder();
        StringBuilder kStr = new StringBuilder();
        for (char c : chars) {
            if (c == '[') {
                stack.push('[');
            } else if (c == ']') {
                char temp;
                while(!stack.isEmpty() && (temp = stack.pop()) != '[') {
                    expression.insert(0, temp);
                }
                while (!stack.isEmpty() && (temp = stack.peek()) <= '9' && temp >= '0') {
                    kStr.insert(0, temp);
                    stack.pop();
                }
                List<Character> process = process(kStr.toString(), expression.toString());
                stack.addAll(process);
                kStr.delete(0, kStr.length());
                expression.delete(0, expression.length());
            } else {
                //num or character
                stack.push(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }

    private List<Character> process(String kStr, String exp) {
        int k;
        if (kStr == null || kStr.length() == 0) {
            k = 1;
        } else {
            k = Integer.parseInt(kStr);
        }
        List<Character> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (char c : exp.toCharArray()) {
                result.add(c);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DecodeString decoder = new DecodeString();
        System.out.println(decoder.decodeString("3[a]2[bc]"));
        System.out.println(decoder.decodeString("2[abc]3[cd]ef"));
        System.out.println(decoder.decodeString("3[a2[c]]"));
    }
}
