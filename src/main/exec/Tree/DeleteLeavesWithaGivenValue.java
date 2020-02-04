package exec.Tree;

/**
 * #1325. Delete Leaves With a Given Value
 */
public class DeleteLeavesWithaGivenValue {

    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        //tree
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null) {
            return root.val == target ? null : root;
        } else {
            return root;
        }
    }

}
