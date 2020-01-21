package exec.Tree;

/**
 * #606
 */
public class ConstructStringFromBianryTree {

    public String tree2str(TreeNode t) {
        return tree2strHelper(t, true, true);
    }

    private String tree2strHelper(TreeNode t, boolean isRoot, boolean isLeft) {
        if (t == null) {
            if (!isRoot && isLeft) {
                return "()";
            }
            //根节点为空 / 右子节点为空
            return "";
        } else {
            //node t is not empty
            StringBuilder sb = new StringBuilder();
            if (!isRoot) {
                sb.append('(');
            }
            sb.append(t.val);
            if (t.left != null || t.right != null) {
                String left = tree2strHelper(t.left, false, true);
                String right = tree2strHelper(t.right, false, false);
                sb.append(left).append(right);
            }
            if (!isRoot) {
                sb.append(')');
            }
            return sb.toString();
        }
    }

}
