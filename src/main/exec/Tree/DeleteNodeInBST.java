package exec.Tree;

/**
 * # 450
 */
public class DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        return deleteNodeHelper(root, null, key);
    }

    private TreeNode deleteNodeHelper(TreeNode cur, TreeNode parent, int key) {
        if (cur.val < key) {
            if (cur.right != null) {
                cur.right = deleteNodeHelper(cur.right, cur, key);
            }
            return cur;
        } else if (cur.val > key) {
            if (cur.left != null) {
                cur.left = deleteNodeHelper(cur.left, cur, key);
            }
            return cur;
        } else {
            //cur.val == key
            TreeNode right = cur.right;
            TreeNode left = cur.left;

            if(left == null || right == null) {
                TreeNode sub = right == null ? left : right;
                if (parent != null) {
                    if (parent.left == cur) {
                        parent.left = sub;
                    } else {
                        parent.right = sub;
                    }
                }
                return sub;
            } else {
                TreeNode rightLeft = cur.right.left;

                TreeNode leftRightParent = left;
                while (leftRightParent.right != null) {
                    leftRightParent = leftRightParent.right;
                }
                //左子节点接收右节点的左侧为右孩子
                leftRightParent.right = rightLeft;
                right.left = left;

                if (parent != null) {
                    if (parent.left == cur) {
                        parent.left = right;
                    } else {
                        parent.right = right;
                    }
                }
                return right;
            }
        }
    }

}
