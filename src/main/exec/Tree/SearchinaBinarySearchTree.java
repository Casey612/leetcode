package exec.Tree;

/**
 * 700. Search in a Binary Search Tree
 */
public class SearchinaBinarySearchTree {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            //root.val > val
            return searchBST(root.left, val);
        }
    }

    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else {
            TreeNode temp = root;
            while (temp != null && temp.val != val) {
                if (temp.val < val) {
                    temp = temp.right;
                } else {
                    temp = temp.left;
                }
            }
            return temp;
        }
    }

}
