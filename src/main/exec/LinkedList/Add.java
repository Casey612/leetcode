package exec.LinkedList;

/**
 * @author yuzhe
 * @since 9/27/18
 */
public class Add {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int mul = 0, val = 0;
        ListNode result = null, index = null;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;

            val = x + y + mul;
            mul = val / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }

            if (index == null) {
                //first node
                index = new ListNode((val) % 10);
                result = index;
            } else {
                index.next = new ListNode((val) % 10);
                index = index.next;
            }

        }
        if (mul > 0) {
            index.next = new ListNode(mul);
        }
        return result;
    }

}

