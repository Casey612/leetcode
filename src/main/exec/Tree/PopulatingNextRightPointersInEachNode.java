package exec.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * #: 116
 * perfect binary tree
 */
public class PopulatingNextRightPointersInEachNode {
    
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        int levelSize = 2;
        
        while (!queue.isEmpty()) {
            int temp = levelSize;
            while (temp > 0) {
                Node pre = queue.poll();
                Node next = null;
                if (temp != 1) {
                    next = queue.peek();
                }
                temp--;
                pre.next = next;
                if (pre.left != null) {
                    queue.add(pre.left);
                }
                if (pre.right !=  null) {
                    queue.add(pre.right);
                }
            }
            levelSize = levelSize << 1;
        }
        return root;
    }
    
    public static void main(String[] args) {
        int i = 2;
        i = i << 2;
        System.out.println(i);
    }
}
