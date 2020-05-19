package exec.LinkedList;

/**
 * @author yuzhe
 * @since 3/27/19
 */
public class ReverseLinkedList {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode result = new ListNode(Integer.MIN_VALUE);
        result.next = head;
        ListNode pre = result, cur = result.next;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        for (int i = 0; i < n - m; i++) {
            ListNode after = cur.next;
            cur.next = after.next;
            after.next = pre.next;
            pre.next = after;
        }

        return result.next;
    }

}
