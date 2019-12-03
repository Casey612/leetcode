package exec.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class PostirderTraversal {
    
    
    public static List<Integer> postorderTraversal(TreeNode root) {
        //return result
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
    
        Set<TreeNode> set = new HashSet<>();
        stack.add(root);
        boolean left = false, right = false;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (cur.left == null && cur.right == null) {
                //叶子节点
                cur = stack.pop();
                set.add(cur);
                result.add(cur.val);
            } else {
                //非叶子节点
                if (cur.right != null && !set.contains(cur.right)) {
                    stack.add(cur.right);
                } else {
                    right = true;
                }
                if (cur.left != null && !set.contains(cur.left)) {
                    stack.add(cur.left);
                } else {
                    left = true;
                }
                if (left && right) {
                    cur = stack.pop();
                    set.add(cur);
                    result.add(cur.val);
                }
                left = false;
                right = false;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        root.right = n1;
        n1.left = n2;
        
        List<Integer> result = postorderTraversal(root);
        System.out.println(result);
    }
    
}
