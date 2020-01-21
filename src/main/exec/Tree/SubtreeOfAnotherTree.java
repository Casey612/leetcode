package exec.Tree;

/**
 * #572
 */
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return subtreeHelper(s, t, true);
    }

    public boolean subtreeHelper(TreeNode s, TreeNode t, boolean start) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else {
            //s != null && t != null
            if (s.val != t.val) {
                if (start) {
                    //当前节点为根节点
                    return isSubtree(s.left, t) || isSubtree(s.right, t);
                } else {
                    //当前节点为子节点，不连贯返回false
                    return false;
                }
            } else {
                //s.val == t.val
                boolean left = subtreeHelper(s.left, t.left, false);
                boolean right = subtreeHelper(s.right, t.right, false);
                if (left && right) {
                    return true;
                } else {
                    //存在不等的子节点
                    if (start) {
                        //当前节点为根节点
                        return isSubtree(s.left, t) || isSubtree(s.right, t);
                    } else {
                        //当前节点为子节点，不连贯返回false
                        return false;
                    }
                }
            }
        }
    }

}
