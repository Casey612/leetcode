package exec.Tree;

/**
 * #114. Flatten Binary Tree to Linked List
 */
public class FlattenBinaryTreeToLinkedList {
    
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        
        TreeNode leftPart = root.left;
        TreeNode rightPart = root.right;
        
        flatten(leftPart);
        flatten(rightPart);
        
        if (leftPart != null) {
            TreeNode temp = leftPart;
            while (temp.right != null) {
                temp = temp.right;
            }
            temp.right = rightPart;
            root.right = leftPart;
            root.left = null;
        }
        
        
    }
    
}
