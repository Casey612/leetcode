package exec.Tree;

import java.util.Stack;

import exec.Tree.TreeNode;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class RangeSumBST {

    public int rangeSumBST(TreeNode root, int L, int R) {
        int result = 0;
        if (root == null || L > R) {
            return 0;
        }
        if (root.val < L) {
            result += rangeSumBST(root.right, L, R);
        } else if (root.val > R) {
            result += rangeSumBST(root.left, L, R);
        } else {
            result += root.val;
            result += rangeSumBST(root.right, L, R);
            result += rangeSumBST(root.left, L, R);
        }
        return result;
    }

    public int rangeSumBST1(TreeNode node, int L, int R) {
        int result = 0;
        if (node == null || L > R) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.val >= L && cur.val <= R) {
                    result += cur.val;
                } else if (cur.val > R) {
                    break;
                }
                cur = cur.right;
            }
        }
        return result;
    }

}
