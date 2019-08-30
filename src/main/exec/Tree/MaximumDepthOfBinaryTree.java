package exec.Tree;

public class MaximumDepthOfBinaryTree {
    
    public int maxDepth(TreeNode root) {
        
        if (root == null) {
            return 0;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        if (left == null && right == null) {
            //叶子节点
            return 1;
        } else if (left == null) {
            return 1 + maxDepth(right);
        } else if (right == null) {
            return 1 + maxDepth(left);
        } else {
            return 1 + Math.max(maxDepth(left), maxDepth(right));
        }
    }
    
    public static void main(String[] args) {
        
        MaximumDepthOfBinaryTree depth = new MaximumDepthOfBinaryTree();
        
        TreeNode root = new TreeNode(3);
        
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        
        root.left = node1;
        root.right = node2;
        
        node2.left = node3;
        node2.right = node4;
        
        System.out.println(depth.maxDepth(root));
        
    }

}
