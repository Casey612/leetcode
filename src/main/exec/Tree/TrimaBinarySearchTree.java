package exec.Tree;

import java.util.Stack;

/**
 * #669. Trim a Binary Search Tree
 */
public class TrimaBinarySearchTree {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        TreeNode result = null;
        //leaf node
        if (root.left == null && root.right == null) {
            if (root.val <= R && root.val >= L) {
                result = root;
            }
            return result;
        }
        //tree
        if (root.val < L) {
            return trimBST(root.right, L, R);
        } else if (root.val > R) {
            return trimBST(root.left, L, R);
        } else {
            TreeNode left = trimBST(root.left, L, R);
            TreeNode right = trimBST(root.right, L, R);
            root.left = left;
            root.right = right;
        }
        return root;
    }

}
