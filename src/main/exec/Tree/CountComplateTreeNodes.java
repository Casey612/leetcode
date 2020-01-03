package exec.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CountComplateTreeNodes {


    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int result = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            result++;
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        return result;
    }

    public int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        int index = 0;
        TreeNode cur = list.get(index);
        while (cur.left != null || cur.right != null) {
            if (cur.left != null) {
                list.add(cur.left);
            }
            if (cur.right != null) {
                list.add(cur.right);
            }
            index++;
            cur = list.get(index);
        }
        return list.size();
    }

    public int countNode1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = leftDepth(root);
        int rightHeight = rightDepth(root);

        //perfect binary tree
        if (leftHeight == rightHeight) {
            if (leftHeight == 1) {
                return 1;
            } else {
                return (2 << (leftHeight - 1)) - 1;
            }
        } else {
            int leftPartRightHeight = rightDepth(root.left);

            if (leftHeight - 1 == leftPartRightHeight) {
                //left part is full
                return countNode1(root.right) + (2 << (leftPartRightHeight - 1));
            } else {
                return countNode1(root.left) + (2 << (rightHeight - 2));
            }
        }
    }

    public int leftDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        TreeNode cur = node;
        int result = 0;
        while (cur != null) {
            result++;
            cur = cur.left;
        }
        return result;
    }

    public int rightDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        TreeNode cur = node;
        int result = 0;
        while (cur != null) {
            result++;
            cur = cur.right;
        }
        return result;
    }


    public static void main(String[] args) {
        double result = Math.pow(2, 2);
        System.out.println(result);
        System.out.println(2<<1);

        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);

        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;

        CountComplateTreeNodes counter = new CountComplateTreeNodes();
        System.out.println(counter.countNode1(n0));
    }

}
