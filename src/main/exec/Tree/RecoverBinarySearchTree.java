package exec.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RecoverBinarySearchTree {
    
    public static void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            return;
        }
        
        List<Integer> array = new ArrayList<>();
        Map<Integer, TreeNode> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            array.add(cur.val);
            map.put(cur.val, cur);
            
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        List<Integer> except = new ArrayList<>(array);
        except.sort(Integer::compareTo);
        for (int i = 0; i < array.size(); i++) {
            int ori = array.get(i);
            int tar = except.get(i);
            if (ori != tar) {
                TreeNode node = map.get(ori);
                node.val = tar;
            }
        }
      
    }

    /**
     * 默认 preNode取Integer.MIN 会和测试用例的case冲突 
     */
    private static TreeNode firstNode = null, secondNode = null, preNode = null;
    public static void recoverTree2(TreeNode root) {
        traverse(root);
        if (firstNode != null && secondNode != null) {
            int temp = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = temp;
        }
    }

    private static void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);

        /**
         *  find first target node
         *  BST 应当恒有 preNode.val < cur.val
         */
        if (firstNode == null && (preNode == null || root.val <= preNode.val)) {
            firstNode = preNode;
        }
        if (firstNode != null && root.val <= preNode.val) {
            secondNode = root;
        }
        preNode = root;
        traverse(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        root.left = n1;
        n1.right = n2;
        recoverTree2(root);
    }
    
}
