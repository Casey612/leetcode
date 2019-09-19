package exec.Tree;

import exec.LinkedList.ListNode;

public class ConvertSortedListToBinarySearchTree {
    
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        TreeNode result = null;
        while (temp != null) {
            result = insert(temp.val, result);
            temp = temp.next;
        }
        return result;
    }
    
    private TreeNode insert(int val, TreeNode result) {
        if (result == null) {
            result = new TreeNode(val);
            return result;
        }
        //插入节点
        if (val > result.val) {
            //插入右子树
            result.right = insert(val, result.right);
        } else if (val < result.val) {
            result.left = insert(val, result.left);
        }
        //再平衡
        int rightHeight = height(result.right);
        int leftHeight = height(result.left);
        if (rightHeight - leftHeight == 2) {
            //链表有序，只有RR型
            result = RRRightRotate(result);
        }
        return result;
    }
    
    public TreeNode RRRightRotate(TreeNode root) {
        TreeNode result = root.right;
        
        TreeNode leftCur = result.left;
        root.right = leftCur;
        
        result.left = root;
        return result;
    }
    
    
    public static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int rightHeight = height(node.right);
        int leftHeight = height(node.left);
        return rightHeight > leftHeight ? rightHeight + 1 : leftHeight + 1;
    }
    
    public static void main(String[] args) {
        ListNode l0 = new ListNode(0);
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l0.next = l1;
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        
        ConvertSortedListToBinarySearchTree converter = new ConvertSortedListToBinarySearchTree();
        TreeNode result = converter.sortedListToBST(l0);
        LevelOrderTraversal traversal = new LevelOrderTraversal();
        traversal.showTreeInLeverOrder(result);
    }
    
}
