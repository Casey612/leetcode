package exec.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * leetcode #144
 */
public class PreorderTraversal {
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if (cur.right != null) {
                stack.add(cur.right);
            }
            if (cur.left != null) {
                stack.add(cur.left);
            }
        }
        return result;
    }
    
    public List<Integer> preorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        result.add(root.val);
        if (root.left != null) {
            result.addAll(preorderTraversalRecursive(root.left));
        }
        if (root.right != null) {
            result.addAll(preorderTraversalRecursive(root.right));
        }
        return result;
    }

    
    
    
}
