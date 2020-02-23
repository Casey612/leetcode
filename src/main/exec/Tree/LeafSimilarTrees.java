package exec.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 872. Leaf-Similar Trees
 */
public class LeafSimilarTrees {

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = getLeaves(root1);
        List<Integer> leaves2 = getLeaves(root2);
        if (leaves1.size() == leaves2.size()) {
            for (int index = 0; index < leaves1.size(); index++) {
                if (!leaves1.get(index).equals(leaves2.get(index))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static List<Integer> getLeaves(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        stack.push(temp);
        while (temp.left != null) {
            temp = temp.left;
            stack.push(temp);
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left == null && cur.right == null) {
                //leaf
                result.add(cur.val);
            } else {
                if (cur.right != null) {
                    temp = cur.right;
                    stack.push(temp);
                    while (temp.left != null) {
                        temp = temp.left;
                        stack.push(temp);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        leafSimilar(root1, root2);
    }
}
