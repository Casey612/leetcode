package exec.Tree;

public class PathSum3 {

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return findPathSum(root, sum, true);

    }

    private int findPathSum(TreeNode root, int sum, boolean notStarted) {
        if (root.left == null && root.right == null) {
            //leaf node
            return root.val == sum ? 1 : 0;
        } else {
            //tree
            int result = 0;
            if (root.val == sum) {
                //find one path
                result++;
            }
            if (!notStarted) {
                //case 1. must count this node
                if (root.left != null) {
                    //case 1.
                    result += findPathSum(root.left, sum - root.val, false);
                }
                if (root.right != null) {
                    result += findPathSum(root.right, sum - root.val, false);
                }

            } else {
                //case 2. do not count this node
                if (root.left != null) {
                    //case 1.
                    result += findPathSum(root.left, sum - root.val, false);
                    result += findPathSum(root.left, sum, true);
                }
                if (root.right != null) {
                    result += findPathSum(root.right, sum - root.val, false);
                    result += findPathSum(root.right, sum, true);
                }
            }
            return result;
        }
    }

}
