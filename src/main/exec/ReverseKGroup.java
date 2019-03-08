package exec;

import java.util.Stack;

/**
 * @author yuzhe
 * @since 3/8/19
 */
public class ReverseKGroup {

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = new ListNode(Integer.MIN_VALUE);
        ListNode temp = result, pre = head, tail;
        Stack<Integer> stack = new Stack<>();
        while (pre != null) {
            tail = pre;
            while (tail != null && stack.size() < k) {
                stack.add(tail.val);
                tail = tail.next;
            }
            if (stack.size() == k) {
                while (!stack.isEmpty()) {
                    temp.next = new ListNode(stack.pop());
                    temp = temp.next;
                }
                pre = tail;
            } else {
                while (pre != null) {
                    temp.next = new ListNode(pre.val);
                    temp = temp.next;
                    pre = pre.next;
                }
            }
        }
        return result.next;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode temp = list;
        for (int i = 2; i <= 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp = reverseKGroup(list, 2);
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
    }

}
