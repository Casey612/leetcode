package exec.LinkedList;

import exec.LinkedList.ListNode;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class SwapPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = new ListNode(Integer.MIN_VALUE), cur = result;
        ListNode pre = head, temp = pre.next;
        while (pre != null) {
            if (temp != null) {
                cur.next = new ListNode(temp.val);
                cur = cur.next;
            }
            cur.next = new ListNode(pre.val);
            cur = cur.next;
            if (temp != null) {
                pre = temp.next;
                if (pre != null) {
                    temp = pre.next;
                }
            } else {
                pre = null;
            }
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(0);
        ListNode temp = list;
        for (int i = 1; i < 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp = swapPairs(list);
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

}
