package exec.Tree;

/**
 * # 124
 */
public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int cur = root.val;
        if (root.left == null && root.right == null) {
            //leaf node
            max = Math.max(cur, max);
            return cur;
        } else if (root.left != null && root.right != null) {
            //左右子节点都存在
            int leftMax = maxPathSum(root.left);
            int rightMax = maxPathSum(root.right);

        } else {
            //只有一个子节点
            TreeNode node = root.left == null ? root.right : root.left;
            int subMax = maxPathSum(node);

        }
        return 0;
    }

}
