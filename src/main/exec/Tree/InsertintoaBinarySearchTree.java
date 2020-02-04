package exec.Tree;

/**
 * #701. Insert into a Binary Search Tree
 */
public class InsertintoaBinarySearchTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode parent = root;
        TreeNode temp = root.val < val ? root.right : root.left;
        while (temp != null) {
            parent = temp;
            if (temp.val < val) {
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }
        temp = new TreeNode(val);
        if (parent.val < val) {
            parent.right = temp;
        } else {
            parent.left = temp;
        }
        return root;
    }
}
