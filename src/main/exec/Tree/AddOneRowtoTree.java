package exec.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * # 623
 */
public class AddOneRowtoTree {

    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode result = new TreeNode(v);
            result.left = root;
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int level = 1;
        while (!queue.isEmpty() && level < d) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                if (level == d -1) {
                    //插入
                    TreeNode left = new TreeNode(v);
                    TreeNode right = new TreeNode(v);
                    left.left = cur.left;
                    right.right = cur.right;
                    cur.left = left;
                    cur.right = right;
                } else {
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
            } else {
                //cur == null;
                level++;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
        return root;
    }

}
