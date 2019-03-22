package exec;

import java.util.Stack;

public class SimplifyPaths {

    public static String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return path;
        }
        Stack<String> stack = new Stack<>();
        for (String s : path.split("/")) {
            s = s.trim();
            if (s.isEmpty() || ".".equals(s)) {
                continue;
            } else if ("..".equals(s)) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop()).insert(0, '/');
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/../"));
    }

}
