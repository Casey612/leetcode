package exec.Tree;

import static exec.Tree.ConvertSortedListToBinarySearchTree.height;

public class BalancedBinaryTree {
    
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        
        int diff = Math.abs(leftHeight - rightHeight);
        
        if (diff > 1) {
            return false;
        } else {
            //该节点高度正常
            if (root.left == null && root.right == null) {
                return true;
            } else if (root.left == null) {
                return isBalanced(root.right);
            } else if (root.right == null) {
                return isBalanced(root.left);
            } else {
                return isBalanced(root.left) && isBalanced(root.right);
            }
        }
    }

}
