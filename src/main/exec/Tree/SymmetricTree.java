package exec.Tree;

import java.util.*;

import exec.Tree.TreeNode;

/**
 * @author yuzhe
 * @since 3/27/19
 */
public class SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            List<Integer> array = getArray(queue);
            if (!isSymmetricArray(array)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getArray(Queue<TreeNode> queue) {
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                break;
            } else {
                if (temp.left != null) {
                    queue.add(temp.left);
                } else {

                }
            }
        }
        return result;
    }

    private boolean isSymmetricArray(List<Integer> array) {
        int low = 0, high = array.size() - 1;
        while (low < high) {
            Integer lowVal = array.get(low);
            Integer highVal = array.get(high);
            if (lowVal.equals(highVal)) {
                low++;
                high--;
            } else {
                return false;
            }
        }
        return true;
    }


}
