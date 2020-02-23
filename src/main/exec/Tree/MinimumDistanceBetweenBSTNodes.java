package exec.Tree;

public class MinimumDistanceBetweenBSTNodes {

    private int result = Integer.MAX_VALUE;

    public int minDiffInBST(TreeNode root) {
        travel(root);
        return result;
    }

    private void travel(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            TreeNode temp = root.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            result = Math.min(result, root.val - temp.val);
            travel(root.left);
        }
        if (root.right != null) {
            TreeNode temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            result = Math.min(result, temp.val - root.val);
            travel(root.right);
        }
    }

}
