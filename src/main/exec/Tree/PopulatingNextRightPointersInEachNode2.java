package exec.Tree;

public class PopulatingNextRightPointersInEachNode2 {
    
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        return connectHelper(root, null);
    }
    
    private Node connectHelper(Node cur, Node parent) {
        if (cur == null) {
            return null;
        }
        Node next = null;
        if (parent != null) {
            if (cur == parent.left && parent.right != null) {
                next = parent.right;
            } else {
                Node parentSubling = parent.next;
                while (parentSubling != null) {
                    if (parentSubling.left != null) {
                        next = parentSubling.left;
                        break;
                    } else if (parentSubling.right != null) {
                        next = parentSubling.right;
                        break;
                    }else {
                        parentSubling = parentSubling.next;
                    }
                }
            }
        }
        cur.next = next;
        connectHelper(cur.right, cur);
        connectHelper(cur.left, cur);
        return cur;
    }
    
}
