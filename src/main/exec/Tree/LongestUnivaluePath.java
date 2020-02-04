package exec.Tree;

/**
 * #687. Longest Univalue Path
 */
public class LongestUnivaluePath {

    private int result = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        result = Math.min(result, findPathLength(root, root.val, true));
        longestUnivaluePath(root.left);
        longestUnivaluePath(root.right);
        return result;
    }

    private int findPathLength(TreeNode root, int val, boolean started) {
        if (root == null) {
            return 0;
        }
        if (started) {
            int left = findPathLength(root.left, val, false);
            int right = findPathLength(root.right, val, false);
            return left + right;
        } else {
            //not started, find uni-value continue
            if (root.val == val) {
                int left = findPathLength(root.left, val, false);
                int right = findPathLength(root.right, val, false);
                return Math.max(left, right) + 1;
            }
        }
        return 0;
    }

}
