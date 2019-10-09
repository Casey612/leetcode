package exec.Tree;

/**
 * leetcode #111
 * 求指定节点到最近叶子节点的高度
 */
public class MinimumDepthOfBinaryTree {
    
    public int minDepth(TreeNode root) {
        if (root == null) {
            //该节点为空
            return 0;
        }
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0 && right == 0) {
            //
            return 1;
        } else if (left == 0) {
            //左子树高度为0 该节点不存在左子树
            return right + 1;
        } else if (right == 0) {
            //右子树高度为0 该节点不存在右子树
            return left + 1;
        } else {
            //该节点同时存在左右子树
            int min = left < right ? left : right;
            return min + 1;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
    
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
    
        root.left = node1;
        root.right = node2;
    
        node2.left = node3;
        node2.right = node4;
    
        MinimumDepthOfBinaryTree minimumDepthOfBinaryTree = new MinimumDepthOfBinaryTree();
        int result = minimumDepthOfBinaryTree.minDepth(root);
        System.out.println(result);
    }
    
}
