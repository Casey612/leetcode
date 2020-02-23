package exec.Tree;

/**
 * 814. Binary Tree Pruning
 */
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            //leaf node
            return root.val == 1 ? root : null;
        } else {
            TreeNode leftPart =  root.left == null ? null : pruneTree(root.left);
            TreeNode rightPart =  root.right == null ? null : pruneTree(root.right);
            root.left = leftPart;
            root.right = rightPart;
            if (leftPart == null && rightPart == null) {
                return root.val == 1 ? root : null;
            } else {
                return root;
            }
        }
    }

}
