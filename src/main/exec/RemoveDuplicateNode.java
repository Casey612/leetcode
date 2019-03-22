package exec;

public class RemoveDuplicateNode {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode pre = head, tail = pre.next,
                result = new ListNode(Integer.MIN_VALUE), temp = result;
        while (tail != null) {
            if (pre.val == tail.val) {
                tail = tail.next;
            } else {
                if (pre.next == tail) {
                    temp.next = new ListNode(pre.val);
                    temp = temp.next;
                }
                pre = tail;
                tail = tail.next;
            }
        }
        if(pre.next == null){
            temp.next = new ListNode(pre.val);
        }
        return result.next;
    }

}
