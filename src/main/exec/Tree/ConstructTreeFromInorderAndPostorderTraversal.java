package exec.Tree;

public class ConstructTreeFromInorderAndPostorderTraversal {
    
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0) {
            return null;
        }
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        if (postorder.length != inorder.length) {
            return null;
        }
        return buildTree(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
        System.out.println(postStart + " " + postEnd + " " + inStart + " " + inEnd);
    
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }
    
        int val = postorder[postEnd];
        TreeNode result = new TreeNode(val);
    
        if (inStart == inEnd || postStart == postEnd) {
            return result;
        }
    
        int valIndex = arrayIndex(inorder, val);
        int leftSize = valIndex - inStart;
    
        if (valIndex > 0) {
            result.left = buildTree(postorder, postStart, postStart + leftSize - 1, inorder, inStart, valIndex - 1);
        }
        if (valIndex < inorder.length - 1) {
            result.right = buildTree(postorder, postStart + leftSize, postEnd - 1, inorder, valIndex + 1, inEnd);
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
        ConstructTreeFromInorderAndPostorderTraversal builder = new ConstructTreeFromInorderAndPostorderTraversal();
        LevelOrderTraversal traversal = new LevelOrderTraversal();
        
        TreeNode root = builder.buildTree(new int[] {2, 3, 1}, new int[] {3, 2, 1});
        traversal.showTreeInLeverOrder(root);
        
        root = builder.buildTree(new int[] {3, 2, 1}, new int[] {3, 2, 1});
        traversal.showTreeInLeverOrder(root);
        
        root = builder.buildTree(new int[] {9, 3, 15, 20, 7}, new int[] {9, 15, 7, 20, 3});
        traversal.showTreeInLeverOrder(root);
    }
    
    
}
