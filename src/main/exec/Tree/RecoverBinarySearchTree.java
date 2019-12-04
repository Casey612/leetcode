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
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        root.left = n1;
        n1.right = n2;
        recoverTree(root);
    }
    
}
