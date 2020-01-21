package exec.Tree;

/**
 * #563
 */
public class BinaryTreeTilt {

    public int findTilt(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int leftTilt = findTilt(root.left);
        int rightTilt = findTilt(root.right);

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        int curTilt = leftSum - rightSum;
        curTilt = curTilt < 0 ? -curTilt : curTilt;
        return leftTilt + rightTilt + curTilt;
    }

    private int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return node.val;
        }
        int left = sum(node.left);
        int right = sum(node.right);
        return left + right + node.val;
    }

    public int findTilt1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int[] left = travel(root.left);
        int[] right = travel(root.right);

        int curTilt = left[1] - right[1];
        curTilt = curTilt < 0 ? -curTilt : curTilt;
        return left[0] + right[0] + curTilt;
    }

    /**
     *
     * @param node
     * @return int[] {nodeTilt, nodeSum}
     */
    private int[] travel(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        if (node.left == null && node.right == null) {
            return new int[]{0, node.val};
        }
        int[] left = travel(node.left);
        int[] right = travel(node.right);
        int curTilt = left[1] - right[1];
        curTilt = curTilt < 0 ? -curTilt : curTilt;
        return new int[]{ left[0] + right[0] + curTilt, left[1] + right[1] + node.val};
    }

}
