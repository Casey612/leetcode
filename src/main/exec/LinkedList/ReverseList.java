package exec.LinkedList;

public class ReverseList {

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode pre = null, tail = head;
        while (tail != null) {
            ListNode temp = tail.next;
            tail.next = pre;
            pre = tail;
            tail = temp;
        }
        return pre;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverseList1(head.next);
        ListNode pre = result;
        while (pre.next != null) {
            pre = pre.next;
        }
        pre.next = head;
        head.next = null;
        return result;
    }

    public static void main(String[] args) {
        ReverseList reverser = new ReverseList();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListNode result = reverser.reverseList1(n1);
        print(result);
    }

    private static void print(ListNode result) {
        ListNode temp = result;
        while (temp != null) {
            System.out.println(temp.val + " ");
            temp = temp.next;
        }
    }


}
