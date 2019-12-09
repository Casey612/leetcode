package exec.Tree;

import java.util.Stack;

public class KthSmallestElementinaBST {
    
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        
        int count = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            count++;
            if (count == k) {
                return cur.val;
            }
            if (cur.right != null) {
                temp = cur.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
        return 0;
    }
    
}
