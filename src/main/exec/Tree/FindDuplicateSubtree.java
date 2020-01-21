package exec.Tree;

import java.util.ArrayList;
import java.util.List;

public class FindDuplicateSubtree {

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            return result;
        }
        return null;
    }

}
