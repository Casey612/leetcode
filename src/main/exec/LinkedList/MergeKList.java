package exec.LinkedList;

import exec.LinkedList.ListNode;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class MergeKList {

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(Integer.MIN_VALUE), temp = result;
        ListNode[] heads = lists;
        while (!allFinished(heads)) {
            int minIndex = minNodeIndex(heads);
            ListNode minNode = heads[minIndex];
            temp.next = new ListNode(minNode.val);
            temp = temp.next;
            heads[minIndex] = minNode.next;
        }
        return result.next;
    }

    private static int minNodeIndex(ListNode[] nodes) {
        int result = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i] != null && nodes[result] != null && nodes[i].val < nodes[result].val) {
                result = i;
            } else if (nodes[i] != null && nodes[result] == null) {
                result = i;
            }
        }
        return result;
    }

    private static boolean allFinished(ListNode[] nodes) {
        for (ListNode node : nodes) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);
        ListNode result = mergeKLists(new ListNode[]{l1, l2, l3});
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

}
