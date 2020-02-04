package exec.Tree;

/**
 * #671. Second Minimum Node In a Binary Tree
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node.
 * If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.
 * More formally, the property root.val = min(root.left.val, root.right.val) always holds.
 */
public class SecondMinimumNodeInaBinaryTree {

    private Integer result = null;

    public int findSecondMinimumValue(TreeNode root) {
        //root cannot be null
        //root's value is the smallest
        int min = root.val;
        findHelper(root, min);
        return result == null ? -1 : result;
    }

    private void findHelper(TreeNode node, int min) {
        if (node == null) {
            return;
        }
        if (node.val == min) {
            findHelper(node.left, min);
            findHelper(node.right, min);
        } else {
            //node.val > min
            if (result == null) {
                result = node.val;
            } else {
                result = Math.min(node.val, result);
            }
        }
    }


}
