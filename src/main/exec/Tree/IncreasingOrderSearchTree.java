package exec.Tree;

/**
 * 897. Increasing Order Search Tree
 */
public class IncreasingOrderSearchTree {

    public TreeNode increasingBST(TreeNode root) {
        if(root == null) {
            return null;
        }
        if (root.right != null) {
            root.right = increasingBST(root.right);
        }
        if (root.left != null) {
            TreeNode leftPart = increasingBST(root.left);
            TreeNode temp = leftPart;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = root;
            root.left = null;
            return leftPart;
        } else {
            return root;
        }
    }

}
