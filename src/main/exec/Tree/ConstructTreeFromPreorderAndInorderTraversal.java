package exec.Tree;

import java.util.Arrays;

public class ConstructTreeFromPreorderAndInorderTraversal {
    
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        
        int val = preorder[0];
        TreeNode result = new TreeNode(val);
        
        int valIndex = arrayIndex(inorder, val);
        
        int[] leftInoder = Arrays.copyOfRange(inorder, 0, valIndex);
        int leftSize = leftInoder.length;
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftSize);
        
        result.left = buildTree1(leftPreorder, leftInoder);
        
        int[] rightInorder = Arrays.copyOfRange(inorder, valIndex + 1, inorder.length);
        int[] rightPreorder = Arrays.copyOfRange(preorder, leftSize + 1, preorder.length);
        result.right = buildTree1(rightPreorder, rightInorder);
        
        return result;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        if (preorder.length != inorder.length) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        
        System.out.println(preStart + " " + preEnd + " " + inStart + " " + inEnd);
        
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        
        int val = preorder[preStart];
        TreeNode result = new TreeNode(val);
        
        if (inStart == inEnd || preStart == preEnd) {
            return result;
        }
        
        int valIndex = arrayIndex(inorder, val);
        int leftSize = valIndex - inStart;
        
        if (valIndex > 0) {
            result.left = buildTree(preorder, preStart + 1, preStart + leftSize, inorder, inStart, valIndex - 1);
        }
        if (valIndex < inorder.length - 1) {
            result.right = buildTree(preorder, preStart + leftSize + 1, preEnd, inorder, valIndex + 1, inEnd);
        }
        
        return result;
    }
    
    private int arrayIndex(int[] inorder, int val) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        ConstructTreeFromPreorderAndInorderTraversal builder = new ConstructTreeFromPreorderAndInorderTraversal();
        LevelOrderTraversal traversal = new LevelOrderTraversal();
        
        TreeNode root = builder.buildTree(new int[] {1, 2, 3}, new int[] {2, 3, 1});
        traversal.showTreeInLeverOrder(root);
        
        root = builder.buildTree(new int[] {1, 2, 3}, new int[] {3, 2, 1});
        traversal.showTreeInLeverOrder(root);
        
        root = builder.buildTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7});
        traversal.showTreeInLeverOrder(root);
    }
    
}
