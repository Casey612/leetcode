package exec.LinkedList;

public class LinkedListCycle {
    
    public boolean hasCycle(ListNode head) {
        
        if (head == null) {
            return false;
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
                 return true;
             }
             
         }
        return false;
    }
    
    public static void main(String[] args) {
        
        LinkedListCycle cycle = new LinkedListCycle();
    
        ListNode head = new ListNode(1);
    
        System.out.println(cycle.hasCycle(head));
    }
    
}
