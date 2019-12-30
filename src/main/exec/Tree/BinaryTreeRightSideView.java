package exec.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
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

        int childNum = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.peek();
            if (cur.left != null) {
                queue.add(cur.left);
                childNum++;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                childNum++;
            }
            if (queue.size() - childNum == 1) {
                result.add(cur.val);
                //下一层
                childNum = 0;
            }
            queue.poll();
        }
        return result;
    }

}
