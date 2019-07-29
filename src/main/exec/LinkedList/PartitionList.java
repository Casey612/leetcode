package exec.LinkedList;

import exec.LinkedList.ListNode;

public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode cur = head, pre = result, tail = result;
        while (cur != null) {
            if (cur.val >= x) {
                tail.next = new ListNode(cur.val);
                tail = tail.next;
            } else {
                //cur.val < x
                ListNode temp = new ListNode(cur.val);
                temp.next = pre.next;
                pre.next = temp;
                if (tail == pre) {
                    tail = temp;
                }
                pre = temp;
            }
            cur = cur.next;
        }
        return result.next;
    }

}
