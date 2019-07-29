package exec.Tree;

import java.util.Stack;

import exec.Tree.TreeNode;

public class IsValidBST {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        TreeNode temp = root;
        Stack<TreeNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        Integer left = null;
        while (!stack.empty()) {
            TreeNode top = stack.pop();
            if(left !=null && top.val <= left){
                return false;
            }else {
                left = top.val;
            }
            if (top.right != null) {
                temp = top.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
        return true;
    }
}
