package exec.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RecoverBinarySearchTree {
    
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            return;
        }
        
        
    }
    
    private List<Integer> middleOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null) {
            stack.add(temp);
            temp = temp.left;
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            result.add(cur.val);
        }
        return result;
    }
    
}
