package exec.Tree;

import java.util.*;

import exec.Tree.TreeNode;

public class InorderTree {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));
        return result;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        while (!stack.empty()) {
            TreeNode top = stack.pop();
            result.add(top.val);
            if (top.right != null) {
                temp = top.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
        return result;
    }

}
