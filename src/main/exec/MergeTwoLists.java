package exec;

/**
 * @author yuzhe
 * @since 3/7/19
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(Integer.MAX_VALUE), left = l1, right = l2, temp = result;
        while (left != null || right != null) {
            if (left != null && right != null) {
                if (left.val <= right.val) {
                    temp.next = new ListNode(left.val);
                    left = left.next;
                } else {
                    temp.next = new ListNode(right.val);
                    right = right.next;
                }
            } else if (left != null) {
                temp.next = new ListNode(left.val);
                left = left.next;
            } else {
                temp.next = new ListNode(right.val);
                right = right.next;
            }
            temp = temp.next;
        }
        return result.next;
    }
}
