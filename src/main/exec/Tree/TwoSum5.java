package exec.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * #653
 */
public class TwoSum5 {

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        boolean result = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!result && !queue.isEmpty()) {
            TreeNode cur = queue.poll();
            result = findTargetHelper(root, k - cur.val, cur);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return result;
    }

    /**
     * 已锁定一个节点，寻找另一个节点
     * @param root
     * @param targetVal
     * @return
     */
    private boolean findTargetHelper(TreeNode root, int targetVal, TreeNode curNode) {
        if (root == null) {
            return false;
        }
        if (root.val == targetVal) {
            if (root != curNode) {
                return true;
            } else {
                return findTargetHelper(root.right, targetVal, curNode)
                        || findTargetHelper(root.left, targetVal, curNode);
            }
        } else if (root.val < targetVal) {
            return findTargetHelper(root.right, targetVal, curNode);
        } else {
            //root.val > k
            return findTargetHelper(root.left, targetVal, curNode);
        }

    }

}
