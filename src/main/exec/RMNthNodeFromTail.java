package exec;

/**
 * @author yuzhe
 * @since 3/7/19
 */
public class RMNthNodeFromTail {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode temp = head, pre, result = head;
        int size = 0, index = 0;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        int targetIndex = size - n + 1;
        if (targetIndex == 0) {
            result = head.next;
        } else {
            pre = temp;
            temp = head;
            while (temp != null) {
                if (index != targetIndex) {
                    pre = temp;
                    temp = temp.next;
                    index++;
                } else {
                    pre.next = temp.next;
                    break;
                }
            }
        }
        return result;
    }

}

