package exec.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 919. Complete Binary Tree Inserter
 */
public class CBTInserter {

    private TreeNode root;
    private Queue<TreeNode> queue = new LinkedList<>();

    public CBTInserter(TreeNode root) {
        this.root = root;
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
            if (cur.left == null || cur.right == null) {
                break;
            } else {
                cur = queue.poll();
            }
        }
    }

    public int insert(int v) {
        TreeNode cur = queue.peek();
        TreeNode target = new TreeNode(v);
        queue.add(target);
        if (cur == null) {
            this.root = target;
            return v;
        } else {
            if (cur.left == null) {
                cur.left = target;
            } else {
                cur.right = target;
                queue.poll();
            }
            return cur.val;
        }
    }

    public TreeNode get_root() {
        return this.root;
    }

}
