package exec.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * #236
 */
public class LCAofBinaryTree {


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (p == q) {
            return p;
        }

        List<TreeNode> path1 = findTargetPath(p, q, new ArrayList<>());
        if (path1 != null) {
            return p;
        }
        List<TreeNode> path2 = findTargetPath(q, p, new ArrayList<>());
        if (path2 != null) {
            return q;
        }

        path1 = findTargetPath(root, p, new ArrayList<>());
        path2 = findTargetPath(root, q, new ArrayList<>());

        if (path1 == null || path2 == null) {
            return null;
        }
        if (path1 == path2) {
            return path1.indexOf(p) < path1.indexOf(q) ? p : q;
        } else {
            int length = Math.min(path1.size(), path2.size());
            TreeNode result = root;
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
    }

    private List<TreeNode> findTargetPath(TreeNode root, TreeNode target, List<TreeNode> list) {
        if (root == null) {
            return null;
        }
        //root is not null
        list.add(root);
        if (root == target) {
            return list;
        } else if (root.left == null && root.right == null) {
            //leaf node 未找到target
            return null;
        } else {
            List<TreeNode> leftPart = findTargetPath(root.left, target, new ArrayList<>(list));
            List<TreeNode> rightPart = findTargetPath(root.right, target, new ArrayList<>(list));
            return leftPart == null ? rightPart : leftPart;
        }

    }


}
