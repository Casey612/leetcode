package exec.Tree;

import java.util.Stack;

/**
 * #173.
 *
 */
public class BinarySearchTreeIterator {


    private Stack<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        this.stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = this.stack.pop();
        if (cur.right != null) {
            TreeNode temp = cur.right;
            while (temp != null) {
                this.stack.push(temp);
                temp = temp.left;
            }
        }
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !this.stack.isEmpty();
    }


}
