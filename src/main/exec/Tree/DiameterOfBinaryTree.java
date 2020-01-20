package exec.Tree;

public class DiameterOfBinaryTree {

    private int max = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left1 = 0, right1 = 0;
        int plan2 = 0;
        if (root.left != null) {
            left1 = diameterOfBinaryTree(root.left);
            plan2 += deepCount(root.left);
            plan2++;
        }
        if (root.right != null) {
            right1 = diameterOfBinaryTree(root.right);
            plan2 += deepCount(root.right);
            plan2++;
        }
        //longest path do not contain root
        int plan1 = Math.max(left1, right1);
        return Math.max(plan1, plan2);
    }

    private int deepCount(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 0;
        }
        int left = 0, right = 0;
        if (node.left != null) {
            left = deepCount(node.left);
        }
        if (node.right != null) {
            right = deepCount(node.right);
        }
        return Math.max(left, right) + 1;
    }

}
