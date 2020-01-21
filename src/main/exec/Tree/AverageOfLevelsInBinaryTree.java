package exec.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * # 637
 */
public class AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        double sum = 0;
        int num = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                sum += cur.val;
                num++;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            } else {
                //cur == null;
                result.add(sum / (double) num);
                sum = 0;
                num = 0;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
        return result;
    }

}
