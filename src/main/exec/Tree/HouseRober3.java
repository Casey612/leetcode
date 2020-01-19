package exec.Tree;

/**
 * #337
 */
public class HouseRober3 {

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return robHelper(root, false);
    }

    private int robHelper(TreeNode root, boolean parentRob) {
        //抢劫该节点方案
        int plan1 = 0;
        //不抢劫该节点方案
        int plan2 = 0;

        if (!parentRob) {
            //父节点未被抢劫，该节点可被抢劫，也可不被抢劫
            plan1 += root.val;
            if (root.left != null) {
                plan1 += robHelper(root.left, true);
            }
            if (root.right != null) {
                plan1 += robHelper(root.right, true);
            }
        }
        //不抢劫该节点 去往下一节点
        if (root.left != null) {
            plan2 += robHelper(root.left, false);
        }
        if (root.right != null) {
            plan2 += robHelper(root.right, false);
        }
        return Math.max(plan1, plan2);
    }

}
