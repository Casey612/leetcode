package exec.Tree;

import java.util.Stack;

/**
 * #538
 *
 */
public class ConvertBSTtoGreterTree {

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.right;
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            cur.val += sum;
            sum = cur.val;
            if (cur.left != null) {
                temp = cur.left;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.right;
                }
            }
        }
        return root;
    }




































}
