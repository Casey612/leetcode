package exec.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * #515
 */
public class FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            result.add(root.val);
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        int curLevelMax = root.val;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                curLevelMax = Math.max(curLevelMax, cur.val);
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            } else {
                //cur == null;
                result.add(curLevelMax);
                if (queue.peek() != null) {
                    curLevelMax = queue.peek().val;
                }
                if (!queue.isEmpty()){
                    queue.add(null);
                }
            }

        }
        return result;
    }

}
