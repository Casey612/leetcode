package exec.Tree;

/**
 * # 124
 */
public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
       int result = travel(root);
       return Math.max(result, max);
    }

    public int travel(TreeNode root) {
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
            int leftMax = travel(root.left);
            int rightMax = travel(root.right);

            //对上一层不可链接
            max = Math.max(cur + leftMax + rightMax, max);
            max = Math.max(rightMax, max);
            max = Math.max(leftMax, max);

            int temp = Math.max(leftMax, rightMax);
            return Math.max(cur, temp + cur);
        } else {
            //只有一个子节点
            TreeNode node = root.left == null ? root.right : root.left;
            int subMax = travel(node);
            max = Math.max(subMax, max);
            return  Math.max(subMax + cur, cur);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode n1 = new TreeNode(9);
        TreeNode n2 = new TreeNode(-6);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(9);
        root.left = n1;
        root.right = n2;
        n2.left = n3;
        n2.right = n4;
        BinaryTreeMaximumPathSum summer = new BinaryTreeMaximumPathSum();
        System.out.println(summer.maxPathSum(root));
    }

}
