package exec.Tree;

public class PathSum {
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            //leaf node
            return sum == root.val;
        } else {
            int subSum = sum - root.val;
            boolean left = false, right = false;
            if (root.left != null) {
                left = hasPathSum(root.left, subSum);
            }
            if (!left && root.right != null) {
                right = hasPathSum(root.right, subSum);
            }
            return left || right;
        }
    }

}
