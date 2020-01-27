package exec.Tree;

import java.util.*;

/**
 * #662. Maximum Width of Binary Tree
 */
public class MaximumWidthofBinaryTree {

    private TreeNode EMPTY = new TreeNode(Integer.MIN_VALUE);

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        List<TreeNode> levelNode = new ArrayList<>();
        boolean addNode = false;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur != null) {
                levelNode.add(cur);
                TreeNode left = EMPTY, right = EMPTY;
                if (cur.left != null) {
                    addNode = true;
                    left = cur.left;
                }
                if (addNode) {
                    queue.add(left);
                }
                if (cur.right != null) {
                    addNode = true;
                    right = cur.right;
                }
                if (addNode) {
                    queue.add(right);
                }
            } else {
                //cur == null, new level
                int levelSize = getSize(levelNode);
                if (levelSize == 0) {
                    queue.clear();
                }
                result = result < levelSize ? levelSize : result;
                levelNode.clear();
                addNode = false;
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
            }
        }
        return result;
    }

    private int getSize(List<TreeNode> levelNode) {
        int result = levelNode.size();
        for (int i = levelNode.size() - 1; i >= 0; i--) {
            TreeNode cur = levelNode.get(i);
            if (cur == EMPTY) {
                result--;
            } else {
                break;
            }
        }
        return result;
    }


    public int widthOfBinaryTree1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = 1;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        deque.add(null);
        boolean addNode = false;
        while (!deque.isEmpty()) {
            TreeNode cur = deque.poll();
            if (cur != null) {
                TreeNode left = EMPTY, right = EMPTY;
                if (cur.left != null) {
                    addNode = true;
                    left = cur.left;
                }
                if (addNode) {
                    deque.add(left);
                }
                if (cur.right != null) {
                    addNode = true;
                    right = cur.right;
                }
                if (addNode) {
                    deque.add(right);
                }
            } else {
                //cur == null, new level
                while (!deque.isEmpty() && deque.getLast() == EMPTY) {
                    deque.pollLast();
                }
                int levelSize = deque.size();
                result = result < levelSize ? levelSize : result;
                addNode = false;
                if (!deque.isEmpty()) {
                    deque.add(null);
                }
            }
        }
        return result;
    }

}
