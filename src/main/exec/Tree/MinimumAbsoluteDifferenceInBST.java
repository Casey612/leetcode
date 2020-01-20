package exec.Tree;

/**
 * # 530
 */
public class MinimumAbsoluteDifferenceInBST {

    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        TreeNode leftMax = null, rightMin = null;
        if (root.left != null) {
            leftMax = root.left;
            while (leftMax.right != null) {
                leftMax = leftMax.right;
            }
        }
        if (root.right != null) {
            rightMin = root.right;
            while (rightMin.left != null) {
                rightMin = rightMin.left;
            }
        }
        int left = leftMax == null ? min : root.val - leftMax.val;
        int right = rightMin == null ? min : rightMin.val - root.val;
        min = Math.min(min, Math.min(left, right));
        if (root.left != null) {
            left = getMinimumDifference(root.left);
        }
        if (root.right != null) {
           right = getMinimumDifference(root.right);
        }
        min = Math.min(min, Math.min(left, right));

        return min;
    }

}
