package exec.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author yuzhe
 * @since 3/27/19
 */
public class MiddleOrderTree {

    /**
     * 中序遍历
     * 递归
     * @param root
     * @return
     */
    private List<Integer> middleOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root.left != null) {
            result.addAll(middleOrder(root.left));
        }
        result.add(root.val);
        if (root.right != null) {
            result.addAll(middleOrder(root.right));
        }
        return result;
    }

    /**
     * 中序遍历
     * 循环
     * @param root
     * @return
     */
    private List<Integer> middleOrder2(TreeNode root) {
        TreeNode temp = root;
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        while (!stack.isEmpty()) {
            temp = stack.pop();
            result.add(temp.val);
            if(temp.right != null){
                temp = temp.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
        }
        return result;
    }


}
