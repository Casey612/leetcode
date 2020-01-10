package exec.Tree;

import java.util.LinkedList;
import java.util.List;

/**
 * #235
 * <p>
 * LCA:
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).
 */
public class LCAofBinarySearchTree {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == q) {
            return p;
        }

        List<TreeNode> path1 = findPath(root, p);
        List<TreeNode> path2 = findPath(root, q);

        TreeNode result = null;
        int length = Math.min(path1.size(), path2.size());
        for (int i = 0; i < length; i++) {
            TreeNode pPart = path1.get(i);
            TreeNode qPart = path2.get(i);
            if (pPart == qPart) {
                result = pPart;
            } else {
                break;
            }
        }
        return result;
    }

    private List<TreeNode> findPath(TreeNode root, TreeNode target) {
        List<TreeNode> result = new LinkedList<>();
        if (root == null || target == null) {
            return result;
        }
        TreeNode cur = root;
        while (cur != null) {
            result.add(cur);
            if (cur == target) {
                break;
            } else if (cur.val < target.val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return result;
    }


}
