package exec.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * # 513
 */
public class FindBottomLeftTreeValue {

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int result = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            } else {
                if (queue.peek() != null) {
                    result = queue.peek().val;
                }
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(3);
        root.left =n1;
        root.right = n2;
        FindBottomLeftTreeValue holder = new FindBottomLeftTreeValue();
        System.out.println(holder.findBottomLeftValue(root));
    }


}
