package exec;

/**
 * @author yuzhe
 * @since 9/27/18
 */
public class Add {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int mul = 0, val = 0;
        ListNode result = null, index = null;
        while (l1 != null || l2 != null) {
            if (l1 != null && l2 != null) {
                val = l1.val + l2.val + mul;

            } else if (l1 != null) {
                val = l1.val + mul;
            } else {
                val = (l2.val + mul);
            }
            if (index == null) {
                index = new ListNode((val + mul) % 10);
                result = index;
            } else {
                index.next = new ListNode((val + mul) % 10);
                index = index.next;
            }
            mul =  val / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        return result;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
