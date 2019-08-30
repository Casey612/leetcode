package exec.Tree;

import java.util.*;

import exec.Tree.TreeNode;

/**
 * @author yuzhe
 * @since 3/27/19
 */
public class SymmetricTree {
    
    /**
     * 层序遍历
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while (!queue.isEmpty()) {
            List<Integer> array = getArray(queue);
            System.out.println(Arrays.toString(array.toArray()));
            if (!isSymmetricArray(array)) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> getArray(Queue<TreeNode> queue) {
        List<Integer> result = new ArrayList<>();
        boolean lastLevel = true;
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                break;
            } else {
                result.add(temp.val);
                if (lastLevel && Integer.MIN_VALUE != temp.val) {
                    lastLevel = false;
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                } else {
                    queue.add(new TreeNode(Integer.MIN_VALUE));
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                } else {
                    queue.add(new TreeNode(Integer.MIN_VALUE));
                }
            }
        }
        if (!lastLevel && !result.isEmpty()) {
            queue.add(null);
        } else {
            queue.clear();
        }
        return result;
    }

    private boolean isSymmetricArray(List<Integer> array) {
        int low = 0, high = array.size() - 1;
        while (low < high) {
            Integer lowVal = array.get(low);
            Integer highVal = array.get(high);
            if (lowVal.equals(highVal)) {
                low++;
                high--;
            } else {
                return false;
            }
        }
        return true;
    }
    
    
    
    /**
     * 递归调用
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else {
            return isMirror(left, right);
        }
    }
    
    private boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if (left == null || right == null) {
            return false;
        } else {
            if (left.val != right.val) {
                return false;
            } else {
                return isMirror(left.left, right.right) && isMirror(left.right, right.left);
            }
        }
    }
    
    public static void main(String[] args) {
        SymmetricTree symmetricTree = new SymmetricTree();
        
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);
        
        root.left = node1;
        root.right = node2;
        
        node1.left = node3;
        node1.right = node4;
        
        node2.left = node5;
        node2.right = node6;
    
        System.out.println(symmetricTree.isSymmetric2(root));
        
    }
    
}
