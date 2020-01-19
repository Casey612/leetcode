package exec.Tree;

import java.util.Stack;

/**
 * 449 todo @yuki
 */
public class SerializeAndDeserializeBST {


    private static final String COMMA_SPLITTER = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            sb.append(cur.val).append(COMMA_SPLITTER);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strs = data.split(COMMA_SPLITTER);
        int[] array = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            array[i] = Integer.parseInt(strs[i]);
        }
        return buildTree(array, 0, array.length - 1);
    }

    private TreeNode buildTree(int[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        int cur = array[start];
        TreeNode result = new TreeNode(cur);
        int subStart = start + 1;
        while (subStart <= end && array[subStart]< cur) {
            subStart++;
        }
        result.left = buildTree(array, start + 1, subStart - 1);
        result.right = buildTree(array, subStart, end);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(3);
        root.left =n1;
        root.right = n2;
        SerializeAndDeserializeBST holder = new SerializeAndDeserializeBST();
        String str = holder.serialize(root);
        holder.deserialize(str);
    }

}
