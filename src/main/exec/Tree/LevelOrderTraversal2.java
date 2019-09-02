package exec.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal2 {
    
    
    public List<List<Integer>> levelOrderButtom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
    
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        List<Integer> currentLevel = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                currentLevel.add(temp.val);
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
                result.push(currentLevel);
                currentLevel = new ArrayList<>();
            }
        }
        return result;
    }
    
    public void showTreeInLeverOrder(TreeNode root) {
        List<List<Integer>> list = levelOrderButtom(root);
        for (List<Integer> item : list) {
            System.out.print(Arrays.toString(item.toArray()));
            System.out.print(" ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        LevelOrderTraversal2 traversal = new LevelOrderTraversal2();
        
        TreeNode root = new TreeNode(3);
        
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        
        root.left = node1;
        root.right = node2;
        
        node2.left = node3;
        node2.right = node4;
    
        System.out.println(traversal.levelOrderButtom(root));
        
    }
    
}
