package exec.LinkedList;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle2 {
    
    public ListNode detectCycle(ListNode head) {
        
        if (head == null) {
            return head;
        }
        
        ListNode one = head, two = head;
        while (one != null && two != null) {
            one = one.next;
            
            two = two.next;
            if (two != null) {
                two = two.next;
            } else {
                two = null;
            }
            if (two != null && one == two) {
                break;
            }
            
        }
        if (one == null || two == null) {
            return null;
        }
        one = head;
        while (one != two) {
            one = one.next;
            two = two.next;
        }
        return one;
    }
    
    /**
     * 空间
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        
        if (head == null) {
            return head;
        }
        ListNode temp = head;
        Set<ListNode> set = new HashSet<>();
        
        while (temp != null) {
            if (!set.contains(temp)) {
                set.add(temp);
                temp = temp.next;
            } else {
                return temp;
            }
        }
        return null;
    }

}
