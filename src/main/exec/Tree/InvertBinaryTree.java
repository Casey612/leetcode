package exec.Tree;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        //leaf node
        if (root.left == null && root.right == null) {
            return root;
        } else {
            TreeNode leftPart = invertTree(root.left);
            TreeNode rightPart = invertTree(root.right);

            root.right = leftPart;
            root.left = rightPart;
            return root;
        }
    }

}
