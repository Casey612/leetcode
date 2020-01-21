package exec.Tree;

/**
 * # 617
 */
public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 == null || t2 == null) {
            TreeNode notEmpty = t1 == null ? t2 : t1;
            TreeNode left = mergeTrees(notEmpty.left, null);
            TreeNode right = mergeTrees(notEmpty.right, null);
            notEmpty.left = left;
            notEmpty.right = right;
            return notEmpty;
        } else {
            //t1 != null && t2 != null
            TreeNode result = new TreeNode(t1.val + t2.val);
            TreeNode left = mergeTrees(t1.left, t2.left);
            TreeNode right = mergeTrees(t1.right, t2.right);
            result.left = left;
            result.right = right;
            return result;
        }
    }

}
