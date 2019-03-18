package exec;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (k % count == 0) {
            return head;
        } else {
            k = k % count;
        }
        temp = head;
        int index = 0;
        while (index < (count - k)) {
            index++;
            temp = temp.next;
        }
        if (temp.next != null) {
            //need rotate
            ListNode tail = temp.next, result = temp.next;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = head;
            temp.next = null;
            return result;
        } else {
            return head;
        }

    }

}
