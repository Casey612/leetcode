package exec.Tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagLevelOrderTraversal {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        
        boolean reverseFlag = false;
        
        LinkedList<Integer> currentLevel = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                if (reverseFlag) {
                    currentLevel.push(temp.val);
                } else {
                    currentLevel.add(temp.val);
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            } else {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                result.add(currentLevel);
                currentLevel = new LinkedList<>();
                reverseFlag = !reverseFlag;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        ZigzagLevelOrderTraversal traversal = new ZigzagLevelOrderTraversal();
        
        TreeNode root = new TreeNode(3);
        
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        
        root.left = node1;
        root.right = node2;
        
        node2.left = node3;
        node2.right = node4;
        
        System.out.println(traversal.levelOrder(root));
        
    }
    
}
