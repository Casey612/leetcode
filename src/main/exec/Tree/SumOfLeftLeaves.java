package exec.Tree;

public class SumOfLeftLeaves {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        return process(null, root, 0);
    }

    private int process(TreeNode parent, TreeNode node, int sum) {
        if (node == null) {
            return sum;
        }
        if (node.left == null && node.right == null) {
            //node是叶子节点
            if (parent == null) {
                return sum;
            }else if (parent.left == node) {
                return sum + node.val;
            } else {
                return sum;
            }
        } else {
            int left = 0, right = 0;
            if (node.left != null) {
                left = process(node, node.left, sum);
            }
            if (node.right != null) {
                right = process(node, node.right, sum);
            }
            return left + right;
        }

    }

}
